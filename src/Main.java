import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    static ArrayList<Product> products = new ArrayList<>();
    static ArrayList<Client> clients = new ArrayList<>();
    static ArrayList<Order> orders = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("Welcome to the Order Management System!");
            System.out.println("Please select an option:");
            System.out.println("1. Add Product");
            System.out.println("2. Add Client");
            System.out.println("3. Create Order");
            System.out.println("4. Process Order");
            System.out.println("5. List Orders");
            System.out.println("6. Exit");

            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Adding a new Product");

                    int id = products.size() + 1;

                    System.out.println("Enter Product Name:");
                    String name = scanner.next();

                    System.out.println("Enter Product Price:");
                    double price = scanner.nextDouble();

                    System.out.println("Enter Product quantity:");
                    int quantity = scanner.nextInt();

                    products.add(new Product(id, name, price, quantity));
                    System.out.println("Product added successfully!");

                    break;
                case 2:
                    System.out.println("Adding a new Client");

                    int clientId = clients.size() + 1;

                    System.out.println("Enter Client Name:");
                    String clientName = scanner.next();

                    System.out.println("Enter Client Address:");
                    String address = scanner.next();

                    clients.add(new Client(clientId, clientName, address));
                    System.out.println("Client added successfully!");

                    break;
                case 3:
                    System.out.println("Creating a new Order");

                    System.out.println("Enter Client ID:");
                    int orderClientId = scanner.nextInt();

                    Client orderClient = clients.stream()
                            .filter(c -> c.getId() == orderClientId)
                            .findFirst()
                            .orElse(null);
                    if (orderClient != null) {
                        int orderId = orders.size() + 1;
                        Order order = new Order(orderId, orderClient);
                        int productId;

                        do {
                            System.out.println("Enter Product ID:");
                            productId = scanner.nextInt();
                            int finalProductId = productId;
                            Product product = products.stream()
                                    .filter(p -> p.getId() == finalProductId)
                                    .findFirst()
                                    .orElse(null);

                            if (product != null) {
                                System.out.println("Enter quantity:");
                                int orderQuantity = scanner.nextInt();
                                try {
                                    order.addItem(product, orderQuantity);
                                } catch (InsufficientStockException e) {
                                    System.out.println("Insufficient stock for the product.");
                                }
                            } else {
                                System.out.println("Product not found.");
                            }
                        } while (productId != 0);

                        orders.add(order);
                        System.out.println("Order created successfully!");
                    } else {
                        System.out.println("Client not found.");
                    }
                    break;
                case 4:
                    System.out.println("Processing an Order");

                    System.out.println("Enter Order ID:");
                    int orderId = scanner.nextInt();

                    Order orderToProcess = orders.stream()
                            .filter(o -> o.getId() == orderId)
                            .findFirst()
                            .orElse(null);
                    if (orderToProcess != null) {
                        orderToProcess.updateOrder();
                        System.out.println("Order processed successfully!");
                    } else {
                        System.out.println("Order not found.");
                    }
                    break;
                case 5:
                    System.out.println("Listing all Orders");
                    for (Order order : orders) {
                        System.out.println("Order ID: " + order.getId());
                        System.out.println("Client: " + order.getClient().getName());
                        System.out.println("Status: " + order.getStatus());
                        System.out.println("Order Date: " + order.getOrderDate());
                        System.out.println("Items:");
                        for (OrderedItem item : order.getItems()) {
                            System.out.println("- " + item.getProduct().getName() + ": " + item.getQuantity());
                        }
                        System.out.println("Shipping Cost: " + order.shippingCost(new DummyShippingService()));
                    }
                    break;
                case 6:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (option != 6);

    scanner.close();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public void updateProduct(Product product) {
        // Update product logic
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public void removeClient(Client client) {
        clients.remove(client);
    }

    public void updateClient(Client client) {
        // Update client logic
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void listOrders() {
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    public void filterOrdersByStatus() {
        List<Order> pendingOrders = orders.stream()
                .filter(order -> order.getStatus() == OrderStatus.PENDING)
                .collect(Collectors.toList());
    }

    public void filterOrdersByDate(LocalDate date) {
        List<Order> pendingOrders = orders.stream()
                .filter(order -> order.getOrderDate().equals(date))
                .collect(Collectors.toList());
    }


}