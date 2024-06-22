package tracker;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Scanner;

import static tracker.Statistics.*;

public class StatisticsCommand implements Command {
    private final static String[] COURSES = {"java", "dsa", "databases", "spring"};

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
            if (!Arrays.asList(COURSES).contains(input.toLowerCase())) {
                System.out.println("Unknown course.");
            }
            printCourseDetails(input);

            input = scanner.nextLine();
        }
        return false;
    }

    private void printCourseDetails(String input) {

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
        return switch (statistics) {
            case MOST_POPULAR -> StatisticsCalculator.mostPopular(dao.findAll());
            case LEAST_POPULAR -> StatisticsCalculator.leastPopular(dao.findAll());
            case HIGHEST_ACTIVITY -> StatisticsCalculator.highestActivity(dao.findAll());
            case LOWEST_ACTIVITY -> StatisticsCalculator.lowestActivity(dao.findAll());
            case EASIEST_COURSE -> StatisticsCalculator.easiestCourse(dao.findAll());
            case HARDEST_COURSE -> StatisticsCalculator.hardestCourse(dao.findAll());
        };
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
