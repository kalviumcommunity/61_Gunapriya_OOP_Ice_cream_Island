import java.util.InputMismatchException;
import java.util.Scanner;

// IceCream.java
class IceCream {

    private String flavor;
    private String size;
    private double cost;
    // Static variable to track the total number of ice creams sold
    private static int totalIceCreamsSold = 0;

    // Constructor
    public IceCream(String iceCreamFlavor, String iceCreamSize) {
        this.flavor = iceCreamFlavor;
        this.size = iceCreamSize;
        this.cost = this.calculateCost();
        totalIceCreamsSold++; // Increment the count whenever a new ice cream is created
    }

    // Method to calculate the cost of the ice cream based on size
    private double calculateCost() {
        switch (this.size.toLowerCase()) {
            case "small":
                return 3.00;
            case "medium":
                return 5.00;
            case "large":
                return 7.00;
            default:
                return 0.00; // Default cost if size is invalid
        }
    }

    // Method to describe the ice cream
    public void describe() {
        System.out.println("This is a " + this.size + " " + this.flavor + " ice cream costing Rs" + this.cost + ".");
    }

    // Method to change the flavor of the ice cream
    public void changeFlavor(String newFlavor) {
        this.flavor = newFlavor;
        System.out.println("Flavor changed to " + newFlavor + ".");
    }

    // Method to get the flavor of the ice cream
    public String getFlavor() {
        return this.flavor;
    }

    // Method to get the size of the ice cream
    public String getSize() {
        return this.size;
    }

    // Method to get the cost of the ice cream
    public double getCost() {
        return this.cost;
    }

    // Static method to get the total ice creams sold
    public static int getTotalIceCreamsSold() {
        return totalIceCreamsSold;
    }
}

// Topping.java
class Topping {

    private String toppingName;
    private boolean isAdded;
    private double cost;

    // Constructor
    public Topping(String name) {
        this.toppingName = name;
        this.isAdded = false; // By default, topping is not added
        this.cost = 1.50; // Fixed cost for each topping
    }

    // Method to add the topping to the ice cream
    public void addTopping() {
        if (!this.isAdded) {
            this.isAdded = true;
            System.out.println(this.toppingName + " added to the ice cream for Rs" + this.cost + ".");
        } else {
            System.out.println(this.toppingName + " is already added.");
        }
    }

    // Method to remove the topping from the ice cream
    public void removeTopping() {
        if (this.isAdded) {
            this.isAdded = false;
            System.out.println(this.toppingName + " removed from the ice cream.");
        } else {
            System.out.println(this.toppingName + " is not added yet.");
        }
    }

    // Method to check if the topping is added
    public boolean isAdded() {
        return this.isAdded;
    }

    // Method to get the topping name
    public String getToppingName() {
        return this.toppingName;
    }

    // Method to get the cost of the topping
    public double getCost() {
        return this.isAdded ? this.cost : 0.00;
    }
}

// Customer.java
class Customer {

    private String name;
    private int loyaltyPoints;
    // Static variable to track the total loyalty points redeemed
    private static int totalLoyaltyPointsRedeemed = 0;

    // Constructor
    public Customer(String name) {
        this.name = name;
        this.loyaltyPoints = 0; // Start with 0 loyalty points
    }

    // Method to add loyalty points
    public void addPoints(int points) {
        this.loyaltyPoints += points;
        System.out.println(points + " loyalty points added. Total points: " + this.loyaltyPoints);
    }

    // Method to redeem loyalty points for a discount
    public boolean redeemPoints(int points) {
        if (points <= this.loyaltyPoints) {
            this.loyaltyPoints -= points;
            totalLoyaltyPointsRedeemed += points; // Increment total points redeemed
            System.out.println(points + " points redeemed. Remaining points: " + this.loyaltyPoints);
            return true;
        } else {
            System.out.println("Insufficient loyalty points.");
            return false;
        }
    }

    // Method to get the current loyalty points
    public int getPoints() {
        return this.loyaltyPoints;
    }

    // Method to get the customer's name
    public String getName() {
        return this.name;
    }

    // Static method to get the total loyalty points redeemed
    public static int getTotalLoyaltyPointsRedeemed() {
        return totalLoyaltyPointsRedeemed;
    }
}

// IceCreamIsland.java
public class IceCreamIsland {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get customer name
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();
        Customer customer = new Customer(customerName); // Create Customer object

        // Get ice cream flavor and size from the user
        System.out.print("Enter the flavor of the ice cream: ");
        String flavor = scanner.nextLine();
        System.out.print("Enter the size of the ice cream (Small, Medium, Large): ");
        String size = scanner.nextLine();

        // Create an IceCream object
        IceCream myIceCream = new IceCream(flavor, size);
        myIceCream.describe();

        // Initialize topping count
        int toppingCount = 0;

        // Get the number of toppings the user wants to add with input validation
        while (true) {
            try {
                System.out.print("How many toppings would you like to add? ");
                toppingCount = scanner.nextInt();
                scanner.nextLine(); // Consume the newline
                break; // Exit the loop if input is valid
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
            }
        }

        // Array of Topping objects
        Topping[] toppings = new Topping[toppingCount];

        // Get topping details from the user
        for (int i = 0; i < toppingCount; i++) {
            System.out.print("Enter topping " + (i + 1) + ": ");
            String toppingName = scanner.nextLine();
            toppings[i] = new Topping(toppingName); // Create and store Topping objects in the array
            toppings[i].addTopping();
        }

        // Ask if the user wants to change the flavor
        System.out.print("Do you want to change the flavor? (yes/no): ");
        String changeFlavor = scanner.nextLine();
        if (changeFlavor.equalsIgnoreCase("yes")) {
            System.out.print("Enter the new flavor: ");
            String newFlavor = scanner.nextLine();
            myIceCream.changeFlavor(newFlavor);
        }

        // Optionally remove a topping
        System.out.print("Do you want to remove any topping? (yes/no): ");
        String removeTopping = scanner.nextLine();
        if (removeTopping.equalsIgnoreCase("yes")) {
            System.out.print("Which topping do you want to remove? ");
            String toppingToRemove = scanner.nextLine();
            for (Topping topping : toppings) {
                if (topping.getToppingName().equalsIgnoreCase(toppingToRemove)) {
                    topping.removeTopping();
                    break;
                }
            }
        }

        // Calculate the total cost
        double totalCost = myIceCream.getCost();
        for (Topping topping : toppings) {
            totalCost += topping.getCost(); // Sum the costs of all added toppings
        }

        // Calculate and add loyalty points based on total cost
        int earnedPoints = (int) (totalCost / 10); // 1 point for every Rs10 spent
        customer.addPoints(earnedPoints); // Add points to the customer's account

        // Ask if the user wants to redeem loyalty points
        System.out.print("Do you want to redeem loyalty points? (yes/no): ");
        String redeemChoice = scanner.nextLine();
        double discount = 0.0;
        if (redeemChoice.equalsIgnoreCase("yes")) {
            System.out.print("Enter points to redeem: ");
            int pointsToRedeem = scanner.nextInt();
            scanner.nextLine(); // Consume the newline
            if (customer.redeemPoints(pointsToRedeem)) {
                discount = pointsToRedeem; // Each point is worth Rs1 discount
                totalCost -= discount; // Apply discount to total cost
            }
        }

        // Display final order summary
        System.out.println("\nOrder Summary:");
        System.out.println("Customer: " + customer.getName());
        System.out.println("Ice Cream: " + myIceCream.getFlavor() + " (" + myIceCream.getSize() + ")");
        System.out.print("Toppings: ");
        for (Topping topping : toppings) {
            if (topping.isAdded()) {
                System.out.print(topping.getToppingName() + " ");
            }
        }
        System.out.println("\nTotal Cost: Rs" + totalCost);
        System.out.println("Loyalty Points Earned: " + earnedPoints);
        System.out.println("Discount Applied: Rs" + discount);
        System.out.println("Thank you for your purchase!");

        // Display total ice creams sold and total loyalty points redeemed
        System.out.println("\nStatistics:");
        System.out.println("Total Ice Creams Sold: " + IceCream.getTotalIceCreamsSold());
        System.out.println("Total Loyalty Points Redeemed: " + Customer.getTotalLoyaltyPointsRedeemed());

        scanner.close(); // Close the scanner to free resources
    }
}
