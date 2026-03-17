import java.util.Scanner;

public class Manager {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        OrderService orderService = new OrderService();
        ProductService productService = new ProductService();
        CustomerManager customerService = new CustomerManager();

        OrderUI orderManager = new OrderUI(orderService, productService, customerService);
        ProductUI productManager = new ProductUI(productService);
        CustomerUI customerManager = new CustomerUI(customerService);

        int table;
        int operation;
        
        while (true) {
            tables();
            table = scanner.nextInt();

            switch (table) {

                case 1:
                    while (true) {
                        customerManager.customerUI();

                        operation = scanner.nextInt();
                        switch (operation) {

                            case 1:
                                try{
                                customerManager.AddCustomer();
                                }
                                catch (IllegalArgumentException e){
                                   System.out.println(e.getMessage());
                                }
                                break;
                            case 2:
                                customerManager.getAllCustomer();
                                break;
                            case 3:
                                customerManager.searchCustomer();
                                break;
                            case 4:
                                customerManager.updateCustomer();
                                break;
                            case 5:
                                System.out.println("Exiting Customer Menu...");
                                break;
                            default:
                                System.out.println("Invalid choice.");
                                break;

                        }
                        if (operation == 5) {
                            break;
                        }

                    }
                    break;

                case 2:
                    while (true) {
                        productManager.productUI();
                        operation = scanner.nextInt();

                        switch (operation) {
                            case 1:
                                productManager.addProduct();
                                break;
                            case 2:
                                productManager.getAllProducts();
                                break;
                            case 3:
                                productManager.searchProduct();
                                break;
                            case 4:
                                productManager.updateProduct();
                                break;
                            case 5:
                                productManager.deleteProduct();
                                break;
                            case 6:
                                System.out.println("Exiting Product menu...");
                                break;

                            default:
                                System.out.println("Invalid choice.");
                                break;

                        }
                        if (operation == 6) {
                            break;
                        }
                    }
                    break;
                case 3:

                    while (true) {
                        orderManager.orderUI();
                        operation = scanner.nextInt();

                        switch (operation) {
                            case 1:
                            orderManager.addOrder();
                                break;
                            case 2:
                                orderManager.getAllOrders();
                                break;
                            case 3:
                                orderManager.searchOrder();
                                break;
                            case 4:
                                orderManager.updateOrder();
                                break;
                            case 5:
                                orderManager.deleteOrder();
                                break;
                            case 6:
                                System.out.println("Exiting Order menu...");
                                break;

                            default:
                                System.out.println("Invalid choice.");
                                break;

                        }
                        if (operation == 6) {
                            break;
                        }
                    }
                    break;

            }
        }

    }

    static void tables() {
        System.out.println("===== MANAGER MENU =====");
        System.out.println("1. Manage Customer");
        System.out.println("2. Manage Product");
        System.out.println("3. Manage Order");
        System.out.print("Enter your choice: ");
    }
}
