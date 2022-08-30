package view;

public class ViewOption {
    public static void displayMainStartView() {
        System.out.println("Please select option below:");
        System.out.println("1. Login page");
        System.out.println("2. Register online page");
        System.out.println("3. Quit program");
    }

    public static void displayMainQuitView() {
        System.out.println("Exited Program, Good Bye!");
        System.exit(0);
    }
    
}