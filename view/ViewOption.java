package view;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import model.AccountDetail;
public class ViewOption {
    static List<AccountDetail> accountDetails = new ArrayList<AccountDetail>();
    Scanner scanner = new Scanner(System.in);
    public static void displayMainStartView() {
        System.out.println("Please select option below:");
        System.out.println("1. Login page");
        System.out.println("2. Register online page");
        System.out.println("3. Quit program");
    }

    

    /**
     * display menu
     */
    // private void displayMenu() {
    //     System.out.println("Menu:");
    //     System.out.println("[1] Them Account customer");
    //     System.out.println("[2] Sua Account customer");
    //     System.out.println("[3] Xoa Account customer");
    //     System.out.println("[4] Quan li lai suat tiet kiem");
    //     System.out.println("[5] Kiem tra nguoi dung co phai khac hang tiem nang");
    //     System.out.println("[6] Quit");
        
    // }

    // private void quit() {
    //     System.out.println("Hen gap lai !!!");
    //     this.close();
    // }
    // public void close() {
    //     if (this.scanner != null) {
    //         this.scanner.close();
    //     }
    // }

    public static void displayMainQuitView() {
        System.out.println("Exited Program, Good Bye!");
        System.exit(0);
    }
}