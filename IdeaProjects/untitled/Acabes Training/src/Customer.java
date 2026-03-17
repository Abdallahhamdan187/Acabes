
public class Customer extends Human  {

    private int customerId;

    public Customer(int customerId, Name name, String phone, int age) {
        super(name, phone, age);
        setCustomerId(customerId);
    }

    public void setCustomerId(int customerId) {
        if(customerId<0){
            throw new IllegalArgumentException("Customer ID cannot be negative");
        }else {
            this.customerId = customerId;
        }
    }


    public int getCustomerId() {
        return customerId;
    }
}
