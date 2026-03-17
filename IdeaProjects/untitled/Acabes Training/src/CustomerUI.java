import java.util.List;

import java.util.Scanner;

public class CustomerUI {
    Scanner scanner = new Scanner(System.in);

    private final CustomerManager customerManager;
    public CustomerUI(CustomerManager customerService) {
        this.customerManager=customerService;
    }

    public void AddCustomer() {
        try{
        System.out.print("Enter Customer ID: ");

        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter Middle Name: ");
        String middleName = scanner.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();

        Name name = new Name();
        name.setFirstName(firstName);
        name.setMiddleName(middleName);
        name.setLastName(lastName);

        Customer customer = new Customer(id, name, phone, age);

        if (customerManager.addCustomer(customer)) {
            System.out.println("Customer added successfully.");
        } else {
            System.out.println("Failed to add customer.");
        }
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());

        }
    }

    public void getAllCustomer() {
        List<Customer> customers = customerManager.getAll();

    if(customers.isEmpty())
    {
        System.out.println("No customers found.");

    } else

    {
        for (Customer c : customers) {
            System.out.println("-------------------------");
            System.out.println("ID: " + c.getCustomerId());
            System.out.println("Name: " + c.getName().getFirstName() + " "
                    + c.getName().getMiddleName() + " " + c.getName().getLastName());
            System.out.println("Age: " + c.getAge());
            System.out.println("Phone: " + c.getPhone());
        }
    }
}

    public void searchCustomer() {
    System.out.print("Enter Customer ID: ");
    int searchId = scanner.nextInt();
    scanner.nextLine();
    Customer found = customerManager.getCustomerById(searchId);

    if (found != null) {
        System.out.println("Customer Found:");
        System.out.println(
                "Name: " + found.getName().getFirstName() + " " + found.getName().getLastName());
        System.out.println("Age: " + found.getAge());
        System.out.println("Phone: " + found.getPhone());
    }

}
public void updateCustomer(){
    // Update
    try {
        System.out.print("Enter Customer ID to update: ");
        int updateId = scanner.nextInt();
        scanner.nextLine();
        Customer customer=customerManager.getCustomerById(updateId);
        if (customer==null){return;}
        System.out.print("Enter New First Name: ");
        String newFirstName = scanner.nextLine();

        System.out.print("Enter New Middle Name: ");
        String newMiddleName = scanner.nextLine();

        System.out.print("Enter New Last Name: ");
        String newLastName = scanner.nextLine();

        System.out.print("Enter New Age: ");
        int newAge = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter New Phone: ");
        String newPhone = scanner.nextLine();

        Name newName = new Name();
        newName.setFirstName(newFirstName);
        newName.setMiddleName(newMiddleName);
        newName.setLastName(newLastName);
        if (!customerManager.updateCustomer(updateId, newName, newAge, newPhone)) {
            System.out.println("Update failed.");
        }

    } catch (IllegalArgumentException e){
        System.out.println(e.getMessage());

    }
}
     void customerUI() {

        System.out.println("\n===== CUSTOMER MENU =====");
        System.out.println("1. Add Customer");
        System.out.println("2. View All Customers");
        System.out.println("3. Search Customer By ID");
        System.out.println("4. Update Customer");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");

    }
}

