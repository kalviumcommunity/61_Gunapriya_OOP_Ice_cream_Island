// IceCream.java
class IceCream {

    private String flavor;
    private String size;
    private double cost;
    private static int totalIceCreamsSold = 0;

    // Constructor
    public IceCream(String iceCreamFlavor, String iceCreamSize) {
        this.setFlavor(iceCreamFlavor); // Using setter
        this.setSize(iceCreamSize);     // Using setter
        this.cost = this.calculateCost();
        totalIceCreamsSold++;
    }

    // Encapsulated setter for flavor
    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    // Encapsulated getter for flavor
    public String getFlavor() {
        return this.flavor;
    }

    // Encapsulated setter for size
    public void setSize(String size) {
        if (size.equalsIgnoreCase("small") || size.equalsIgnoreCase("medium") || size.equalsIgnoreCase("large")) {
            this.size = size;
        } else {
            this.size = "small"; // Default to small if invalid size
        }
    }

    // Encapsulated getter for size
    public String getSize() {
        return this.size;
    }

    // Private method to calculate the cost
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

    public double getCost() {
        return this.cost;
    }

    public void changeFlavor(String newFlavor) {
        this.setFlavor(newFlavor);  // Using encapsulated setter
        System.out.println("Flavor changed to " + newFlavor + ".");
    }

    public void describe() {
        System.out.println("This is a " + this.size + " " + this.flavor + " ice cream costing Rs" + this.cost + ".");
    }

    public static int getTotalIceCreamsSold() {
        return totalIceCreamsSold;
    }
}

// Topping.java
class Topping {

    private String toppingName;
    private boolean isAdded;
    private double cost;

    public Topping(String toppingName) {
        this.setToppingName(toppingName); // Using setter
        this.isAdded = false;
        this.cost = 1.50;
    }

    // Encapsulated setter for topping name
    public void setToppingName(String toppingName) {
        this.toppingName = toppingName;
    }

    // Encapsulated getter for topping name
    public String getToppingName() {
        return this.toppingName;
    }

    public void addTopping() {
        if (!this.isAdded) {
            this.isAdded = true;
            System.out.println(this.toppingName + " added to the ice cream for Rs" + this.cost + ".");
        } else {
            System.out.println(this.toppingName + " is already added.");
        }
    }

    public void removeTopping() {
        if (this.isAdded) {
            this.isAdded = false;
            System.out.println(this.toppingName + " removed from the ice cream.");
        } else {
            System.out.println(this.toppingName + " is not added yet.");
        }
    }

    public boolean isAdded() {
        return this.isAdded;
    }

    public double getCost() {
        return this.isAdded ? this.cost : 0.00;
    }
}
