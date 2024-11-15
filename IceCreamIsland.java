import java.util.*;


abstract class IceCream {
    protected String flavor;
    protected String size;
    protected static final double SMALL_COST = 3.00;
    protected static final double MEDIUM_COST = 5.00;
    protected static final double LARGE_COST = 7.00;

    public IceCream(String flavor, String size) {
        this.flavor = flavor;
        this.size = size;
    }

    // Abstract methods (OOP: Abstraction)
    public abstract double calculateCost();
    public abstract String describe();

    // Encapsulation with getters and setters
    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}

// SpecialIceCream class (OOP: Inheritance, Polymorphism)
class SpecialIceCream extends IceCream {
    private String premiumTopping;
    private static final double PREMIUM_TOPPING_COST = 3.00;

    public SpecialIceCream(String flavor, String size, String premiumTopping) {
        super(flavor, size);
        this.premiumTopping = premiumTopping;
    }

    @Override
    public double calculateCost() {
        double baseCost = IceCreamUtility.getBaseCost(size);
        return baseCost + PREMIUM_TOPPING_COST;
    }

    @Override
    public String describe() {
        return "This is a " + size + " " + flavor + " ice cream with " + premiumTopping + " topping, costing Rs" + calculateCost() + ".";
    }
}

// RegularIceCream class (OOP: Inheritance, Polymorphism)
class RegularIceCream extends IceCream {

    public RegularIceCream(String flavor, String size) {
        super(flavor, size);
    }

    @Override
    public double calculateCost() {
        return IceCreamUtility.getBaseCost(size);
    }

    @Override
    public String describe() {
        return "This is a " + size + " " + flavor + " ice cream costing Rs" + calculateCost() + ".";
    }
}

// Utility class for common functions (Single Responsibility Principle, Static Methods)
class IceCreamUtility {
    public static double getBaseCost(String size) {
        switch (size.toLowerCase()) {
            case "small":
                return IceCream.SMALL_COST;
            case "medium":
                return IceCream.MEDIUM_COST;
            case "large":
                return IceCream.LARGE_COST;
            default:
                return 0.00;
        }
    }
}

// Abstract Topping class (OOP: Abstraction)
abstract class Topping {
    protected String toppingName;
    protected boolean isAdded;

    public Topping(String toppingName) {
        this.toppingName = toppingName;
        this.isAdded = false;
    }

    public abstract double getCost();
    public abstract String addTopping();
}

// PremiumTopping class (OOP: Inheritance, Interface Segregation)
class PremiumTopping extends Topping {
    private static final double PREMIUM_COST = 3.00;

    public PremiumTopping(String name) {
        super(name);
    }

    @Override
    public String addTopping() {
        this.isAdded = true;
        return toppingName + " (premium) added for Rs" + PREMIUM_COST + ".";
    }

    @Override
    public double getCost() {
        return isAdded ? PREMIUM_COST : 0.00;
    }
}

// RegularTopping class (OOP: Inheritance, Interface Segregation)
class RegularTopping extends Topping {
    private static final double REGULAR_COST = 1.50;

    public RegularTopping(String name) {
        super(name);
    }

    @Override
    public String addTopping() {
        this.isAdded = true;
        return toppingName + " added for Rs" + REGULAR_COST + ".";
    }

    @Override
    public double getCost() {
        return isAdded ? REGULAR_COST : 0.00;
    }
}

// Customer class (Encapsulation, Static Variables)
class Customer {
    private String name;
    private int loyaltyPoints;
    private static int totalCustomers = 0;

    public Customer(String name) {
        this.name = name;
        this.loyaltyPoints = 0;
        totalCustomers++;
    }

    public void addPoints(int points) {
        loyaltyPoints += points;
    }

    public boolean redeemPoints(int points) {
        if (points <= loyaltyPoints) {
            loyaltyPoints -= points;
            return true;
        }
        return false;
    }

    public static int getTotalCustomers() {
        return totalCustomers;
    }
}

// Main class (Dependency Inversion, Polymorphism)
public class IceCreamIsland {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();
        Customer customer = new Customer(customerName);

        System.out.print("Enter the flavor of the ice cream: ");
        String flavor = scanner.nextLine();
        System.out.print("Enter the size of the ice cream (Small, Medium, Large): ");
        String size = scanner.nextLine();
        System.out.print("Do you want a premium ice cream with a topping? (yes/no): ");
        String isPremium = scanner.nextLine();

        IceCream iceCream = isPremium.equalsIgnoreCase("yes")
                ? new SpecialIceCream(flavor, size, "Chocolate Chips")
                : new RegularIceCream(flavor, size);

        // List to store chosen toppings
        List<Topping> chosenToppings = chooseToppings(scanner);

        // Calculate total cost including toppings
        double totalCost = iceCream.calculateCost();
        StringBuilder description = new StringBuilder(iceCream.describe());

        // Add each chosen topping to the description and total cost
        for (Topping topping : chosenToppings) {
            description.append("\n").append(topping.addTopping());
            totalCost += topping.getCost();
        }

        // Display final description and cost
        description.append("\nTotal cost with toppings: Rs").append(totalCost);
        System.out.println(description);
        System.out.println("Total Customers: " + Customer.getTotalCustomers());

        scanner.close();
    }

    // Method to choose multiple toppings (Open-Closed Principle)
    public static List<Topping> chooseToppings(Scanner scanner) {
        List<Topping> toppings = new ArrayList<>();
        String choice;

        while (true) {
            System.out.print("Choose a topping (regular, premium) or type 'done' to finish: ");
            choice = scanner.nextLine().toLowerCase();

            if (choice.equals("done")) {
                break;
            }

            Topping topping = getTopping(choice);
            if (topping != null) {
                toppings.add(topping);
            } else {
                System.out.println("Invalid choice, please try again.");
            }
        }
        return toppings;
    }

    // Factory method to get the appropriate Topping (Dependency Inversion Principle)
    public static Topping getTopping(String choice) {
        switch (choice.toLowerCase()) {
            case "regular":
                return new RegularTopping("Sprinkles");
            case "premium":
                return new PremiumTopping("Chocolate Chips");
            default:
                return null;
        }
    }
}