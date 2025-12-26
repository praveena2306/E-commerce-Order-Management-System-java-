import java.util.*;

public class Order {
    private int orderId;
    private String customerName;
    private List<Product> products;
    private String status;

    public Order(int orderId, String customerName) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.products = new ArrayList<>();
        this.status = "Pending";
    }

    public void addProduct(Product p) {
        products.add(p);
    }

    public int getOrderId() { return orderId; }
    public void setStatus(String status) { this.status = status; }

    public double getTotal() {
        double total = 0;
        for (Product p : products) total += p.getPrice();
        return total;
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId +
               ", Customer: " + customerName +
               ", Products: " + products +
               ", Total: ₹" + getTotal() +
               ", Status: " + status;
    }
}
