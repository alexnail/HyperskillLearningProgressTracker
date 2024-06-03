package tracker;

public class Student {
    private String firstName;
    private String lastName;
    private String email;

    public static Student fromString(String input) {
        Student student = new Student();
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
}
