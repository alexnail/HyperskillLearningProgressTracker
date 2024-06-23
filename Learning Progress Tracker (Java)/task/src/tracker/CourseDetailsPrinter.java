package tracker;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

public class CourseDetailsPrinter {
    private final static List<String> COURSES = List.of("java", "dsa", "databases", "spring");
    private final static List<Integer> COMPLETION_POINTS = List.of(600, 400, 480, 550);

    private final static StudentDAO dao = InMemoryStudentDAO.dao();

    public static void print(String input) {
        if (!COURSES.contains(input.toLowerCase())) {
            System.out.println("Unknown course.");
        } else {
            printCourseTitle(input);
            printHeaders();
            printTopStudentDetails(input);
        }
    }

    private static void printTopStudentDetails(String input) {
        Map<String, Student> all = dao.findAll();
        if (all.isEmpty()) {
            return;
        }
        int completionPoints = COMPLETION_POINTS.get(COURSES.indexOf(input.toLowerCase()));
        List<Course> courses = all.values().stream()
                .map(s -> {
                            int points = s.getPoints(input);
                            BigDecimal completed = new BigDecimal((double)points / completionPoints)
                                    .setScale(3, RoundingMode.HALF_UP)
                                    .scaleByPowerOfTen(2);
                            return new Course(input, s.getId(), points, completed.doubleValue());
                        }
                ).toList();

        for (Course course : courses) {
            System.out.println("%s\t%s\t%.2f%%".formatted(course.studentId(), course.points(), course.completed()));
        }
    }

    private record Course(String name, String studentId, int points, double completed){}

    private static void printHeaders() {
        System.out.println("%s\t%s\t%s".formatted("id", "points", "completed"));
    }

    private static void printCourseTitle(String input) {
        switch (input.toLowerCase()) {
            case "java" -> System.out.println("Java");
            case "dsa" -> System.out.println("DSA");
            case "databases" -> System.out.println("Databases");
            case "spring" -> System.out.println("Spring");
        }
    }
}
