public class DummyShippingService implements ShippingService{
    @Override
    public  double calculateShippingCost(Order order) {
        double total = order.getItems().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();

        return total < 100 ? 10 : 0;
    }
}
