import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

// ---------------------- Product Class ----------------------
class Product {
    private String productId;
    private String name;
    private double price;
    private int quantity;

    public Product(String productId, String name, double price, int quantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters
    public String getProductId() { return productId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    // Setters
    public void setPrice(double price) {
        if (price >= 0) this.price = price;
    }

    public void setQuantity(int quantity) {
        if (quantity >= 0) this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Name: %s | Price: %.2f | Quantity: %d",
                productId, name, price, quantity);
    }
}

// ---------------------- Inventory Class ----------------------
public class Inventory {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        for (Product p : products) {
            if (p.getProductId().equals(product.getProductId())) {
                System.out.println("Error: Product with this ID already exists.");
                return;
            }
        }
        products.add(product);
        System.out.println("Product added successfully.");
    }

    public boolean removeProduct(String productId) {
        for (Product p : products) {
            if (p.getProductId().equals(productId)) {
                products.remove(p);
                System.out.println("Product removed successfully.");
                return true;
            }
        }
        System.out.println("Product not found.");
        return false;
    }

    public boolean updateProduct(String productId, double price, int quantity) {
        for (Product p : products) {
            if (p.getProductId().equals(productId)) {
                if (price >= 0) p.setPrice(price);
                if (quantity >= 0) p.setQuantity(quantity);
                System.out.println("Product updated successfully.");
                return true;
            }
        }
        System.out.println("Product not found.");
        return false;
    }

    public Product searchProduct(String productId) {
        for (Product p : products) {
            if (p.getProductId().equals(productId)) {
                return p;
            }
        }
        return null;
    }

    public void viewAllProducts() {
        if (products.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        System.out.println("--- Inventory ---");
        for (Product p : products) {
            System.out.println(p);
        }
    }
}

// ---------------------- Main Class ----------------------
 class InventoryManagementSystem {
    private static Inventory inventory = new Inventory();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            showMenu();
            choice = getIntInput("Enter choice: ");

            switch (choice) {
                case 1 -> addProduct();
                case 2 -> removeProduct();
                case 3 -> updateProduct();
                case 4 -> searchProduct();
                case 5 -> inventory.viewAllProducts();
                case 6 -> System.out.println("Exiting... Goodbye!");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 6);
    }

    private static void showMenu() {
        System.out.println("\n--- Inventory Menu ---");
        System.out.println("1. Add Product");
        System.out.println("2. Remove Product");
        System.out.println("3. Update Product");
        System.out.println("4. Search Product");
        System.out.println("5. View All Products");
        System.out.println("6. Exit");
    }

    private static void addProduct() {
        System.out.print("Enter Product ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();
        double price = getDoubleInput("Enter Price: ");
        int quantity = getIntInput("Enter Quantity: ");

        Product product = new Product(id, name, price, quantity);
        inventory.addProduct(product);
    }

    private static void removeProduct() {
        System.out.print("Enter Product ID to remove: ");
        String id = scanner.nextLine();
        inventory.removeProduct(id);
    }

    private static void updateProduct() {
        System.out.print("Enter Product ID to update: ");
        String id = scanner.nextLine();
        double price = getDoubleInput("Enter New Price (-1 to skip): ");
        int quantity = getIntInput("Enter New Quantity (-1 to skip): ");
        inventory.updateProduct(id, price, quantity);
    }

    private static void searchProduct() {
        System.out.print("Enter Product ID to search: ");
        String id = scanner.nextLine();
        Product p = inventory.searchProduct(id);
        if (p != null) System.out.println(p);
        else System.out.println("Product not found.");
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = scanner.nextInt();
                scanner.nextLine(); // Clear buffer
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Enter an integer.");
                scanner.nextLine();
            }
        }
    }

    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = scanner.nextDouble();
                scanner.nextLine(); // Clear buffer
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Enter a numeric value.");
                scanner.nextLine();
            }
        }
    }
}
