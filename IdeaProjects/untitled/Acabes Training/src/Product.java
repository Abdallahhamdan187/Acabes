import java.math.BigDecimal;

public class Product {

    private int productId;
    private BigDecimal price;
    private String category;
    private String description;
    private int quantity;

    public Product(int productId, BigDecimal price, String category, String description, int quantity) {
        super();
        this.productId = productId;
        setPrice(price);
        this.category = category;
        this.description = description;
        setQuantity(quantity);

    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        if(productId<0){
            throw new IllegalArgumentException("Product ID cannot be negative");
        }
        this.productId = productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if(price.doubleValue()<0){
            throw new IllegalArgumentException("Product Price cannot be negative");
        }
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if(quantity<0){
            throw new IllegalArgumentException("Insert a valid Quantity");
        }
        this.quantity = quantity;
    }

}
