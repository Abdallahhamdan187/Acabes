import java.math.BigDecimal;
import java.util.List;

import java.util.Scanner;
public class ProductUI {
    Scanner scanner = new Scanner(System.in);

    private final ProductService productManager;
    public ProductUI(ProductService productService) {
        this.productManager = productService;
    }



    void addProduct() {
      try{
        System.out.print("Enter Product ID: ");

        int productId = scanner.nextInt();

        scanner.nextLine();

        System.out.print("Enter Price: ");
        BigDecimal price = new BigDecimal(scanner.next());


        scanner.nextLine();

        System.out.print("Enter Category: ");
        String category = scanner.nextLine();

        System.out.print("Enter Description: ");
        String description = scanner.nextLine();

        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();


        Product product = new Product(productId, price, category, description, quantity);

        if (productManager.addProduct(product)) {
            System.out.println("Product added successfully.");
        } else {
            System.out.println("Failed to add Product.");
        }}catch (IllegalArgumentException e){
          System.out.println(e.getMessage());

      }
    }
    void getAllProducts() {
        List<Product> products = productManager.getAll();

        if (products.isEmpty()) {
            System.out.println("No product found.");
        } else {
            for (Product p : products) {
                System.out.println("-------------------------");
                System.out.println("Product ID: " + p.getProductId());
                System.out.println("Price: " + p.getPrice());
                System.out.println("Category: " + p.getCategory());
                System.out.println("Description: " + p.getDescription());
                System.out.println("Quantity: " + p.getQuantity());
            }
        }
    }

    void searchProduct() {
        System.out.print("Enter Product ID: ");
        int searchId = scanner.nextInt();

        Product found = productManager.getProductById(searchId);

        if (found != null) {
            System.out.println("ID: " + found.getProductId());
            System.out.println("Price: " + found.getPrice());
            System.out.println("Category: " + found.getCategory());
            System.out.println("Description: " + found.getDescription());
            System.out.println("Quantity: " + found.getQuantity());
        }
    }
    void updateProduct() {
        try{
        System.out.print("Enter Product ID to update: ");
        int updateId = scanner.nextInt();
        scanner.nextLine();
        Product product =productManager.getProductById(updateId);
            if (product==null) {return;}
        System.out.print("Enter New Price: ");
        BigDecimal newPrice = new BigDecimal(scanner.next());

        scanner.nextLine();
        System.out.print("Enter New Category: ");
        String newCategory = scanner.nextLine();

        System.out.print("Enter New Description: ");
        String newDescription = scanner.nextLine();

        System.out.print("Enter new Quantity: ");
        int newQuantity = scanner.nextInt();

        scanner.nextLine();


        if (!productManager.updateProduct(updateId, newPrice, newCategory, newDescription,
                newQuantity)) {
            System.out.println("Update failed.");
        } else {
            System.out.println("Product Updated successfully");
        }
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());

        }
    }

    void deleteProduct(){
        try{
    System.out.print("Enter Product ID to Delete: ");
    int deleteId = scanner.nextInt();
    scanner.nextLine();
    System.out.println("Are you sure you want to delete 1 for yes 0 for no: ");

    int sure = scanner.nextInt();
    scanner.nextLine();
    if (sure == 1) {
        if (productManager.deleteProduct(deleteId)) {
            System.out.print("Product deleted successfully");

        } else {
            System.out.println("Product not found!");
        }


    }}catch (IllegalArgumentException e){
            System.out.println(e.getMessage());

        }
    }
     void productUI() {
        System.out.println("\n===== PRODUCT MENU =====");
        System.out.println("1. Add Product");
        System.out.println("2. View All Products");
        System.out.println("3. Search Product By ID");
        System.out.println("4. Update Product");
        System.out.println("5. Delete Product");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");

    }

}
