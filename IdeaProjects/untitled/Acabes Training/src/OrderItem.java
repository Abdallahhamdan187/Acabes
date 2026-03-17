import java.math.BigDecimal;

public class OrderItem {

    private Product product;
    private int quantity;
    private BigDecimal totalAmount;


    public OrderItem(Product product, int quantity, BigDecimal totalAmount) {
        super();
        setProduct(product);
        setQuantity(quantity);
        setTotalAmount(totalAmount);
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setProduct(Product product) {
        if(product==null){
            throw new IllegalArgumentException("Products must no be empty");
        }
        this.product = product;
    }

    public void setQuantity(int quantity) {
        if(quantity<0){
            throw new IllegalArgumentException("You must use a valid Quantity");
        }
        this.quantity = quantity;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return product.getCategory() + " | Quantity: " + quantity + " | Total: " + totalAmount;
    }




}
