import java.util.Scanner;

public class IceCreamIsland {

    // IceCream class (Parent class)
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

        // Overloaded constructor with default size
        public IceCream(String iceCreamFlavor) {
            this(iceCreamFlavor, "Medium");
        }

        // Getter and Setter methods for flavor and size
        public String getFlavor() {
            return this.flavor;
        }

        public void setFlavor(String flavor) {
            this.flavor = flavor;
            this.cost = this.calculateCost();
        }

        public String getSize() {
            return this.size;
        }

        public void setSize(String size) {
            this.size = size;
            this.cost = this.calculateCost();
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

        // Method to describe the ice cream - can be overridden in subclasses
        public void describe() {
            System.out.println("This is a " + this.size + " " + this.flavor + " ice cream costing Rs" + this.cost + ".");
        }

        // Method to get the cost - can be overridden in subclasses
        public double getCost() {
            return this.cost;
        }
    }

    // SpecialIceCream class (Subclass) - demonstrating method overriding
    static class SpecialIceCream extends IceCream {
        private String premiumTopping;

        // Constructor for special ice cream
        public SpecialIceCream(String flavor, String size, String premiumTopping) {
            super(flavor, size); // calling parent constructor
            this.premiumTopping = premiumTopping;
        }

        // Overriding the describe method (polymorphism)
        @Override
        public void describe() {
            super.describe();
            System.out.println("It comes with a premium topping of " + this.premiumTopping + ".");
        }

        // Overriding the getCost method (polymorphism) to add premium topping cost
        @Override
        public double getCost() {
            return super.getCost() + 3.00; // Adding extra Rs 3.00 for premium topping
        }
    }

    // Topping class (Parent class)
    static class Topping {
        private String toppingName;
        protected boolean isAdded;
        private double cost;

        // Constructor
        public Topping(String name) {
            this.toppingName = name;
            this.isAdded = false;
            this.cost = 1.50;
        }

        // Method to add topping
        public void addTopping() {
            this.isAdded = true;
            System.out.println(this.toppingName + " added to the ice cream for Rs" + this.cost + ".");
        }

        // Method to get topping cost
        public double getCost() {
            return this.isAdded ? this.cost : 0.00;
        }
    }

    // PremiumTopping class (Subclass) - demonstrating method overriding
    static class PremiumTopping extends Topping {
        private double premiumCost;

        // Constructor for premium topping
        public PremiumTopping(String name) {
            super(name);
            this.premiumCost = 3.00;
        }

        // Overriding the addTopping method (polymorphism)
        @Override
        public void addTopping() {
            this.isAdded = true;
            System.out.println(super.toppingName + " (premium) added for Rs" + this.premiumCost + ".");
        }

        // Overriding the getCost method (polymorphism)
        @Override
        public double getCost() {
            return this.isAdded ? this.premiumCost : 0.00;
        }
    }

    // Customer class (Parent class)
    static class Customer {
        private String name;
        protected int loyaltyPoints;
        private static int totalLoyaltyPointsRedeemed = 0;

        // Constructor
        public Customer(String name) {
            this.name = name;
            this.loyaltyPoints = 0;
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
    }

    // VIPCustomer class (Subclass) - demonstrating method overriding
    static class VIPCustomer extends Customer {
        // Constructor
        public VIPCustomer(String name) {
            super(name); // calling parent constructor
        }

        // Overriding the addPoints method (polymorphism)
        @Override
        public void addPoints(int points) {
            super.addPoints(points * 2); // VIP customers earn double points
        }

        // Overriding the redeemPoints method (polymorphism) to offer a discount
        @Override
        public boolean redeemPoints(int points) {
            return super.redeemPoints((int) (points * 0.9)); // 10% more value when redeeming points
        }
    }

    // Main method to demonstrate polymorphism
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get customer type (polymorphism through upcasting)
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();
        System.out.print("Are you a VIP customer? (yes/no): ");
        String isVIP = scanner.nextLine();
        Customer customer = isVIP.equalsIgnoreCase("yes") ? new VIPCustomer(customerName) : new Customer(customerName); // Polymorphic behavior

        // Get ice cream type (polymorphism through upcasting)
        System.out.print("Enter the flavor of the ice cream: ");
        String flavor = scanner.nextLine();
        System.out.print("Enter the size of the ice cream (Small, Medium, Large): ");
        String size = scanner.nextLine();
        System.out.print("Do you want a premium ice cream with a topping? (yes/no): ");
        String isPremium = scanner.nextLine();
        IceCream iceCream = isPremium.equalsIgnoreCase("yes") ? new SpecialIceCream(flavor, size, "Chocolate Chips") : new IceCream(flavor, size); // Polymorphic behavior

        // Describe the ice cream (polymorphism at work)
        iceCream.describe();
        System.out.println("Total cost: Rs" + iceCream.getCost());

        // Redeem loyalty points (polymorphic method call)
        System.out.print("Would you like to redeem loyalty points? (yes/no): ");
        String redeemChoice = scanner.nextLine();
        if (redeemChoice.equalsIgnoreCase("yes")) {
            System.out.print("Enter points to redeem: ");
            int pointsToRedeem = scanner.nextInt();
            customer.redeemPoints(pointsToRedeem); // Polymorphic method
        }

        scanner.close();
    }
}
