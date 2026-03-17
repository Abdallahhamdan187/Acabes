
public class Name {

    private String firstName;
    private String middleName;
    private String lastName;

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }



    @Override
    public String toString() {
        return firstName +" "+ middleName +" "+ lastName ;
    }

}
