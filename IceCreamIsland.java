import java.util.InputMismatchException;
import java.util.Scanner;

public class IceCreamIsland {

    // IceCream class
    static class IceCream {

        private String flavor;
        private String size;
        private double cost;
        private static int totalIceCreamsSold = 0;

        // Constructor
        public IceCream(String iceCreamFlavor, String iceCreamSize) {
            this.flavor = iceCreamFlavor;
            this.size = iceCreamSize;
            this.cost = this.calculateCost();
            totalIceCreamsSold++;
        }

        // Getter (Accessor) for flavor
        public String getFlavor() {
            return this.flavor;
        }

        // Setter (Mutator) for flavor
        public void setFlavor(String flavor) {
            this.flavor = flavor;
            this.cost = this.calculateCost(); // Update cost based on flavor change
        }

        // Getter (Accessor) for size
        public String getSize() {
            return this.size;
        }

        // Setter (Mutator) for size
        public void setSize(String size) {
            this.size = size;
            this.cost = this.calculateCost(); // Update cost based on size change
        }

        // Getter (Accessor) for cost
        public double getCost() {
            return this.cost;
        }

        // Static method to get total ice creams sold
        public static int getTotalIceCreamsSold() {
            return totalIceCreamsSold;
        }

        // Private method to calculate cost based on size
        private double calculateCost() {
            switch (this.size.toLowerCase()) {
                case "small":
                    return 3.00;
                case "medium":
                    return 5.00;
                case "large":
                    return 7.00;
                default:
                    return 0.00;
            }
        }

        // Method to describe the ice cream
        public void describe() {
            System.out.println("This is a " + this.size + " " + this.flavor + " ice cream costing Rs" + this.cost + ".");
        }
    }

    // Topping class
    static class Topping {

        private String toppingName;
        private boolean isAdded;
        private double cost;

        // Constructor
        public Topping(String name) {
            this.toppingName = name;
            this.isAdded = false;
            this.cost = 1.50;
        }

        // Getter (Accessor) for topping name
        public String getToppingName() {
            return this.toppingName;
        }

        // Setter (Mutator) for topping name
        public void setToppingName(String toppingName) {
            this.toppingName = toppingName;
        }

        // Getter (Accessor) for isAdded
        public boolean isAdded() {
            return this.isAdded;
        }

        // Setter (Mutator) to add topping
        public void addTopping() {
            this.isAdded = true;
            System.out.println(this.toppingName + " added to the ice cream for Rs" + this.cost + ".");
        }

        // Setter (Mutator) to remove topping
        public void removeTopping() {
            this.isAdded = false;
            System.out.println(this.toppingName + " removed from the ice cream.");
        }

        // Getter (Accessor) for topping cost
        public double getCost() {
            return this.isAdded ? this.cost : 0.00;
        }
    }

    // Customer class
    static class Customer {

        private String name;
        private int loyaltyPoints;
        private static int totalLoyaltyPointsRedeemed = 0;

        // Constructor
        public Customer(String name) {
            this.name = name;
            this.loyaltyPoints = 0;
        }

        // Getter (Accessor) for customer name
        public String getName() {
            return this.name;
        }

        // Getter (Accessor) for loyalty points
        public int getPoints() {
            return this.loyaltyPoints;
        }

        // Method to add loyalty points
        public void addPoints(int points) {
            this.loyaltyPoints += points;
            System.out.println(points + " loyalty points added. Total points: " + this.loyaltyPoints);
        }

        // Method to redeem loyalty points
        public boolean redeemPoints(int points) {
            if (points <= this.loyaltyPoints) {
                this.loyaltyPoints -= points;
                totalLoyaltyPointsRedeemed += points;
                System.out.println(points + " points redeemed. Remaining points: " + this.loyaltyPoints);
                return true;
            } else {
                System.out.println("Insufficient loyalty points.");
                return false;
            }
        }

        // Static method to get total loyalty points redeemed
        public static int getTotalLoyaltyPointsRedeemed() {
            return totalLoyaltyPointsRedeemed;
        }
    }

    // Main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get customer name
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();
        Customer customer = new Customer(customerName);

        // Get ice cream flavor and size from the user
        System.out.print("Enter the flavor of the ice cream: ");
        String flavor = scanner.nextLine();
        System.out.print("Enter the size of the ice cream (Small, Medium, Large): ");
        String size = scanner.nextLine();

        // Create an IceCream object
        IceCream myIceCream = new IceCream(flavor, size);
        myIceCream.describe();

        // Get the number of toppings with validation
        int toppingCount = 0;
        while (true) {
            try {
                System.out.print("How many toppings would you like to add? ");
                toppingCount = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                break;
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
            toppings[i] = new Topping(toppingName);
            toppings[i].addTopping();
        }

        // Ask if the user wants to change the flavor
        System.out.print("Do you want to change the flavor? (yes/no): ");
        String changeFlavor = scanner.nextLine();
        if (changeFlavor.equalsIgnoreCase("yes")) {
            System.out.print("Enter the new flavor: ");
            String newFlavor = scanner.nextLine();
            myIceCream.setFlavor(newFlavor); // Using setter to change flavor
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
            totalCost += topping.getCost();
        }

        // Add loyalty points based on total cost
        int earnedPoints = (int) (totalCost / 10);
        customer.addPoints(earnedPoints);

        // Ask if the user wants to redeem loyalty points
        System.out.print("Do you want to redeem loyalty points? (yes/no): ");
        String redeemChoice = scanner.nextLine();
        double discount = 0.0;
        if (redeemChoice.equalsIgnoreCase("yes")) {
            System.out.print("Enter points to redeem: ");
            int pointsToRedeem = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if (customer.redeemPoints(pointsToRedeem)) {
                discount = pointsToRedeem; // Each point is worth Rs1 discount
                totalCost -= discount;
            }
        }

        // Display order summary
        System.out.println("\nOrder Summary:");
        System.out.println("Customer: " + customer.getName());
        System.out.println("Ice Cream: " + myIceCream.getFlavor() + " (" + myIceCream.getSize() + ")");
        System.out.print("Toppings: ");
        for (Topping topping : toppings) {
            if (topping.isAdded()) {
                System.out.print(topping.getToppingName() + " ");
            }
        }
        System.out.println();
        System.out.println("Total Cost: Rs" + totalCost);
        System.out.println("Loyalty Points Earned: " + earnedPoints);
        System.out.println("Discount Applied: Rs" + discount);

        // Show static data
        System.out.println("Total Ice Creams Sold: " + IceCream.getTotalIceCreamsSold());
        System.out.println("Total Loyalty Points Redeemed: " + Customer.getTotalLoyaltyPointsRedeemed());

        scanner.close();
    }
}
