import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class Order {

    private int orderId;
    private Customer customer;
    private List <OrderItem> item =new ArrayList<>();
    private LocalDateTime time;
    private Money totalAmount;//currency

    public Order(int orderId, Customer customer, List<OrderItem> item, LocalDateTime time, Money totalAmount) {
        setOrderId(orderId);
        this.customer = customer;
        this.item = item;
        this.time = time;
        this.totalAmount = totalAmount;
    }

    public int getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getItem() {
        return item;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Money getTotalAmount() {
        return totalAmount;
    }

    public void setOrderId(int orderId) {
        if(orderId<0){
            throw new IllegalArgumentException("Order id must be positive");
        }
          this.orderId = orderId;

    }

    public void setCustomer(Customer customer) {
        if(customer.getCustomerId()<0){
            throw new IllegalArgumentException("Customer id must be positive");
        }
        this.customer = customer;
    }

    public void setItem(List<OrderItem> item) {
        if(item==null){
            throw new IllegalArgumentException("You must insert an item");
        }
        this.item = item;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void setTotalAmount(Money totalAmount) {

        this.totalAmount = totalAmount;
    }
}
