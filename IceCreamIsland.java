import java.util.InputMismatchException;
import java.util.Scanner;

public class IceCreamIsland {

    // IceCream class
    static class IceCream {
        private String flavor;   // private access: restricted to this class
        private String size;     // private access: restricted to this class
        private double cost;     // private access: restricted to this class
        private static int totalIceCreamsSold = 0; 

        // Constructor with flavor and size
        public IceCream(String iceCreamFlavor, String iceCreamSize) {
            this.flavor = iceCreamFlavor;
            this.size = iceCreamSize;
            this.cost = this.calculateCost();
            totalIceCreamsSold++;
        }

        // Overloaded constructor with only flavor (default size set to medium)
        public IceCream(String iceCreamFlavor) {
            this(iceCreamFlavor, "Medium");
        }

        // Getter (Accessor) for flavor - public to allow access outside class
        public String getFlavor() {
            return this.flavor;
        }

        // Setter (Mutator) for flavor - public to allow access outside class
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

        // Getter (Accessor) for cost - public to allow reading cost
        public double getCost() {
            return this.cost;
        }

        // Static method to get total ice creams sold
        public static int getTotalIceCreamsSold() {
            return totalIceCreamsSold;
        }

        // Private method to calculate cost based on size - no need for public exposure
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

    // SpecialIceCream class that inherits IceCream
    static class SpecialIceCream extends IceCream {
        private String premiumTopping; // new field for special topping

        // Constructor for special ice cream with topping
        public SpecialIceCream(String flavor, String size, String premiumTopping) {
            super(flavor, size); // calling parent constructor
            this.premiumTopping = premiumTopping;
        }

        // Method to describe the special ice cream
        @Override
        public void describe() {
            super.describe();
            System.out.println("It comes with a premium topping of " + this.premiumTopping + ".");
        }
    }

    // Topping class
    static class Topping {
        private String toppingName;  // private access to internal field
        protected boolean isAdded;   // protected to allow access in subclasses
        private double cost;         // private as it's related to internal calculation

        // Constructor
        public Topping(String name) {
            this.toppingName = name;
            this.isAdded = false;
            this.cost = 1.50;
        }

        // Getter (Accessor) for topping name - public to allow outside access
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

        // Getter (Accessor) for topping cost - public to allow access
        public double getCost() {
            return this.isAdded ? this.cost : 0.00;
        }
    }

    // PremiumTopping class that inherits Topping
    static class PremiumTopping extends Topping {
        private double premiumCost; // additional cost for premium toppings

        // Constructor for premium topping
        public PremiumTopping(String name) {
            super(name);
            this.premiumCost = 3.00; // Premium toppings cost more
        }

        // Overridden method to add premium topping
        @Override
        public void addTopping() {
            this.isAdded = true;
            System.out.println(this.getToppingName() + " (premium) added to the ice cream for Rs" + this.premiumCost + ".");
        }

        // Overridden getter for cost to reflect premium price
        @Override
        public double getCost() {
            return this.isAdded() ? this.premiumCost : 0.00;
        }
    }

    // Customer class
    static class Customer {
        private String name;        // private as customer name should only be modified via methods
        protected int loyaltyPoints; // protected as subclasses may access it
        private static int totalLoyaltyPointsRedeemed = 0;  // private as it relates to internal tracking

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

    // VIPCustomer class that inherits Customer
    static class VIPCustomer extends Customer {
        // Constructor
        public VIPCustomer(String name) {
            super(name); // call parent constructor
        }

        // VIP customers earn double loyalty points
        @Override
        public void addPoints(int points) {
            super.addPoints(points * 2);
        }

        // VIP customers can redeem 10% more value from loyalty points
        @Override
        public boolean redeemPoints(int points) {
            return super.redeemPoints((int) (points * 0.9)); // 10% extra value for points
        }
    }

    // Main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get customer name and type (regular or VIP)
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();
        System.out.print("Are you a VIP customer? (yes/no): ");
        String isVIP = scanner.nextLine();
        Customer customer = isVIP.equalsIgnoreCase("yes") ? new VIPCustomer(customerName) : new Customer(customerName);

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
                System.out.println("Please enter a valid number.");
                scanner.nextLine(); // Consume invalid input
            }
        }

        // Loop to add toppings based on user input
        for (int i = 0; i < toppingCount; i++) {
            System.out.print("Enter the topping name: ");
            String toppingName = scanner.nextLine();

            System.out.print("Is this a premium topping? (yes/no): ");
            String isPremium = scanner.nextLine();
            Topping topping = isPremium.equalsIgnoreCase("yes") ? new PremiumTopping(toppingName) : new Topping(toppingName);
            topping.addTopping();
        }

        // Redeem points if customer has enough loyalty points
        System.out.print("Would you like to redeem loyalty points? (yes/no): ");
        String redeemChoice = scanner.nextLine();
        if (redeemChoice.equalsIgnoreCase("yes")) {
            System.out.print("Enter points to redeem: ");
            int pointsToRedeem = scanner.nextInt();
            customer.redeemPoints(pointsToRedeem);
        }

        scanner.close();
    }
}
