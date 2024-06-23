package tracker;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
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
            System.out.println(getCourseTitle(input));
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
                )
                .filter(c -> c.points > 0)
                .sorted(Comparator.comparing(Course::points).reversed().thenComparing(Course::studentId))
                .toList();

        for (Course course : courses) {
            System.out.println("%s\t%s\t%.1f%%".formatted(course.studentId(), course.points(), course.completed()));
        }
    }

    private record Course(String name, String studentId, int points, double completed){}

    private static void printHeaders() {
        System.out.println("%s\t%s\t%s".formatted("id", "points", "completed"));
    }

    public static String getCourseTitle(String input) {
        return switch (input.toLowerCase()) {
            case "java" ->"Java";
            case "dsa" -> "DSA";
            case "databases" -> "Databases";
            case "spring" -> "Spring";
            default -> input;
        };
    }
}
