public class Human {
    protected Name name;
    protected String phone;
    protected int age;

    public Human(Name name, String phone, int age) {
        super();
        this.name = name;
        setPhone(phone);
        setAge(age);
    }

    public Name getName() {
        return name;
    }
    public void setName(Name name) {
        this.name = name;
    }
    public String getPhone() {return phone;}
    public void setPhone(String phone) {
        if (phone == null || phone.isBlank()||phone.length()>11) {
            throw new IllegalArgumentException("Enter a valid phone number");
        }
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        if(age>90||age<18){
            throw new IllegalArgumentException("Use a Real Age");
        }else{
        this.age = age;
    }
    }



}
