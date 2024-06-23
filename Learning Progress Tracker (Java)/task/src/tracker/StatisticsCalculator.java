package tracker;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StatisticsCalculator {

    private StudentDAO dao;

    private static StatisticsCalculator instance;

    public static StatisticsCalculator get(StudentDAO dao) {
        if (instance == null) {
            instance = new StatisticsCalculator();
        }
        instance.dao = dao;
        return instance;
    }

    public List<String> mostPopular() {
        Submission enrolled = getEnrolled(dao.findAll());
        int max = Stream.of(enrolled.java(), enrolled.dsa(), enrolled.dbs(), enrolled.spring())
                .max(Integer::compare).orElse(0);
        return toList(max, enrolled);
    }

    public List<String> leastPopular() {
        Submission enrolled = getEnrolled(dao.findAll());
        int min = Stream.of(enrolled.java(), enrolled.dsa(), enrolled.dbs(), enrolled.spring())
                .min(Integer::compare).orElse(0);
        return toList(min, enrolled).stream()
                .filter(it-> !mostPopular().contains(it))
                .toList();
    }

    public List<String> highestActivity() {
        Submission submissions = getSubmissions(dao.findAll());
        int max = Stream.of(submissions.java(), submissions.dsa(), submissions.dbs(), submissions.spring())
                .max(Integer::compare).orElse(0);
        return toList(max, submissions);
    }

    public List<String> lowestActivity() {
        Submission submissions = getSubmissions(dao.findAll());
        int min = Stream.of(submissions.java(), submissions.dsa(), submissions.dbs(), submissions.spring())
                .min(Integer::compare).orElse(0);
        return toList(min, submissions).stream()
                .filter(it -> !highestActivity().contains(it))
                .toList();
    }

    public List<String> easiestCourse() {
        Submission averages = getAverages(dao.findAll());
        Integer max = Stream.of(averages.java(), averages.dsa(), averages.dbs(), averages.spring())
                .max(Integer::compare).orElse(0);
        return toList(max, averages);
    }

    public List<String> hardestCourse() {
        Submission averages = getAverages(dao.findAll());
        Integer min = Stream.of(averages.java(), averages.dsa(), averages.dbs(), averages.spring())
                .min(Integer::compare).orElse(0);
        return toList(min, averages);
    }

    private Submission getEnrolled(Map<String, Student> all) {
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

    private Submission getSubmissions(Map<String, Student> all) {
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

    private Submission getAverages(Map<String, Student> all) {
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

    private List<String> toList(int target, Submission enrolled) {
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
        return courses;
    }
}
