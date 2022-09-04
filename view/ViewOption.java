package view;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import model.AccountDetail;

public class ViewOption {
    static List<AccountDetail> accountDetails = new ArrayList<AccountDetail>();
    Scanner scanner = new Scanner(System.in);

    public static void displayMainStartView() {
        System.out.println(">---------------------------------------------------->");
        System.out.printf("\n\nPlease select option:\n");
        System.out.println("[1] Login page");
        System.out.println("[2] Register online page");
        System.out.println("[3] Quit program");
    }

    public static void displayMainQuitView() {
        System.out.println("[Exited] Good Bye!");
        System.exit(0);
    }
}