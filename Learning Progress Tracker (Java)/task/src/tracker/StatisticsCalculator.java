package tracker;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StatisticsCalculator {

    public static String mostPopular(Map<String, Student> all) {
        Submission enrolled = getEnrolled(all);
        int max = Stream.of(enrolled.java(), enrolled.dsa(), enrolled.dbs(), enrolled.spring())
                .max(Integer::compare).orElse(0);
        return buildString(max, enrolled);
    }

    public static String leastPopular(Map<String, Student> all) {
        Submission enrolled = getEnrolled(all);
        int min = Stream.of(enrolled.java(), enrolled.dsa(), enrolled.dbs(), enrolled.spring())
                .min(Integer::compare).orElse(0);
        return buildString(min, enrolled);
    }

    public static String highestActivity(Map<String, Student> all) {
        Submission submissions = getSubmissions(all);
        int max = Stream.of(submissions.java(), submissions.dsa(), submissions.dbs(), submissions.spring())
                .max(Integer::compare).orElse(0);
        return buildString(max, submissions);
    }

    public static String lowestActivity(Map<String, Student> all) {
        Submission submissions = getSubmissions(all);
        int min = Stream.of(submissions.java(), submissions.dsa(), submissions.dbs(), submissions.spring())
                .min(Integer::compare).orElse(0);
        return buildString(min, submissions);
    }

    public static String easiestCourse(Map<String, Student> all) {
        Submission averages = getAverages(all);
        Integer max = Stream.of(averages.java(), averages.dsa(), averages.dbs(), averages.spring())
                .max(Integer::compare).orElse(0);
        return buildString(max, averages);
    }

    public static String hardestCourse(Map<String, Student> all) {
        Submission averages = getAverages(all);
        Integer min = Stream.of(averages.java(), averages.dsa(), averages.dbs(), averages.spring())
                .min(Integer::compare).orElse(0);
        return buildString(min, averages);
    }

    private static Submission getEnrolled(Map<String, Student> all) {
        return all.values().stream()
                .map(Student::getTotal)
                .map(ts -> {
                    //System.out.println(ts);
                    return new Submission(
                            Math.min(1, ts.java()),
                            Math.min(1, ts.dsa()),
                            Math.min(1, ts.dbs()),
                            Math.min(1, ts.spring()));
                })
                .reduce(Submission::add)
                .orElse(Submission.empty());
    }

    private static Submission getSubmissions(Map<String, Student> all) {
        return all.values().stream()
                .flatMap(student -> student.getSubmissions().stream())
                .map(ts -> {
                    //System.out.println(ts);
                    return new Submission(
                            Math.min(1, ts.java()),
                            Math.min(1, ts.dsa()),
                            Math.min(1, ts.dbs()),
                            Math.min(1, ts.spring()));
                })
                .reduce(Submission::add)
                .orElse(Submission.empty());
    }

    private static Submission getAverages(Map<String, Student> all) {
        /*DoubleSummaryStatistics javaAvg = all.values().stream()
                .map(Student::getTotal)
                .collect(Collectors.summarizingDouble(Submission::java));

        DoubleSummaryStatistics dsaAvg = all.values().stream()
                .map(Student::getTotal)
                .collect(Collectors.summarizingDouble(Submission::dsa));

        DoubleSummaryStatistics dbsAvg = all.values().stream()
                .map(Student::getTotal)
                .collect(Collectors.summarizingDouble(Submission::dbs));

        DoubleSummaryStatistics springAvg = all.values().stream()
                .map(Student::getTotal)
                .collect(Collectors.summarizingDouble(Submission::spring));*/

        DoubleSummaryStatistics javaAvg = all.values().stream()
                .flatMap(s -> s.getSubmissions().stream())
                .collect(Collectors.summarizingDouble(Submission::java));

        DoubleSummaryStatistics dsaAvg = all.values().stream()
                .flatMap(s -> s.getSubmissions().stream())
                .collect(Collectors.summarizingDouble(Submission::dsa));

        DoubleSummaryStatistics dbsAvg = all.values().stream()
                .flatMap(s -> s.getSubmissions().stream())
                .collect(Collectors.summarizingDouble(Submission::dbs));

        DoubleSummaryStatistics springAvg = all.values().stream()
                .flatMap(s -> s.getSubmissions().stream())
                .collect(Collectors.summarizingDouble(Submission::spring));

        return new Submission((int)javaAvg.getAverage(), (int)dsaAvg.getAverage(),
                (int)dbsAvg.getAverage(), (int)springAvg.getAverage());
    }

    private static String buildString(int target, Submission enrolled) {
        List<String> courses = new ArrayList<>();
        if (target == enrolled.java()) {
            courses.add("Java");
        }
        if (target == enrolled.dsa()) {
            courses.add("DSA");
        }
        if (target == enrolled.dbs()) {
            courses.add("Databases");
        }
        if (target == enrolled.spring()) {
            courses.add("Spring");
        }
        return String.join(", ", courses);
    }
}
