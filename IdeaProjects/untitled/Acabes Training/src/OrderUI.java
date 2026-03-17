import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Scanner;

public class OrderUI {
    Scanner scanner = new Scanner(System.in);
    private final OrderService orderManager;
    private final ProductService productManager;
    private final CustomerManager customerManager;


    public OrderUI(OrderService orderService, ProductService productService, CustomerManager customerService) {
        this.orderManager = orderService;
        this.productManager = productService;
        this.customerManager = customerService;
    }

    void addOrder() {
       try{
        System.out.println("Select Currency (JOD / USD / EUR): ");
        String currencyCode = scanner.next().toUpperCase();

        Currency selectedCurrency;
        try {
            selectedCurrency = Currency.getInstance(currencyCode);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid currency. Defaulting to JOD.");
            selectedCurrency = Currency.getInstance("JOD");
        }

        System.out.print("Enter Order ID: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();

        Customer customer = customerManager.getCustomerById(customerId);
        if (customer==null) { return;}
        System.out.println("How many products you want to enter ?");

        int numberProduct = scanner.nextInt();
        scanner.nextLine();

        List<OrderItem> items = new ArrayList<>();
        BigDecimal orderTotal = BigDecimal.ZERO;
        boolean addValidation = false;

        for (int i = 0; i < numberProduct; i++) {
            System.out.println("Enter Product " + (i + 1) + " ID :");
            int proID = scanner.nextInt();
            scanner.nextLine();

            Product product = productManager.getProductById(proID);
            if (product==null) { return;}
                System.out.println("Enter the quantity :");

            int proQuantity = scanner.nextInt();
            scanner.nextLine();

            if (product.getQuantity() >= proQuantity) {
                addValidation = true;
                BigDecimal quantityBD = BigDecimal.valueOf(proQuantity);
                BigDecimal total = product.getPrice().multiply(quantityBD);

                items.add(new OrderItem(product, proQuantity, total));
                orderTotal = orderTotal.add(total);

                product.setQuantity(product.getQuantity() - proQuantity);

            } else {
                System.out.println("Not enough product, we have " + product.getQuantity());
                addValidation = false;

            }
        }
        Money totalMoney = new Money(orderTotal, selectedCurrency);
        Order order = new Order(orderId, customer, items, LocalDateTime.now(), totalMoney);

        if (orderManager.addOrder(order) && addValidation) {

            System.out.println("Order added successfully.");

        } else {
            System.out.println("Failed to add Order.");
        }}catch (IllegalArgumentException e){
           System.out.println(e.getMessage());

       }
    }
    void getAllOrders() {
        List<Order> orders = orderManager.getAll();

        if (orders.isEmpty()) {
            System.out.println("No Orders found.");
        } else {
            for (Order o : orders) {
                System.out.println("-------------------------");
                System.out.println("Order ID: " + o.getOrderId());
                System.out.println("Customer: " + o.getCustomer().getName().toString());
                System.out.println("Items: " + o.getItem());
                System.out.println("Time: " + o.getTime());
                System.out.println("Total: " + o.getTotalAmount());
            }
        }
    }
    void searchOrder() {
        System.out.print("Enter Order ID: ");
        int searchId = scanner.nextInt();

        Order found = orderManager.getOrderId(searchId);

        if (found != null) {
            System.out.println("ID: " + found.getOrderId());
            System.out.println("Customer: " + found.getCustomer().getName().toString());
            System.out.println("Items: " + found.getItem());
            System.out.println("Time: " + found.getTime());
            System.out.println("Total: " + found.getTotalAmount());
        } else {
            System.out.println("Order not found.");
        }
    }
    void updateOrder() {
        // Update
        try {
            System.out.print("Enter Order ID to update: ");
            int updateId = scanner.nextInt();
            scanner.nextLine();

            Order orderToUpdate = orderManager.getOrderId(updateId);

            if (orderToUpdate == null) {
                System.out.println("No order found!");
                return;
            }

            System.out.print("Enter New Customer ID: ");
            int newCustomerId = scanner.nextInt();
            scanner.nextLine();

            boolean validUpdate = true;

            Customer updatedCustomer = customerManager.getCustomerById(newCustomerId);
            if(updatedCustomer==null){
                return;
            }
            System.out.println("How many products you want to enter ?");
            int numberProducts = scanner.nextInt();
            scanner.nextLine();

            for (OrderItem oldItem : orderToUpdate.getItem()) {
                Product p = oldItem.getProduct();
                p.setQuantity(p.getQuantity() + oldItem.getQuantity());
            }


            List<OrderItem> newItems = new ArrayList<>();
            BigDecimal updatedOrderTotal = BigDecimal.ZERO;



            for (int i = 0; i < numberProducts; i++) {

                System.out.println("Enter Product " + (i + 1) + " ID :");
                int proID = scanner.nextInt();
                scanner.nextLine();

                Product product = productManager.getProductById(proID);

                if (product == null) {
                    validUpdate = false;
                    break;
                }

                System.out.println("Enter the quantity :");
                int proQuantity = scanner.nextInt();
                scanner.nextLine();

                if (product.getQuantity() >= proQuantity) {

                    BigDecimal quantityBD = BigDecimal.valueOf(proQuantity);
                    BigDecimal total = product.getPrice().multiply(quantityBD);

                    newItems.add(new OrderItem(product, proQuantity, total));
                    updatedOrderTotal = updatedOrderTotal.add(total);

                    product.setQuantity(product.getQuantity() - proQuantity);
                } else {
                    System.out.println("Not enough product, we have " + product.getQuantity());
                    validUpdate = false;

                }
            }
            if (!validUpdate) {

                for (OrderItem oldItem : orderToUpdate.getItem()) {
                    Product p = oldItem.getProduct();
                    p.setQuantity(p.getQuantity() - oldItem.getQuantity());
                }

                System.out.println("Order update cancelled.");


            }

            for (OrderItem item : newItems) {
                Product p = item.getProduct();
                p.setQuantity(p.getQuantity() - item.getQuantity());
            }

            Money updatedtotalMoney = new Money(updatedOrderTotal,
                    orderToUpdate.getTotalAmount().getCurrency());

            if (!orderManager.updateOrder(updateId, updatedCustomer, newItems, LocalDateTime.now(),
                    updatedtotalMoney)) {
                System.out.println("Update failed");
            } else {
                System.out.println("Order updated successfully");
            }
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());

        }
    }
    void deleteOrder() {
        try{
        System.out.print("Enter Order ID to Delete: ");
        int deleteId = scanner.nextInt();
        System.out.print("Are you sure you want to delete 1 for yes 0 for no: ");

        int sure = scanner.nextInt();
        if (sure == 1) {
            orderManager.deleteOrder(deleteId);
        }}catch (IllegalArgumentException e){
            System.out.println(e.getMessage());

        }
    }
     void orderUI() {
        System.out.println("\n===== ORDER MENU =====");
        System.out.println("1. Add Order");
        System.out.println("2. View All Order");
        System.out.println("3. Search Order By ID");
        System.out.println("4. Update Order");
        System.out.println("5. Delete Order");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");

    }

}
