import java.util.ArrayList;
import java.util.List;

public class CustomerManager {

    private final List<Customer> customers = new ArrayList<>();


    boolean addCustomer(Customer customer) {
        if (customer == null) {
            return false;
        }

        for (Customer c : customers) {
            if (c.getCustomerId() == customer.getCustomerId()) {
                System.out.println("No Duplicates ID's");

                return false;
            }
        }
        if (customers.stream().anyMatch( c -> c.getPhone().equals(customer.phone))) {
            throw new IllegalArgumentException("Phone already exists");
        }
        customers.add(customer);
        return true;

    }

    Customer getCustomerById(int id) {
        for (Customer c : customers) {
            if (c.getCustomerId() == id) {
                return c;
            }
        }
        System.out.println("Customer not found !");

        return null;
    }

    List<Customer> getAll() {
        return customers;

    }

    boolean updateCustomer(int id, Name newName, int newAge, String newPhone) {
        for (Customer c : customers) {
            if (c.getCustomerId() == id) {

                c.setName(newName);
                c.setAge(newAge);
                c.setPhone(newPhone);

                System.out.println("Customer updated successfully.");
                return true;
            }

        }

        System.out.println("Customer not found.");
        return false;

    }
}