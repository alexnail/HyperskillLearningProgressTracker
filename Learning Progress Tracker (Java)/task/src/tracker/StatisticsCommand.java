package tracker;

import java.util.List;
import java.util.Scanner;

import static tracker.Statistics.*;

public class StatisticsCommand implements Command {


    private final Scanner scanner;

    private final StudentDAO dao = InMemoryStudentDAO.dao();

    public StatisticsCommand(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public boolean execute() {
        System.out.println("Type the name of a course to see details or 'back' to quit:");
        printStatistics();

        String input = scanner.nextLine();
        while (!input.equalsIgnoreCase("back")) {
            printCourseDetails(input);

            input = scanner.nextLine();
        }
        return false;
    }

    private void printCourseDetails(String input) {
        CourseDetailsPrinter.print(input);
    }

    private void printStatistics() {
        System.out.printf("Most popular: %s%n", statistics(MOST_POPULAR));
        System.out.printf("Least popular: %s%n", statistics(LEAST_POPULAR));
        System.out.printf("Highest activity: %s%n", statistics(HIGHEST_ACTIVITY));
        System.out.printf("Lowest activity: %s%n", statistics(LOWEST_ACTIVITY));
        System.out.printf("Easiest course: %s%n", statistics(EASIEST_COURSE));
        System.out.printf("Hardest course: %s%n", statistics(HARDEST_COURSE));
    }

    private String statistics(Statistics statistics) {
        if (dao.size() == 0) {
            return "n/a";
        }
        StatisticsCalculator calculator = StatisticsCalculator.get(dao);
        List<String> list = switch (statistics) {
            case MOST_POPULAR -> calculator.mostPopular();
            case LEAST_POPULAR -> calculator.leastPopular();
            case HIGHEST_ACTIVITY -> calculator.highestActivity();
            case LOWEST_ACTIVITY -> calculator.lowestActivity();
            case EASIEST_COURSE -> calculator.easiestCourse();
            case HARDEST_COURSE -> calculator.hardestCourse();
        };
        return join(list);
    }
    private String join(List<String> list) {
        return list.isEmpty() ? "n/a" : String.join(", ", list);
    }
}

enum Statistics {
    MOST_POPULAR,
    LEAST_POPULAR,
    HIGHEST_ACTIVITY,
    LOWEST_ACTIVITY,
    EASIEST_COURSE,
    HARDEST_COURSE
}
