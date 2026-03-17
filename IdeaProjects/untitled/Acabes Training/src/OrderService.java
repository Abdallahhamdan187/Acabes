import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private final List<Order> orders = new ArrayList<>();

    List<Order> getAll() {
        return orders;
    }

    Order getOrderId(int id) {

        for (Order o : orders) {
            if (o.getOrderId() == id) {

                return o;
            }
        }
        return null;

    }



    boolean addOrder(Order order) {
        if (order == null) {
            System.out.println("There is no orders");
            return false;
        }
        for (Order o : orders) {
            if (o.getOrderId() == order.getOrderId()) {
                System.out.println("No Duplicates ID Allowed For Orders");
                return false;
            }

        }
        orders.add(order);
        return true;
    }

    boolean updateOrder(int id, Customer newCustomer, List<OrderItem> newItem, LocalDateTime newTime,
                        Money newTotalAmount) {

        for (Order o : orders) {
            if (o.getOrderId() == id) {

                o.setCustomer(newCustomer);
                o.setItem(newItem);
                o.setTime(newTime);
                o.setTotalAmount(newTotalAmount);

                return true;

            }

        }
        System.out.println("Order not found!");
        return false;
    }

    void deleteOrder(int id) {

        Order order = getOrderId(id);
        if (order == null) {
            System.out.println("Order Not found!");
        }
        orders.remove(order);
        System.out.println("Order deleted successfully");
    }

}
