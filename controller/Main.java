package controller;
import java.util.Scanner;
import view.*;
import utils.*;
import model.AccountDetail;
import model.Admin;
import model.Customer;
import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.management.relation.Role;

public class Main{
    static Scanner scanner = new Scanner(System.in);
    static List<AccountDetail> accountDetails = new ArrayList<AccountDetail>();

    static String PASSWORD_PATTERN =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()-_[{}]:;',?/*~$^+=<>]).{8,15}$";
    // static String EMAIL_PATTERN =
    //         "^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$";
    public static void openScanner() {
        scanner  = new Scanner(System.in);
    }
    public static void mainLoop() {
        int userOption = 0;

        System.out.printf("\n\ninput option: ");
        userOption = scanner.nextInt();

        while (true) {
            switch (userOption) {
                case ConstantVars.OPTION_LOGIN_PAGE: {
                    Login();
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
    }
    private static void showMenu(String role) {
        System.out.println("Chuc nang cua " + role);
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
        
            System.out.println("Chuc nang cua " + role);
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

    public static void Login() {
        
        System.out.println("----Login Page----");
        System.out.println();
        scanner.nextLine();
        System.out.print("Nhap accountId : ");
        String accountId = scanner.nextLine();
        System.out.println();

        System.out.print("Nhap password : ");
        String pass = scanner.nextLine();
        System.out.println();

        System.out.print("Nhap role : ");
        String role = scanner.nextLine();
        System.out.println();

        // Tạo điều kiện kiểm tra AccountId để đăng nhập
        boolean checkExistAccountId = false;
        for (AccountDetail accountDetail : accountDetails) {
            if (accountId.equals(accountDetail.getAccountId())) {
                checkExistAccountId = true;
                break;
            }
        }
        if (!checkExistAccountId) {
            System.out.println("Kiem tra lai AccountId"); //nếu accountid sai yêu cầu kiểm tra lại accountid!!!
            Login();
        }

        // Dùng equals để so sánh vs AccountId and password ban đầu !!!
        boolean checkExistAccountDetail = false;
        for (AccountDetail accountDetail : accountDetails) {
            if (accountId.equals(accountDetail.getAccountId()) && pass.equals(accountDetail.getPassword())) {
                checkExistAccountDetail = true;
                break;
            }
        }
        // Kiểm tra role đề đăng nhập
        boolean checkExistRole = false;
        for (AccountDetail accountDetail : accountDetails) {
            if (role.equals(accountDetail.getRole())) {
                checkExistRole = true;
                break;
            }
        }
    
        if (!checkExistAccountDetail) {
            int choose;
            System.out.println("1. Dang nhap lai");
            System.out.println("2. Quen mat khau");
            do {
                choose = Integer.parseInt(scanner.nextLine());

                switch (choose) {
                    case 1:
                        mainLoop();
                        return;
                    default:
                        quenPass();
                        return;
                }
            } while (choose != 1);
        }
        showMenu(role);
    }
    static void quenPass() {
        boolean checkExistAccountDetail = false;
        System.out.print("Nhap Role : ");
        String role = scanner.nextLine();
        System.out.println();

        for (AccountDetail accountDetail : accountDetails) {
            if (role.equals(accountDetail.getRole())) {
                checkExistAccountDetail = true;
                thaoTacDoiPass();
                showMenu(role);
                break;
            }
        }

        if (!checkExistAccountDetail) {
            System.out.println("role khong ton tai");
            Login();
        }
    }

    static void thaoTacDoiPass() {
        System.out.print("Nhap mat khau moi: ");
        String pass = scanner.nextLine();
        for(AccountDetail accountDetail : accountDetails){
        if (!checkPass(pass)) {
            System.out.println("Mat khau khong dung dinh dang");
            thaoTacDoiPass();
        }
            accountDetail.setPassword(pass);
        System.out.println("Doi mat khau thanh cong");
            return;
        }
    }

    public static void closeScanner() {
        scanner.close();
    }


    public static void main(String[] args) { 
        accountDetails.add((AccountDetail) new Admin("123456", "Admin@123", "admin"));
        accountDetails.add((AccountDetail) new Customer("000001", "Customer@1", "customer1", 100000000, "Hieu", LocalDate.of(1999, 07, 27), "0966759751", "hieu@gmail.com", "nationalId", "Ha Noi", "Nam", false));
        accountDetails.add((AccountDetail) new Customer("000002", "Customer@2", "customer2", 100000000, "Hoang", LocalDate.of(1998, 04, 27), "0961256789", "hoang@gmail.com", "nationalId", "Ha Noi", "Nam", false));
        
        openScanner();
        ViewOption.displayMainStartView();
        mainLoop();

        closeScanner();
    }
    
    // // static boolean checkEmail(String email) {
    // //     boolean checkExistEmail = false;
    // //     for (AccountDetail accountDetail : accountDetails) {
    // //         if (email.equals(accountDetail.getEmail())) {
    // //             checkExistEmail = true;
    // //         }
    // //     }
    // //     return Pattern.matches(EMAIL_PATTERN, email) && !checkExistEmail;
    // // }

    static boolean checkPass(String pass) {
        return Pattern.matches(PASSWORD_PATTERN, pass);
    }
}