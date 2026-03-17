import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class ProductService {

    private final List <Product> products =new ArrayList<>();

    boolean addProduct(Product product) {
        if(product==null) {
            return false;
        }
        for(Product p:products) {
            if(p.getProductId()==product.getProductId()) {
                System.out.println("No Duplicate ID's");
                return false;
            }
        }
        products.add(product);
        return true;
    }

    Product getProductById(int id) {
        for(Product p :products) {
            if(p.getProductId()==id) {
                return p;
            }
        }
        System.out.println("Product not found!");
        return null;
    }

    List<Product> getAll() {
        return products;
    }


    public boolean deleteProduct(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId() == id) {
                products.remove(i);
                return true;
            }
        }
        return false;
    }
    //update here
    boolean updateProduct(int id,BigDecimal newPrice,String newCategory,String newDescription, int newQuantity) {

        for (Product p : products) {
            if(p.getProductId()==id) {
                p.setProductId(id);
                p.setPrice(newPrice);
                p.setCategory(newCategory);
                p.setDescription(newDescription);
                p.setQuantity(newQuantity);
                System.out.println("Product updated successfully.");
                return true;

            }

        }
        System.out.println("Product not found.");
        return false;
    }


}
