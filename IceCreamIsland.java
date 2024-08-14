import java.util.Scanner;

class IceCream {
    private String flavor;
    private String size;
    private double cost;

    // Constructor
    public IceCream(String iceCreamFlavor, String iceCreamSize) {
        flavor = iceCreamFlavor;
        size = iceCreamSize;
        cost = calculateCost();
    }

    // Method to calculate the cost of the ice cream based on size
    private double calculateCost() {
        switch (size.toLowerCase()) {
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
        System.out.println("This is a " + size + " " + flavor + " ice cream costing $" + cost + ".");
    }

    // Method to change the flavor of the ice cream
    public void changeFlavor(String newFlavor) {
        flavor = newFlavor;
        System.out.println("Flavor changed to " + newFlavor + ".");
    }

    // Method to get the flavor of the ice cream
    public String getFlavor() {
        return flavor;
    }

    // Method to get the size of the ice cream
    public String getSize() {
        return size;
    }

    // Method to get the cost of the ice cream
    public double getCost() {
        return cost;
    }
}

// Topping.java
class Topping {
    private String toppingName;
    private boolean isAdded;
    private double cost;

    // Constructor
    public Topping(String name) {
        toppingName = name;
        isAdded = false; // By default, topping is not added
        cost = 1.50; // Fixed cost for each topping
    }

    // Method to add the topping to the ice cream
    public void addTopping() {
        if (!isAdded) {
            isAdded = true;
            System.out.println(toppingName + " added to the ice cream for $" + cost + ".");
        } else {
            System.out.println(toppingName + " is already added.");
        }
    }

    // Method to remove the topping from the ice cream
    public void removeTopping() {
        if (isAdded) {
            isAdded = false;
            System.out.println(toppingName + " removed from the ice cream.");
        } else {
            System.out.println(toppingName + " is not added yet.");
        }
    }

    // Method to check if the topping is added
    public boolean isAdded() {
        return isAdded;
    }

    // Method to get the topping name
    public String getToppingName() {
        return toppingName;
    }

    // Method to get the cost of the topping
    public double getCost() {
        return isAdded ? cost : 0.00;
    }
}

// IceCreamIsland.java
public class IceCreamIsland {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get ice cream flavor and size from the user
        System.out.print("Enter the flavor of the ice cream: ");
        String flavor = scanner.nextLine();
        System.out.print("Enter the size of the ice cream (Small, Medium, Large): ");
        String size = scanner.nextLine();

        // Create an IceCream object
        IceCream myIceCream = new IceCream(flavor, size);
        myIceCream.describe();

        // Get topping details from the user
        System.out.print("Enter a topping to add: ");
        String toppingName1 = scanner.nextLine();
        Topping topping1 = new Topping(toppingName1);
        topping1.addTopping();

        System.out.print("Enter another topping to add: ");
        String toppingName2 = scanner.nextLine();
        Topping topping2 = new Topping(toppingName2);
        topping2.addTopping();

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
            System.out.print("Which topping do you want to remove: " + toppingName1 + " or " + toppingName2 + "? ");
            String toppingToRemove = scanner.nextLine();
            if (toppingToRemove.equalsIgnoreCase(toppingName1)) {
                topping1.removeTopping();
            } else if (toppingToRemove.equalsIgnoreCase(toppingName2)) {
                topping2.removeTopping();
            }
        }

        // Calculate the total cost
        double totalCost = myIceCream.getCost() + topping1.getCost() + topping2.getCost();

        // Display final order summary
        System.out.println("\nOrder Summary:");
        System.out.println("Flavor: " + myIceCream.getFlavor());
        System.out.println("Size: " + myIceCream.getSize());
        System.out.println("Toppings:");
        if (topping1.isAdded()) {
            System.out.println("- " + topping1.getToppingName());
        }
        if (topping2.isAdded()) {
            System.out.println("- " + topping2.getToppingName());
        }
        System.out.println("Total Cost: $" + totalCost);
        System.out.println("\nYour order has been placed!");

        scanner.close();
    }
}
