package a0;

public class Main {
    public static void main(String[] args) {
        // Prints out your Java version
        // Make sure it is 10.0 or higher
        System.out.println("Your Java version: " + System.getProperty(("java.version")));

        // Task 2 here
        System.out.println(Main.sayHello());
    }

    // Task 1 here
    public static String sayHello()
    {
        String hi = "Hello World";
        return hi;
    }


}
