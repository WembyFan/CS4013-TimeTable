package Logic;

import Model.*;
import java.util.*;

/**
 * Generates university timetables by scheduling classes into time slots and rooms.
 * Ensures no scheduling conflicts - rooms, lecturers, and student groups are never double-booked.
 * Creates a balanced schedule that distributes classes throughout the week.
 */
public class TimetableManager {
    /** All course modules offered by the university */
    private List<CourseModule> courseModules;

    /** Required hours for each module (lectures, tutorials, labs) */
    private List<ModuleHours> moduleHours;

    /** Lecturer assignments to specific modules and class types */
    private List<TeachingAssignment> teachingAssignments;

    /** Student enrollment counts per module */
    private List<ModuleEnrollment> enrollments;

    /** Organized student groups by course, year, and group */
    private List<StudentGroup> studentGroups;

    /** Available teaching spaces and laboratories */
    private List<Room> rooms;

    /**
     * Time slots organized to distribute classes across the week.
     * Prioritizes 10am-3pm time slots, then fills early and late hours.
     * Format: A1=Monday 9am, B1=Tuesday 9am, A2=Monday 10am, etc.
     */
    private final String[] BALANCED_SLOTS = {
            // 10am-3pm time slots across all weekdays
            "A2", "B2", "C2", "D2", "E2",  // 10:00-11:00
            "A3", "B3", "C3", "D3", "E3",  // 11:00-12:00
            "A4", "B4", "C4", "D4", "E4",  // 12:00-13:00
            "A5", "B5", "C5", "D5", "E5",  // 13:00-14:00
            "A6", "B6", "C6", "D6", "E6",  // 14:00-15:00
            // Early morning and late afternoon slots
            "A1", "B1", "C1", "D1", "E1",  // 09:00-10:00
            "A7", "B7", "C7", "D7", "E7",  // 15:00-16:00
            "A8", "B8", "C8", "D8", "E8",  // 16:00-17:00
            "A9", "B9", "C9", "D9", "E9"   // 17:00-18:00
    };

    /** Tracks which student groups are scheduled in each time slot */
    private Map<String, Set<String>> slotOccupancy;

    /**
     * Creates a timetable manager with university data.
     */
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

        // Initialize all time slots with empty occupancy
        for (String slot : BALANCED_SLOTS) {
            slotOccupancy.put(slot, new HashSet<>());
        }
    }

    /**
     * Generates a complete timetable with all scheduled classes.
     * @return list of timetable slots representing the full schedule
     */
    public List<TimetableSlot> makeTimetable() {
        System.out.println("Starting timetable generation...");
        List<TimetableSlot> timetable = new ArrayList<>();

        Map<String, List<TeachingAssignment>> assignmentsByModule = groupAssignmentsByModule();

        timetable.addAll(scheduleLectures(assignmentsByModule));
        timetable.addAll(scheduleTutorialsAndLabs(assignmentsByModule));

        System.out.println("Timetable generation completed. Generated " + timetable.size() + " slots.");
        return timetable;
    }

    /**
     * Schedules all lecture sessions.
     * Lectures are scheduled for entire year groups using shared group IDs like "ALL_CS_Y1".
     */
    private List<TimetableSlot> scheduleLectures(Map<String, List<TeachingAssignment>> assignmentsByModule) {
        List<TimetableSlot> lectureSlots = new ArrayList<>();
        Set<String> usedSlots = new HashSet<>(); // Tracks occupied rooms and lecturers

        for (CourseModule courseModule : courseModules) {
            String moduleCode = courseModule.getModuleCode();
            List<TeachingAssignment> moduleAssignments = assignmentsByModule.get(moduleCode);

            if (moduleAssignments == null) continue;

            // Find lecture assignment for this module
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

            // Create shared group for year-wide lectures
            String sharedGroupId = "ALL_" + courseModule.getCourse() + "_Y" + courseModule.getYear();

            Set<String> individualGroups = new HashSet<>();
            for (StudentGroup group : groupsForModule) {
                individualGroups.add(group.getGroupId());
            }

            // Schedule required lecture hours
            for (int i = 0; i < hours.getLectureHours(); i++) {
                TimetableSlot lectureSlot = scheduleBalancedSlot(
                        moduleCode, "lecture", lectureAssignment, sharedGroupId, individualGroups, usedSlots
                );

                if (lectureSlot != null) {
                    lectureSlots.add(lectureSlot);
                    usedSlots.add(lectureSlot.getSlot() + "_" + lectureSlot.getRoomId());
                    usedSlots.add(lectureSlot.getSlot() + "_" + lectureSlot.getLecturer());

                    // Mark all attending groups as occupied
                    for (String groupId : individualGroups) {
                        slotOccupancy.get(lectureSlot.getSlot()).add(groupId);
                    }
                }
            }
        }

        System.out.println("Scheduled " + lectureSlots.size() + " lecture slots");
        return lectureSlots;
    }

    /**
     * Finds an available time slot and room for a class session using random selection.
     * Shuffles both rooms and time slots to distribute usage evenly across resources.
     *
     * @param moduleCode the module to schedule
     * @param classType lecture, tutorial, or lab
     * @param assignment the teaching assignment with room type requirements
     * @param groupId student group or shared lecture group
     * @param individualGroups for lectures: all attending groups. For tutorials/labs: null
     * @param usedSlots tracks occupied rooms and lecturers
     * @return scheduled slot or null if no availability after checking all combinations
     */
    private TimetableSlot scheduleBalancedSlot(String moduleCode, String classType,
                                               TeachingAssignment assignment, String groupId,
                                               Set<String> individualGroups, Set<String> usedSlots) {
        // Get suitable rooms and shuffle them
        List<Room> suitableRooms = findSuitableRooms(assignment.getRoomType(), assignment.getMaxCapacity());
        if (suitableRooms.isEmpty()) {
            System.out.println("    No suitable " + assignment.getRoomType() + " rooms found for " + moduleCode);
            return null;
        }
        Collections.shuffle(suitableRooms);

        // Shuffle the slots for random time selection
        List<String> shuffledSlots = new ArrayList<>(Arrays.asList(BALANCED_SLOTS));
        Collections.shuffle(shuffledSlots);

        for (String slot : shuffledSlots) {
            for (Room room : suitableRooms) {
                // Check if room is already booked for this slot
                if (usedSlots.contains(slot + "_" + room.getRoomId())) continue;

                // Check if lecturer is already booked for this slot
                if (usedSlots.contains(slot + "_" + assignment.getLecturerName())) continue;

                // Check group conflicts
                boolean groupConflict = false;
                if (individualGroups != null) {
                    // For shared lectures, check all individual groups
                    for (String individualGroup : individualGroups) {
                        if (slotOccupancy.get(slot).contains(individualGroup)) {
                            groupConflict = true;
                            break;
                        }
                    }
                } else {
                    // For regular classes, check the single group
                    if (slotOccupancy.get(slot).contains(groupId)) {
                        groupConflict = true;
                    }
                }

                if (groupConflict) continue;

                // All checks passed - schedule this slot
                return new TimetableSlot(
                        moduleCode,
                        classType,
                        groupId,
                        assignment.getLecturerName(),
                        room.getRoomId(),
                        slot
                );
            }
        }

        // Failed to find a suitable slot
        return null;
    }

    /**
     * Schedules all tutorial and lab sessions.
     */
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

            // Schedule labs
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

    /**
     * Schedules multiple sessions of the same type for different student groups.
     */
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

    /**
     * Groups teaching assignments by module code.
     */
    private Map<String, List<TeachingAssignment>> groupAssignmentsByModule() {
        Map<String, List<TeachingAssignment>> assignmentsByModule = new HashMap<>();
        for (TeachingAssignment assignment : teachingAssignments) {
            String moduleCode = assignment.getModuleCode();
            assignmentsByModule.computeIfAbsent(moduleCode, k -> new ArrayList<>()).add(assignment);
        }
        return assignmentsByModule;
    }

    /**
     * Finds hour requirements for a module.
     */
    private ModuleHours findModuleHours(String moduleCode) {
        for (ModuleHours hours : moduleHours) {
            if (hours.getModuleCode().equals(moduleCode)) {
                return hours;
            }
        }
        return null;
    }

    /**
     * Finds student groups that take a specific module.
     */
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

    /**
     * Finds rooms matching type and capacity requirements.
     */
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