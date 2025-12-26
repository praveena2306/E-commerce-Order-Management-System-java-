import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static List<Product> products = new ArrayList<>();
    static List<Order> orders = new ArrayList<>();
    static int orderIdCounter = 1;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- E-Commerce Order Management ---");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Create Order");
            System.out.println("4. View Orders");
            System.out.println("5. Update Order Status");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: addProduct(); break;
                case 2: viewProducts(); break;
                case 3: createOrder(); break;
                case 4: viewOrders(); break;
                case 5: updateOrderStatus(); break;
                case 6: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid choice. Try again.");
            }
        }
    }

    static void addProduct() {
        System.out.print("Enter product name: ");
        String name = sc.nextLine();
        System.out.print("Enter price: ");
        double price = sc.nextDouble();
        sc.nextLine();
        products.add(new Product(name, price));
        System.out.println("Product added successfully!");
    }

    static void viewProducts() {
        System.out.println("\n--- Product List ---");
        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i));
        }
    }

    static void createOrder() {
        if (products.isEmpty()) {
            System.out.println("No products available to order.");
            return;
        }
        System.out.print("Enter customer name: ");
        String customerName = sc.nextLine();
        Order order = new Order(orderIdCounter++, customerName);

        while (true) {
            viewProducts();
            System.out.print("Choose product number to add (0 to finish): ");
            int prodChoice = sc.nextInt();
            sc.nextLine();
            if (prodChoice == 0) break;
            if (prodChoice < 1 || prodChoice > products.size()) {
                System.out.println("Invalid product number.");
            } else {
                order.addProduct(products.get(prodChoice - 1));
                System.out.println("Product added to order.");
            }
        }

        orders.add(order);
        System.out.println("Order created successfully!");
    }

    static void viewOrders() {
        System.out.println("\n--- Orders List ---");
        if (orders.isEmpty()) {
            System.out.println("No orders placed yet.");
            return;
        }
        for (Order o : orders) {
            System.out.println(o);
        }
    }

    static void updateOrderStatus() {
        System.out.print("Enter Order ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        boolean found = false;
        for (Order o : orders) {
            if (o.getOrderId() == id) {
                System.out.print("Enter new status (Pending/Shipped/Delivered): ");
                String status = sc.nextLine();
                o.setStatus(status);
                System.out.println("Order status updated!");
                found = true;
                break;
            }
        }
        if (!found) System.out.println("Order ID not found.");
    }
}
