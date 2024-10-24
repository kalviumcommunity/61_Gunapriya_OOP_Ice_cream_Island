import java.util.Scanner;

public class IceCreamIsland {

    // Abstract IceCream class (Parent class)
    static abstract class IceCream {
        protected String flavor;
        protected String size;

        public IceCream(String flavor, String size) {
            this.flavor = flavor;
            this.size = size;
        }

        // Abstract method (virtual function)
        public abstract double calculateCost();

        // Abstract method (virtual function)
        public abstract String describe();

        // Getter and Setter methods for flavor and size
        public String getFlavor() {
            return this.flavor;
        }

        public void setFlavor(String flavor) {
            this.flavor = flavor;
        }

        public String getSize() {
            return this.size;
        }

        public void setSize(String size) {
            this.size = size;
        }
    }

    // SpecialIceCream class (Subclass) - implementing abstract methods
    static class SpecialIceCream extends IceCream {
        private String premiumTopping;

        // Constructor for special ice cream
        public SpecialIceCream(String flavor, String size, String premiumTopping) {
            super(flavor, size); // calling parent constructor
            this.premiumTopping = premiumTopping;
        }

        // Implementing abstract method - calculate cost for special ice cream
        @Override
        public double calculateCost() {
            double baseCost;
            switch (this.size.toLowerCase()) {
                case "small":
                    baseCost = 3.00;
                    break;
                case "medium":
                    baseCost = 5.00;
                    break;
                case "large":
                    baseCost = 7.00;
                    break;
                default:
                    baseCost = 0.00;
            }
            // Add premium topping cost
            return baseCost + 3.00; // Adding Rs 3.00 for premium topping
        }

        // Implementing abstract method - describing the special ice cream
        @Override
        public String describe() {
            return "This is a " + this.size + " " + this.flavor + " ice cream with " + this.premiumTopping + " topping, costing Rs" + this.calculateCost() + ".";
        }
    }

    // RegularIceCream class (Subclass) - implementing abstract methods
    static class RegularIceCream extends IceCream {

        public RegularIceCream(String flavor, String size) {
            super(flavor, size); // calling parent constructor
        }

        // Implementing abstract method - calculate cost for regular ice cream
        @Override
        public double calculateCost() {
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

        // Implementing abstract method - describing the regular ice cream
        @Override
        public String describe() {
            return "This is a " + this.size + " " + this.flavor + " ice cream costing Rs" + this.calculateCost() + ".";
        }
    }

    // Topping classes remain unchanged, with methods returning strings instead of printing directly

    // Abstract Topping class (Parent class)
    static abstract class Topping {
        protected String toppingName;
        protected boolean isAdded;

        public Topping(String toppingName) {
            this.toppingName = toppingName;
            this.isAdded = false;
        }

        // Abstract method (virtual function)
        public abstract double getCost();

        // Abstract method (virtual function)
        public abstract String addTopping();
    }

    // PremiumTopping class (Subclass) - implementing abstract methods
    static class PremiumTopping extends Topping {
        private double premiumCost;

        // Constructor for premium topping
        public PremiumTopping(String name) {
            super(name);
            this.premiumCost = 3.00;
        }

        // Implementing abstract method - add premium topping
        @Override
        public String addTopping() {
            this.isAdded = true;
            return this.toppingName + " (premium) added for Rs" + this.premiumCost + ".";
        }

        // Implementing abstract method - get premium topping cost
        @Override
        public double getCost() {
            return this.isAdded ? this.premiumCost : 0.00;
        }
    }

    // RegularTopping class (Subclass) - implementing abstract methods
    static class RegularTopping extends Topping {
        private double regularCost;

        // Constructor for regular topping
        public RegularTopping(String name) {
            super(name);
            this.regularCost = 1.50;
        }

        // Implementing abstract method - add regular topping
        @Override
        public String addTopping() {
            this.isAdded = true;
            return this.toppingName + " added for Rs" + this.regularCost + ".";
        }

        // Implementing abstract method - get regular topping cost
        @Override
        public double getCost() {
            return this.isAdded ? this.regularCost : 0.00;
        }
    }

    // Customer class
    static class Customer {
        private String name;
        protected int loyaltyPoints;

        // Constructor
        public Customer(String name) {
            this.name = name;
            this.loyaltyPoints = 0;
        }

        // Method to add loyalty points
        public void addPoints(int points) {
            this.loyaltyPoints += points;
        }

        // Method to redeem loyalty points
        public boolean redeemPoints(int points) {
            if (points <= this.loyaltyPoints) {
                this.loyaltyPoints -= points;
                return true;
            } else {
                return false;
            }
        }
    }

    // Main method to demonstrate the use of abstract classes and polymorphism
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get customer information
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();
        Customer customer = new Customer(customerName);

        // Get ice cream type
        System.out.print("Enter the flavor of the ice cream: ");
        String flavor = scanner.nextLine();
        System.out.print("Enter the size of the ice cream (Small, Medium, Large): ");
        String size = scanner.nextLine();
        System.out.print("Do you want a premium ice cream with a topping? (yes/no): ");
        String isPremium = scanner.nextLine();
        IceCream iceCream = isPremium.equalsIgnoreCase("yes") ? new SpecialIceCream(flavor, size, "Chocolate Chips") : new RegularIceCream(flavor, size);

        // Optionally add a topping
        System.out.print("Do you want to add a regular topping? (yes/no): ");
        String addTopping = scanner.nextLine();

        Topping topping = null;
        if (addTopping.equalsIgnoreCase("yes")) {
            topping = new RegularTopping("Sprinkles");
        }

        // Combine output at the end
        String output = iceCream.describe();
        if (topping != null) {
            output += "\n" + topping.addTopping();
            output += "\nTotal cost with topping: Rs" + (iceCream.calculateCost() + topping.getCost());
        } else {
            output += "\nTotal cost: Rs" + iceCream.calculateCost();
        }

        // Display all output at once
        System.out.println(output);

        // Close scanner
        scanner.close();
    }
}
