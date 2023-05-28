import java.util.Scanner;

public class OnlineShop {
    private static final int NUM_PRODUCTS = 10;
    private static String[] productNames = {"product1", "Product2", "Product3", "Product4", "Product5", "Product6", "Product7", "Product8", "Product9", "Product10"};
    private static int[] quantities = {5, 3, 8, 0, 2, 6, 1, 4, 7, 9};
    private static double[] prices = {10.99, 5.99, 7.49, 0.0, 3.25, 9.99, 2.99, 6.75, 4.49, 8.99};
    private static String[] cartItems = new String[NUM_PRODUCTS];
    private static int[] cartQuantities = new int[NUM_PRODUCTS];
    private static double totalPrice = 0.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Display shop name and academic ID
        System.out.print("Ali's Online Shop 443811034\n");

        int choice;
        do {
            System.out.println("\n----- Menu -----");
            System.out.println("1. Display products");
            System.out.println("2. Add product to cart");
            System.out.println("3. Remove product from cart");
            System.out.println("4. Display cart items and total price");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayProducts();
                    break;
                case 2:
                    addProductToCart(scanner);
                    break;
                case 3:
                    removeProductFromCart(scanner);
                    break;
                case 4:
                    displayCartItems();
                    break;
                case 5:
                    System.out.println("Thank you for using Ali's Online Shop. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 5);

        scanner.close();
    }

    // Method to display products in the shop
    private static void displayProducts() {
        System.out.println("\n----- Products -----");
        System.out.println("Name\t\tQuantity\tPrice");

        for (int i = 0; i < NUM_PRODUCTS; i++) {
            System.out.print(productNames[i] + "\t");
            if (quantities[i] == 0) {
                System.out.print("Sold out\t");
            } else {
                System.out.print(quantities[i] + "\t\t");
            }
            System.out.println(prices[i]);
        }
    }

    // Method to add a product to the cart
    private static void addProductToCart(Scanner scanner) {
        System.out.println("\n----- Add Product to Cart -----");
        System.out.print("Enter the product name: ");
        String productName = scanner.next();

        int productIndex = -1;
        for (int i = 0; i < NUM_PRODUCTS; i++) {
            if (productNames[i].equals(productName)) {
                productIndex = i;
                break;
            }
        }

        if (productIndex == -1) {
            System.out.println("Product not found.");
        } else if (quantities[productIndex] == 0) {
            System.out.println("Product is sold out.");
        } else {
            System.out.print("Enter the quantity: ");
            int quantity = scanner.nextInt();

            if (quantity > quantities[productIndex]) {
                System.out.println("Insufficient quantity available.");
            } else {
                cartItems[productIndex] = productName;
                cartQuantities[productIndex] += quantity;
                totalPrice += prices[productIndex] * quantity;

                quantities[productIndex] -= quantity;
                System.out.println("Product added to cart.");
            }
        }
    }

    // Method to remove a product from the cart
    private static void removeProductFromCart(Scanner scanner) {
        System.out.println("\n----- Remove Product from Cart -----");
        System.out.print("Enter the product name: ");
        String productName = scanner.next();

        int productIndex = -1;
        for (int i = 0; i < NUM_PRODUCTS; i++) {
            if (cartItems[i] != null && cartItems[i].equalsIgnoreCase(productName)) {
                productIndex = i;
                break;
            }
        }

        if (productIndex == -1) {
            System.out.println("Product not found in cart.");
        } else {
            System.out.print("Enter the quantity: ");
            int quantity = scanner.nextInt();

            if (quantity > cartQuantities[productIndex]) {
                System.out.println("Invalid quantity.");
            } else {
                cartQuantities[productIndex] -= quantity;
                totalPrice -= prices[productIndex] * quantity;

                quantities[productIndex] += quantity;
                System.out.println("Product removed from cart.");
            }
        }
    }

    // Method to display cart items and total price
    private static void displayCartItems() {
        System.out.println("\n----- Cart Items -----");
        System.out.println("Name\t\tQuantity\tPrice");

        for (int i = 0; i < NUM_PRODUCTS; i++) {
            if (cartItems[i] != null) {
                System.out.print(cartItems[i] + "\t");
                System.out.print(cartQuantities[i] + "\t\t");
                System.out.println(prices[i]);
            }
        }

        System.out.println("Total Price: " + totalPrice);
    }
}
