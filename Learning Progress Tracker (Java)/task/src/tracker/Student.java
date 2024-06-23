package tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Student {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private List<Submission> submissions = new ArrayList<>();

    public static Student fromString(String input) {
        Student student = new Student();
        student.id = UUID.randomUUID().toString();
        student.firstName = input.substring(0, input.indexOf(" "));
        student.lastName = input.substring(input.indexOf(" ") + 1, input.lastIndexOf(" "));
        student.email = input.substring(input.lastIndexOf(" ") + 1);
        return student;
    }

    public static StudentValidator getValidator() {
        return new StudentValidator();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void addSubmission(Submission submission) {
        submissions.add(submission);
    }

    public List<Submission> getSubmissions() {
        return submissions;
    }

    public Submission getTotal() {
        return submissions.stream()
                .reduce(Submission::add)
                .orElse(Submission.empty());
    }

    public int getPoints(String input) {
        Submission total = getTotal();
        return switch (input.toLowerCase()) {
            case "java" -> total.java();
            case "dsa" -> total.dsa();
            case "databases" -> total.dbs();
            case "spring" -> total.spring();
            default -> 0;
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
