package Java8;

public class Person {
    private String name;
    private  Gender gender;

    enum Gender{
        Male,Female
    }

    public Person(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                '}';
    }
}
