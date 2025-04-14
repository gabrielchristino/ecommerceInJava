import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private Client client;
    private List<OrderedItem> items;
    private LocalDate orderDate;
    private OrderStatus status;

    public Order (int id, Client client) {
        this.id = id;
        this.client = client;
        this.items = new ArrayList<>();
        this.orderDate = LocalDate.now();
        this.status = OrderStatus.PENDING;
    }

    public void addItem (Product product, int quantity) throws InsufficientStockException {
        product.sell(quantity);

        OrderedItem orderedItem = new OrderedItem(product, quantity);
        items.add(orderedItem);
    }

    public void updateOrder() {
        this.status = OrderStatus.PROCESSING;
    }

    public int getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public Product getProduct(int index) {
        return items.get(index).getProduct();
    }

    public OrderStatus getStatus() {
        return status;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public List<OrderedItem> getItems() {
        return items;
    }
}
