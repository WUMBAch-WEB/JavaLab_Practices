package models;

public class Teacher {
    private String firstName;
    private String lastName;
    private String experience;
    private Long id;

    public Teacher(Long id, String firstName, String lastName, String experience) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.experience = experience;
        this.id = id;
    }

    public Teacher(String firstName, String lastName, String experience) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.experience = experience;
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

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Teacher{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", experience='" + experience + '\'' +
                ", id=" + id +
                '}';
    }
}
