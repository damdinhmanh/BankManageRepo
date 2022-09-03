package controller;
import java.util.Scanner;
import view.*;
import utils.*;
import model.AccountDetail;
import model.Admin;
import model.Customer;
import java.util.List;
import java.util.Random;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Main{
    static Scanner scanner = new Scanner(System.in);
    static List<AccountDetail> accountDetails = new ArrayList<AccountDetail>();
    static String ACCOUNTID_PATTERN =
            "^([0-9]).{6}$";

    static String PASSWORD_PATTERN =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()-_[{}]:;',?/*~$^+=<>]).{8,15}$";

    public static void openScanner() {
        scanner  = new Scanner(System.in);
    }

    static public int getRandomNumberUsingInts(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
          .findFirst()
          .getAsInt();
    }

    public static void mainLoop() {
        int userOption = 0;

        System.out.printf("\n\ninput option: ");
        userOption = scanner.nextInt();

        while (true) {
            switch (userOption) {
                case ConstantVars.OPTION_LOGIN_PAGE: {
                    loginBanking();
                    showMenu();
                    break;
                }

                case ConstantVars.OPTION_REG_ONLINE_PAGE: {
                    //Hoang code
                    break;
                }
            }
        }
    }
    

    private static void showMenu() {
        System.out.println("Chuc nang cua Admin ");
        System.out.println("[1] Them Account customer");
        System.out.println("[2] Sua Account customer");
        System.out.println("[3] Xoa Account customer");
        System.out.println("[4] Quan li lai suat tiet kiem");
        System.out.println("[5] Kiem tra nguoi dung co phai khac hang tiem nang");
        System.out.println("[6] Quit");
            int menu1;
        do {
            menu1 = Integer.parseInt(scanner.nextLine());
            scanner.nextLine();
            switch (menu1) {
                case 1:{
                    // addAcc();
                    break;
                }
                case 2: {
                    // changeAccount();
                    break;
                }
                case 3: {
                    // deleteAccount();
                    break;
                }
                case 4: {
                    // interestRate();
                }
                case 5:{
                    // customerTrustlevel();
                }
                case 6: {
                    mainLoop();
                    break;
                }
                default:
                    System.exit(0);
            }
        } while (menu1 != 6);
        
            System.out.println("Chuc nang cua Customer");
            System.out.println("[1] Chuyen tien");
            System.out.println("[2] Gui tiet kiem");
            System.out.println("[3] Rut tiet kiem");
            System.out.println("[4] Kiem tra danh sach so tiet kiem");
            System.out.println("[5] Lich su giao dich");
            System.out.println("[6] Kiem tra so du");
                int menu2;
            do {
                menu2 = Integer.parseInt(scanner.nextLine());
                scanner.nextLine();
                switch (menu2) {
                    case 1:{
                        // chuyentien
                        break;
                    }
                    case 2: {
                    //    gui tiet kiem
                        break;
                    }
                    case 3: {
                    //    rut tiet kiem
                        break;
                    }
                    case 4: {
                    // Kiem tra danh sach so tiet kiem
                    }
                    case 5:{
                    // Lich su giao dich
                    }
                    case 6:{
                    // Kiem tra so du
                    }
                    case 7: {
                        mainLoop();
                        break;
                    }
                    default:
                        System.exit(0);
                }
            } while (menu2 != 7);
    }

    public static void loginBanking() {
        System.out.println("----Login Page----");
        scanner.nextLine();

        boolean kiemTraGiaTriAccountId = false;
        String accountID;
        do {
            System.out.print("Nhap accountId: ");
            accountID = scanner.nextLine();
            if(checkAccountId(accountID) == false){
                System.out.println("Ban nhap sai dinh dang!!! ");
                kiemTraGiaTriAccountId = false;
            }else{
                for (AccountDetail accountDetail : accountDetails) {
                    if (accountID.equals(accountDetail.getAccountId())) {
                        kiemTraGiaTriAccountId = true;
                    }else{
                        kiemTraGiaTriAccountId = false;
                        System.out.println("Kiem tra lai AccountId!!!");
                    }  
                }
            }

        } while(kiemTraGiaTriAccountId == false);

        boolean kiemTraGiatriPassword = false;

        do {
            System.out.print("Nhap Password: ");
            String password = scanner.nextLine();
            if(checkPass(password) == false){
                System.out.println("Ban nhap sai dinh dang!!! ");
                scanner.nextLine();
                kiemTraGiatriPassword = false;
            }else{
                for (AccountDetail accountDetail : accountDetails) {
                    if (password.equals(accountDetail.getPassword()) && accountID.equals(accountDetail.getAccountId())) {
                        kiemTraGiatriPassword = true;
                    }else{
                        kiemTraGiatriPassword = false;
                        System.out.println("Kiem tra lai Password!!!");
                        scanner.nextLine();
                    }
                }
            }
        } while(kiemTraGiatriPassword == false);
        
       


        while(true){
            int newCapcha = getRandomNumberUsingInts(10000, 99999);
            System.out.println("Nhap ma bao ve sau: " + newCapcha);
            System.out.print("Nhap ma bao ve: ");
            int inputCapcha = scanner.nextInt();
            
            if(newCapcha == inputCapcha){
                System.out.println("Nhap dung capcha");
                break;
            }else{
                System.out.println("Ban da nhap sai ma bao ve , Yeu cau nhap lai!!!");
            }
        }

        System.out.println("Dang nhap thanh cong !!!");
        scanner.nextLine();
       
    }

    public static void closeScanner() {
        scanner.close();
    }


    public static void main(String[] args) { 
        accountDetails.add((AccountDetail) new Admin("100001", "Admin@123", "admin"));
        accountDetails.add((AccountDetail) new Customer("100002", "Customer@1", "customer1", 100000000, "Hieu", LocalDate.of(1999, 07, 27), "0966759751", "hieu@gmail.com", "nationalId", "Ha Noi", "Nam", false));
        accountDetails.add((AccountDetail) new Customer("100003", "Customer@2", "customer2", 100000000, "Hoang", LocalDate.of(1998, 04, 27), "0961256789", "hoang@gmail.com", "nationalId", "Ha Noi", "Nam", false));
        openScanner();
        ViewOption.displayMainStartView();
        mainLoop();

        closeScanner();
    }

    static boolean checkPass(String pass) {
        return Pattern.matches(PASSWORD_PATTERN, pass);
    }
    static boolean checkAccountId(String accountID) {
        return Pattern.matches(PASSWORD_PATTERN, accountID);
    }

}