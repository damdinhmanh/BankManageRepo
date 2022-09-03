package controller;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

import javax.lang.model.element.Element;

import model.Account;
import model.AccountDetail;
import model.Admin;
import model.Customer;
import model.DepositDetail;
import model.TransactionHistory;
import model.serviceImpl.CustomerService;
import view.*;
import utils.*;

public class Main{
    public static Scanner scanner;
    static List<AccountDetail> accountDetails = new ArrayList<AccountDetail>();
    private static List<DepositDetail> depositList;
    private static List<TransactionHistory> transactionList;
    static String ACCOUNTID_PATTERN = "{10,15}$";
    static String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()-_[{}]:;',?/*~$^+=<>]).{8,15}$";
    static String EMAIL_PATTERN = "^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$";
    public static Account listAcc;

    public static void openScanner() {
        scanner  = new Scanner(System.in);
        
        cusService = new CustomerService(); //----Hoang them
        cusService.openScanner();  //--------Hoang them
    }

    public static void closeScanner() {
        scanner.close();
       
        cusService.closeScanner();    //------Hoang them
    }

    // ------------------------------ LOGIN CODE-----------------------------------------

    static public int getRandomNumberUsingInts(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
          .findFirst()
          .getAsInt();
    }

    public static void mainLoop() {
        int userOption;

        ViewOption.displayMainStartView();

        System.out.printf("\n\ninput option: ");
        userOption = scanner.nextInt();

        do {
            switch (userOption) {
                case ConstantVars.OPTION_LOGIN_PAGE: {
                    loginBankingPage();
                    userOption = ConstantVars.OPTION_NONE_PAGE;
                    break;
                }

                case ConstantVars.OPTION_REG_ONLINE_PAGE: {
                    registerAccount();
                    userOption = ConstantVars.OPTION_NONE_PAGE;
                    break;
                }
            }
        } while (userOption != ConstantVars.OPTION_NONE_PAGE);
    }
    

    public static void showAdminMenu() {
        System.out.printf("\n\nChuc nang cua Admin\n");
        System.out.println("[1] Them Account customer");
        System.out.println("[2] Sua Account customer");
        System.out.println("[3] Xoa Account customer");
        System.out.println("[4] Quan li lai suat tiet kiem");
        System.out.println("[5] Kiem tra nguoi dung co phai khac hang tiem nang");
        System.out.println("[6] Quit");

        System.out.printf("Nhap lua chon: ");

        int menu1;
        do {
            menu1 = Integer.parseInt(scanner.nextLine());

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
                    break;
                }
                case 5:{
                    // customerTrustlevel();
                    break;
                }
                case 6: {
                    mainLoop();
                    break;
                }
                default:
                    System.exit(0);
            }
        } while (menu1 != 6);
    }
     
    
    public static void showCustomerMenu() {    // ----Hoang da sua----------------------
       
        int menu2;
        
        do {
            System.out.printf("\n\nChuc nang cua Customer\n");
            System.out.println("[1] Chuyen tien");
            System.out.println("[2] Gui tiet kiem");
            System.out.println("[3] Rut tiet kiem");
            System.out.println("[4] Kiem tra danh sach so tiet kiem");
            System.out.println("[5] Lich su giao dich");
            System.out.println("[6] Kiem tra so du");
            System.out.println("[7] Xem thông tin ca nhân");
            System.out.println("[8] Sửa thông tin");
            System.out.println("[9] Thoat");
    
            System.out.printf("Nhap lua chon: ");

            menu2 = Integer.parseInt(scanner.nextLine());

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
                    break;
                }
                case 5:{
                // Lich su giao dich
                    break;
                }
                case 6:{
                // Kiem tra so du
                    break;
                }
                case 7:{
                // Xem thong tin ca nhan
                    cusService.showInfoCustomer(accountIDLogin, accountDetails);
                    System.out.println("\n\nNhan mot phim bat ky cong \"Enter\" de thoat");
                    scanner.nextLine();
                    break;
                }
                case 8: {
                // Sua thong tin
                    updateInfoCustomer();
                    break;
                }
                case 9: {
                    mainLoop();
                    break;
                }
                default:
                    System.exit(0);
            }
        } while (menu2 != 9);
    }
    public static String accountIDLogin; //--Login theo bien nay (Hoang them)

    public static void loginBankingPage() {
        System.out.println("----Login Page----");
        scanner.nextLine();

        String loginType = "";

        boolean kiemTraGiaTriAccountId = false;

        do {
            System.out.print("Nhap accountId: ");
            accountIDLogin = scanner.nextLine();
            if(checkAccountId(accountIDLogin) == false){
                System.out.println("Ban nhap sai dinh dang!!! ");
                kiemTraGiaTriAccountId = false;
            }else{
                for (AccountDetail accountDetail : accountDetails) {
                    if (accountIDLogin.equals(accountDetail.getAccountId())) {
                        kiemTraGiaTriAccountId = true;
                        break;
                    } 
                }

                if (kiemTraGiaTriAccountId == false) {
                    System.out.println("Kiem tra lai AccountId!!!");
                }
            }

        } while(kiemTraGiaTriAccountId == false);

        boolean kiemTraGiatriPassword = false;

        do {
            System.out.print("Nhap Password: ");
            String password = scanner.nextLine();
            if(checkPass(password) == false){
                System.out.println("Ban nhap sai dinh dang!!! ");
                kiemTraGiatriPassword = false;
            }else{
                for (AccountDetail accountDetail : accountDetails) {
                    if (password.equals(accountDetail.getPassword()) && accountIDLogin.equals(accountDetail.getAccountId())) {
                        kiemTraGiatriPassword = true;
                        loginType = accountDetail.getRole(); 
                        break;
                    }
                }

                if (kiemTraGiatriPassword == false) {
                    System.out.println("Kiem tra lai Password!!!");
                }
            }
        } while(kiemTraGiatriPassword == false);
        
        while(true){
            int newCapcha = getRandomNumberUsingInts(10000, 99999);
            System.out.println("Nhap ma bao ve sau: " + newCapcha);
            System.out.print("Nhap ma bao ve: ");
            int inputCapcha = scanner.nextInt();
            
            if(newCapcha == inputCapcha){
                //System.out.println("Nhap dung capcha");
                break;
            }else{
                System.out.println("Ban da nhap sai ma bao ve , Yeu cau nhap lai!!!");
            }
        }

        System.out.printf("\nXin chao mung! Ban da dang nhap thanh cong !");
        scanner.nextLine();

        if (loginType.equals(ConstantVars.LOGIN_AS_ADMIN)) {
            showAdminMenu();
        } else if (loginType.equals(ConstantVars.LOGIN_AS_CUSTOMER)) {
            showCustomerMenu();
        }
       
    }
    

    
    static boolean checkPass(String pass) {
        return Pattern.matches(PASSWORD_PATTERN, pass);
    }
    static boolean checkAccountId(String accountID) {
        boolean isMatchFormat = true;

        if (accountID.length() != 6) {
            isMatchFormat = false;
            return isMatchFormat;
        }

        for(int idx = 0; idx < accountID.length(); idx++) {
            char aCharacter = accountID.charAt(idx);
            if (aCharacter < '0' && aCharacter > '9') {
                isMatchFormat = false;
            }
        }

        return isMatchFormat;
    }

    //---------------END LOGIN-------------------------------

    ///--------------HOANG----------------------------------
    public static boolean checkPhone(String phoneNum) {
        String PHONE_PATTERN = "^[0-9]*$";
        return Pattern.matches(PHONE_PATTERN, phoneNum);
    }
    public static boolean hasPhone(String phoneNum, List<AccountDetail> accountDetails) {
        boolean isHas = false;
        for (AccountDetail acc : accountDetails) {
            if (acc instanceof Customer) {
                Customer cus = (Customer) acc;
                if (cus.getPhoneNum().equals(phoneNum)) {
                    isHas = true;
                    break;
                }
            }
        }
        return isHas;
    }

    public static boolean truePass(String password) {
        return Pattern.matches(PASSWORD_PATTERN, password);
    }

 
    public static boolean trueEmail(String email) {
         return Pattern.matches(EMAIL_PATTERN, email);
    }
    public static boolean isBirthday(String birthday) {
        String BIRTHDAY_PATTERN = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$";
        return Pattern.matches(BIRTHDAY_PATTERN, birthday);
    }
    public static boolean hasEmail(String email, List<AccountDetail> accountDetails) {
        boolean isHas = false;
        for (AccountDetail acc : accountDetails) {
            if (acc instanceof Customer) {
                Customer cus = (Customer) acc;
                if (cus.getEmail().equals(email)) {
                    isHas = true;
                    break;
                }
            }
        }
        return isHas;
    }
    public static Customer hasNationalID(String nationalID, List<AccountDetail> accountDetails) {
        Customer cusReturn = null;
        for (AccountDetail acc : accountDetails) {
            if (acc instanceof Customer) {
                Customer cus = (Customer) acc;
                if (cus.getNationalId().equals(nationalID)) {
                    cusReturn = cus;
                    break;
                }
            }
        }
        return cusReturn;
    }


    public static void registerAccount(){
        System.out.printf("\n\nYou are in: Register New Account Page\n\n");
        System.out.print("Enter name:   ");
        String name = scanner.nextLine();
        String birthday;
        while (true) {
            System.out.print("Enter birthday(dd/MM/yyyy):   ");
            birthday = scanner.nextLine();
            if (isBirthday(birthday)) {
                break;
            } else {
                System.out.println("This birthday is not valid");
            }
        }
        
        String phoneNum;
        while (true) {
            System.out.print("Enter phone number:   ");
            phoneNum = scanner.nextLine();
            if (checkPhone(phoneNum) == false) {
                System.out.println("This phone number is not valid");
            } else if (hasEmail(phoneNum, accountDetails)){
                System.out.println("This phone number is already used");
            } else {
                break;
            }
         }
        String email;
        while (true){
            System.out.print("Enter email:  ");
            email = scanner.nextLine();
            if (trueEmail(email) == false) {
                System.out.println("This email is not valid");
            } else if (hasEmail(email, accountDetails)){
                System.out.println("This email is already used");
            } else {
                break;
            }
         }
         System.out.print("Enter address:  ");
         String address = scanner.nextLine();
         System.out.print("Enter gender:  ");
         String gender = scanner.nextLine();

         System.out.print("Enter national ID:  ");
         String nationalId = scanner.nextLine();
         Customer regCus = hasNationalID(nationalId, accountDetails);

         if (regCus != null) {
            if (regCus.getEnabled() == true) {
                System.out.println("This nationalId is already used, can not register account with this nationalId");
            } else {
                while (true) {
                    System.out.printf("Enter password: ");
                    String password = scanner.nextLine();
                    if (truePass(password)) {
                        int newid = getRandomNumberUsingInts(100000, 999999);
                        String accountId = Integer.toString(newid);
    
                        regCus.setName(name);;
                        regCus.setBirthday(birthday);
                        regCus.setEmail(email);
                        regCus.setPhoneNum(phoneNum);
                        regCus.setAddress(address);
                        regCus.setGender(gender);
                        regCus.setAccountId(accountId);
                        regCus.setPassword(password);
                        regCus.setRole("customer");
                        regCus.setIsAccountEnabled(true);
    
                        System.out.println("\nWelcome to you have successfully registered!\n");
    
                        System.out.println(regCus.toString());
    
    
                        break;                   
                    } else {
                        System.out.println("Password must be between 8 and 15 characters and must contain uppercase, lowercase letters and numbers");
                    }
                }

            }
           
         } else {
            while (true) {
                
                System.out.printf("Enter password: ");
                String password = scanner.nextLine();
                if (truePass(password)) {
                    int newid = getRandomNumberUsingInts(100000, 999999);
                    String accountId = Integer.toString(newid);
                    AccountDetail customerAcc = new Customer(accountId, password, "customer", 2000000, name, birthday, 
                                               phoneNum, email, nationalId, address, gender,
                                               50, true, 0, depositList, transactionList);

                    accountDetails.add(customerAcc);
                    System.out.println(customerAcc.toString());
                    System.out.println("\nWelcome to you have successfully registered!\n");
                    break;                   
                } else {
                    System.out.println("Password must be between 8 and 15 characters and must contain uppercase, lowercase letters and numbers");
                }
            }
         }
         
         //Đăng ký thành công quay về trang chủ
         mainLoop();
    }
    public static void showUpdateInfoCusMenu() {
        System.out.println("\nTrang chinh sua thong tin");
        System.out.println("[1] Sua password");
        System.out.println("[2] Sua ten");
        System.out.println("[3] Sua ngay sinh ");
        System.out.println("[4] Sua so dien thoai");
        System.out.println("[5] Sua email");
        System.out.println("[6] Sua dia chi");
        System.out.println("[7] Sua gioi tinh");
        System.out.println("[8] Thoat");

        System.out.printf("Nhap lua chon: ");
    }
    public static CustomerService cusService;
    public static void updateInfoCustomer(){
        
        
        int menuUpdateInfo;
        do {
            showUpdateInfoCusMenu();
            menuUpdateInfo = Integer.parseInt(scanner.nextLine());
            switch (menuUpdateInfo) {
                case 1:{
                // Sua password
                    cusService.updateAccPassword(accountIDLogin, accountDetails);
                    break;
                }
                case 2: {
                // Sua ten
                    cusService.updateAccName(accountIDLogin, accountDetails);
                    break;
                }
                case 3: {
                // Sua ngay sinh
                    cusService.updateAccBirthday(accountIDLogin, accountDetails);
                    break;
                }
                case 4: {
                // Sua so dien thoai
                    cusService.updateAccPhoneNum(accountIDLogin, accountDetails);
                    break;
                }
                case 5:{
                // Sua email
                    cusService.updateAccEmail(accountIDLogin, accountDetails);
                    break;
                }
                case 6:{
                // Sua dia chi
                    cusService.updateAccAddress(accountIDLogin, accountDetails);
                    break;
                }
                case 7:{
                // Sua gioi tinh
                    cusService.updateAccGender(accountIDLogin, accountDetails);
                    break;
                }
                case 8: {
                //  Exit
                    break;
                }
                default:
                    System.exit(0);
            }
        } while (menuUpdateInfo != 8);


    }

    //-----------HOANG END---------------------

    //-----------MAIN--------------------------
    public static void main(String[] args) { 
        //TEST data
        accountDetails.add((AccountDetail) new Admin("100001", "Admin@123", "admin"));
        accountDetails.add((AccountDetail) new Customer("123456", "Hoang123", "customer", 15000000, "Hoang", "18/06/1998",
        "021932184", "Hoang@gmail.com", "123456", "address", "MALE", 90,
        true, 200000, depositList, transactionList));
        accountDetails.add((AccountDetail) new Customer("123457", "123", "customer", 15000000, "Hoang", "18/06/1998",
        "021932184", "Hoang@gmail.com", "123457", "address", "MALE", 90,
        true, 200000, depositList, transactionList));
        //-----------


        openScanner();
        
        mainLoop();

        closeScanner();
    }
}