package Logic;

import Model.*;
import java.util.*;

public class TimetableManager {
    private List<CourseModule> courseModules;
    private List<ModuleHours> moduleHours;
    private List<TeachingAssignment> teachingAssignments;
    private List<ModuleEnrollment> enrollments;
    private List<StudentGroup> studentGroups;
    private List<Room> rooms;

    // Balanced slot order: spread across days and prefer middle hours
    private final String[] BALANCED_SLOTS = {
            // Week 1 - Middle hours first (10am-3pm)
            "A2", "B2", "C2", "D2", "E2",  // 10:00-11:00
            "A3", "B3", "C3", "D3", "E3",  // 11:00-12:00
            "A4", "B4", "C4", "D4", "E4",  // 12:00-13:00
            "A5", "B5", "C5", "D5", "E5",  // 13:00-14:00
            "A6", "B6", "C6", "D6", "E6",  // 14:00-15:00
            // Week 2 - Early and late hours
            "A1", "B1", "C1", "D1", "E1",  // 09:00-10:00
            "A7", "B7", "C7", "D7", "E7",  // 15:00-16:00
            "A8", "B8", "C8", "D8", "E8",  // 16:00-17:00
            "A9", "B9", "C9", "D9", "E9"   // 17:00-18:00
    };

    private Map<String, Set<String>> slotOccupancy;

    public TimetableManager(List<CourseModule> courseModules, List<ModuleHours> moduleHours,
                            List<TeachingAssignment> teachingAssignments, List<ModuleEnrollment> enrollments,
                            List<StudentGroup> studentGroups, List<Room> rooms) {
        this.courseModules = courseModules;
        this.moduleHours = moduleHours;
        this.teachingAssignments = teachingAssignments;
        this.enrollments = enrollments;
        this.studentGroups = studentGroups;
        this.rooms = rooms;
        this.slotOccupancy = new HashMap<>();

        for (String slot : BALANCED_SLOTS) {
            slotOccupancy.put(slot, new HashSet<>());
        }
    }

    public List<TimetableSlot> makeTimetable() {
        System.out.println("Starting balanced timetable generation...");
        List<TimetableSlot> timetable = new ArrayList<>();

        Map<String, List<TeachingAssignment>> assignmentsByModule = groupAssignmentsByModule();

                timetable.addAll(scheduleLectures(assignmentsByModule));

        timetable.addAll(scheduleTutorialsAndLabs(assignmentsByModule));

        System.out.println("Balanced timetable generation completed. Generated " + timetable.size() + " slots.");
        return timetable;
    }

    private List<TimetableSlot> scheduleLectures(Map<String, List<TeachingAssignment>> assignmentsByModule) {
        List<TimetableSlot> lectureSlots = new ArrayList<>();
        Set<String> usedSlots = new HashSet<>();

        for (CourseModule courseModule : courseModules) {
            String moduleCode = courseModule.getModuleCode();
            List<TeachingAssignment> moduleAssignments = assignmentsByModule.get(moduleCode);

            if (moduleAssignments == null) continue;

            TeachingAssignment lectureAssignment = null;
            for (TeachingAssignment assignment : moduleAssignments) {
                if ("lecture".equalsIgnoreCase(assignment.getClassType())) {
                    lectureAssignment = assignment;
                    break;
                }
            }

            if (lectureAssignment == null) continue;

            ModuleHours hours = findModuleHours(moduleCode);
            if (hours == null) continue;

            List<StudentGroup> groupsForModule = findGroupsForModule(courseModule);
            if (groupsForModule.isEmpty()) continue;

            String sharedGroupId = "ALL_" + courseModule.getCourse() + "_Y" + courseModule.getYear();

            Set<String> individualGroups = new HashSet<>();
            for (StudentGroup group : groupsForModule) {
                individualGroups.add(group.getGroupId());
            }

            // Schedule lectures with day distribution
            for (int i = 0; i < hours.getLectureHours(); i++) {
                TimetableSlot lectureSlot = scheduleBalancedSlot(
                        moduleCode, "lecture", lectureAssignment, sharedGroupId, individualGroups, usedSlots
                );

                if (lectureSlot != null) {
                    lectureSlots.add(lectureSlot);
                    usedSlots.add(lectureSlot.getSlot() + "_" + lectureSlot.getRoomId());
                    usedSlots.add(lectureSlot.getSlot() + "_" + lectureSlot.getLecturer());

                    for (String groupId : individualGroups) {
                        slotOccupancy.get(lectureSlot.getSlot()).add(groupId);
                    }
                }
            }
        }

        System.out.println("Scheduled " + lectureSlots.size() + " lecture slots");
        return lectureSlots;
    }

    private TimetableSlot scheduleBalancedSlot(String moduleCode, String classType,
                                               TeachingAssignment assignment, String groupId,
                                               Set<String> individualGroups, Set<String> usedSlots) {
                String roomType = "lecture".equalsIgnoreCase(classType) ? "teaching" : "lab";

        List<Room> suitableRooms = findSuitableRooms(roomType, assignment.getMaxCapacity());

        for (String slot : BALANCED_SLOTS) {
            for (Room room : suitableRooms) {

                                boolean groupConflict = false;
                if (individualGroups != null) {
                    for (String individualGroup : individualGroups) {
                        if (slotOccupancy.get(slot).contains(individualGroup)) {
                            groupConflict = true;
                            break;
                        }
                    }
                } else {
                    if (slotOccupancy.get(slot).contains(groupId)) {
                        groupConflict = true;
                    }
                }

                if (groupConflict) continue;

                if (!usedSlots.contains(slot + "_" + room.getRoomId()) &&
                        !usedSlots.contains(slot + "_" + assignment.getLecturerName())) {

                    return new TimetableSlot(moduleCode, classType, groupId, assignment.getLecturerName(), room.getRoomId(), slot);
                }
            }
        }

        System.out.println("Warning: Could not schedule " + classType + " for " + moduleCode);
        return null;
    }

    private List<TimetableSlot> scheduleTutorialsAndLabs(Map<String, List<TeachingAssignment>> assignmentsByModule) {
        List<TimetableSlot> tutorialLabSlots = new ArrayList<>();

        for (CourseModule courseModule : courseModules) {
            String moduleCode = courseModule.getModuleCode();
            List<TeachingAssignment> moduleAssignments = assignmentsByModule.get(moduleCode);

            if (moduleAssignments == null) continue;

            ModuleHours hours = findModuleHours(moduleCode);
            if (hours == null) continue;

            List<StudentGroup> groupsForModule = findGroupsForModule(courseModule);
            if (groupsForModule.isEmpty()) continue;

            // Schedule tutorials
            if (hours.getTutorialHours() > 0) {
                List<TeachingAssignment> tutorialAssignments = new ArrayList<>();
                for (TeachingAssignment assignment : moduleAssignments) {
                    if ("tutorial".equalsIgnoreCase(assignment.getClassType())) {
                        tutorialAssignments.add(assignment);
                    }
                }

                if (!tutorialAssignments.isEmpty()) {
                    tutorialLabSlots.addAll(scheduleGroupSessions(
                            moduleCode, "tutorial", tutorialAssignments, groupsForModule, hours.getTutorialHours()
                    ));
                }
            }

            if (hours.getLabHours() > 0) {
                List<TeachingAssignment> labAssignments = new ArrayList<>();
                for (TeachingAssignment assignment : moduleAssignments) {
                    if ("lab".equalsIgnoreCase(assignment.getClassType())) {
                        labAssignments.add(assignment);
                    }
                }

                if (!labAssignments.isEmpty()) {
                    tutorialLabSlots.addAll(scheduleGroupSessions(
                            moduleCode, "lab", labAssignments, groupsForModule, hours.getLabHours()
                    ));
                }
            }
        }

        System.out.println("Scheduled " + tutorialLabSlots.size() + " tutorial/lab slots");
        return tutorialLabSlots;
    }

    private List<TimetableSlot> scheduleGroupSessions(String moduleCode, String classType,
                                                      List<TeachingAssignment> assignments,
                                                      List<StudentGroup> groups, int hoursNeeded) {
        List<TimetableSlot> slots = new ArrayList<>();
        Set<String> usedSlots = new HashSet<>();

        for (StudentGroup group : groups) {
            for (int i = 0; i < hoursNeeded; i++) {
                TeachingAssignment assignment = assignments.get(i % assignments.size());

                TimetableSlot classSlot = scheduleBalancedSlot(
                        moduleCode, classType, assignment, group.getGroupId(), null, usedSlots
                );

                if (classSlot != null) {
                    slots.add(classSlot);
                    usedSlots.add(classSlot.getSlot() + "_" + classSlot.getRoomId());
                    usedSlots.add(classSlot.getSlot() + "_" + classSlot.getLecturer());
                    slotOccupancy.get(classSlot.getSlot()).add(group.getGroupId());
                }
            }
        }

        return slots;
    }

    private Map<String, List<TeachingAssignment>> groupAssignmentsByModule() {
        Map<String, List<TeachingAssignment>> assignmentsByModule = new HashMap<>();
        for (TeachingAssignment assignment : teachingAssignments) {
            String moduleCode = assignment.getModuleCode();
            assignmentsByModule.computeIfAbsent(moduleCode, k -> new ArrayList<>()).add(assignment);
        }
        return assignmentsByModule;
    }

    private ModuleHours findModuleHours(String moduleCode) {
        for (ModuleHours hours : moduleHours) {
            if (hours.getModuleCode().equals(moduleCode)) {
                return hours;
            }
        }
        return null;
    }

    private List<StudentGroup> findGroupsForModule(CourseModule courseModule) {
        List<StudentGroup> groups = new ArrayList<>();
        for (StudentGroup group : studentGroups) {
            if (group.getCourse().equals(courseModule.getCourse()) &&
                    group.getYear() == courseModule.getYear()) {
                groups.add(group);
            }
        }
        return groups;
    }

    private List<Room> findSuitableRooms(String roomType, int minCapacity) {
        List<Room> suitableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.getType().equalsIgnoreCase(roomType) && room.getCapacity() >= minCapacity) {
                suitableRooms.add(room);
            }
        }
        if (suitableRooms.isEmpty()) {
            System.out.println("Warning: No suitable " + roomType + " rooms found for capacity " + minCapacity);
        }
        return suitableRooms;
    }
}