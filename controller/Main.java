package controller;
import java.util.Scanner;
import view.*;
import utils.*;

public class Main{
    public static Scanner scanner;
    //public static LoginService loginService;
    //public static List<User> listUser;

    public static void openScanner() {
        scanner = new Scanner(System.in);
    }

    public static void closeScanner() {
        scanner.close();
    }

    public static void mainLoop() {
        int userOption = 0;

        System.out.printf("\n\ninput option: ");
        userOption = scanner.nextInt();

        while (true) {
            switch (userOption) {
                case ConstantVars.OPTION_LOGIN_PAGE: {
                    //Hieu code
                    break;
                }

                case ConstantVars.OPTION_REG_ONLINE_PAGE: {
                    //Hoang code
                    break;
                }
            }
        }


    }

    public static void main(String[] args) {
        openScanner();

        ViewOption.displayMainStartView();
        mainLoop();

        closeScanner();
    }
}