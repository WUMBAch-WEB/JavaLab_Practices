package models;

public class Student {
    private String firstName;
    private String lastName;
    private String groupNumber;
    private Long id;

    public Student(Long id, String firstName, String lastName, String groupNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.groupNumber = groupNumber;
        this.id = id;
    }

    public Student(String firstName, String lastName, String groupNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.groupNumber = groupNumber;
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

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", groupNumber='" + groupNumber + '\'' +
                ", id=" + id +
                '}';
    }
}
