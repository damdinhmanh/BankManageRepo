package controller;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Pattern;

import javax.lang.model.element.Element;

import model.Account;
import model.AccountDetail;
import model.Admin;
import model.Customer;
import model.DepositDetail;
import model.KnowYourCustomer;
import model.TransactionHistory;
import model.serviceImpl.AdminService;
import model.serviceImpl.CustomerService;
import view.*;
import utils.*;

public class Main{
    public static Scanner scanner;
    static List<AccountDetail> accountDetails = new ArrayList<AccountDetail>();
    private static AdminService adminSeviceManager;

    static String ACCOUNTID_PATTERN = "{10,15}$";
    static String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()-_[{}]:;',?/*~$^+=<>]).{8,15}$";
    static String EMAIL_PATTERN = "^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$";
    public static Account listAcc;

    public static void openSession() {
        adminSeviceManager = new AdminService();
        adminSeviceManager.initAdminService();

        scanner  = new Scanner(System.in);

        cusService = new CustomerService(); //----Hoang them
        cusService.openScanner(scanner);  //--------Hoang them
    }

    public static void closeSession() {
        scanner.close();
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
                case ConstantVars.OPTION_EXIT_PROGRAME: {
                    System.exit(0);
                    break;
                }
            }
        } while (userOption != ConstantVars.OPTION_NONE_PAGE);
    }

    public static void adminSavingInterestPage() {
        System.out.printf("\n\nQuan li lai suat tiet kiem Page\n");
        System.out.println("[1] Update lai suat tiet kiem");
        System.out.println("[2] Kiem tra lai suat tiet kiem hien tai");
        System.out.println("[3] Quay lai-> Chuc nang cua Admin");

        int menu1;
        do {
            System.out.printf("Nhap lua chon: ");
            menu1 = Integer.parseInt(scanner.nextLine());

            switch (menu1) {
                case 1:{
                    System.out.printf("Nhap lai suat: ");
                    double newInterestRate = Double.parseDouble(scanner.nextLine());
                    adminSeviceManager.updateInterestRate(newInterestRate);
                    System.out.println("Cap nhat thanh cong\n");
                    break;
                }
                case 2: {
                    System.out.println("Lai suat huy dong hien tai la: " + adminSeviceManager.getInterestRate());
                    break;
                }
                case 3: {
                    showAdminMenu();
                    break;
                }
            }
        } while (true);
    }

    public static void adminAddNewAccount() {
        System.out.printf("\n\nYou are in: Admin add Account Page\n\n");
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
        System.out.print("Enter phone number:   ");
        String phoneNum = scanner.nextLine();
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
                System.out.println("ERROR: This nationalId is already used, can not add account with this nationalId");
                showAdminMenu();
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
                                               50, true, 0, null, null);
                         
                    accountDetails.add(customerAcc);
                    System.out.println(customerAcc.toString());
                    System.out.println("\nyou have successfully added a account!\n");
                    System.out.println("MYdebug--->" + accountDetails.toString());
                    break;
                } else {
                    System.out.println("Password must be between 8 and 15 characters and must contain uppercase, lowercase letters and numbers");
                }
            }
        }

        showAdminMenu();
    }

    public static void adminShowAccountListInfor() {
        System.out.println("----------Account List Infors:----------------");
        for (AccountDetail accountDetail : accountDetails) {
            if(accountDetail.getRole().equals(ConstantVars.LOGIN_AS_ADMIN)) {
                System.out.println(accountDetail.toString());
            } else if (accountDetail.getRole().equals(ConstantVars.LOGIN_AS_CUSTOMER)) {
                Customer customer = (Customer) accountDetail;
                System.out.println(customer.toString());
            }
        }
        System.out.println("----------////////////////////----------------");
    }

    public static void adminUpdateTrustLevel () {
        System.out.printf("Nhap so CCCD: ");
        String nationalID = scanner.nextLine();

        System.out.printf("Nhap trust level [0-100]: ");
        int newTrustLevel = Integer.parseInt(scanner.nextLine());

        for (AccountDetail accountDetail : accountDetails) {
            if (accountDetail.getRole().equals(ConstantVars.LOGIN_AS_CUSTOMER)) {
                Customer customer = (Customer) accountDetail;
                if (customer.getNationalId().equals(nationalID)) {
                    customer.setTrustLevel(newTrustLevel);
                }
            }
        }
    }

    public static void adminUpdatePassword () {
        System.out.printf("Nhap Account ID: ");
        String updateAccountID = scanner.nextLine();

        while (true) {         
            System.out.printf("Enter new password: ");
            String password = scanner.nextLine();

            if (truePass(password)) {   
                for (AccountDetail accountDetail : accountDetails) {
                    if (accountDetail.getRole().equals(ConstantVars.LOGIN_AS_CUSTOMER)) {
                        Customer customer = (Customer) accountDetail;
                        if (customer.getAccountId().equals(updateAccountID)) {
                            customer.setPassword(password);
                        }
                    }
                }

                break;
            } else {
                System.out.println("Password must be between 8 and 15 characters and must contain uppercase, lowercase letters and numbers");
            }
        }

        System.out.println("Cap nhat password thanh cong!");
    }

    public static void adminUpdateName () {
        System.out.printf("Nhap Account ID: ");
        String updateAccountID = scanner.nextLine();

        System.out.print("Enter new name:   ");
        String name = scanner.nextLine();

        for (AccountDetail accountDetail : accountDetails) {
            if (accountDetail.getRole().equals(ConstantVars.LOGIN_AS_CUSTOMER)) {
                Customer customer = (Customer) accountDetail;
                if (customer.getAccountId().equals(updateAccountID)) {
                    customer.setName(name);
                }
            }
        }

        System.out.println("Cap nhat ten thanh cong!");
    }

    public static void adminUpdateBirthday () {
        System.out.printf("Nhap Account ID: ");
        String updateAccountID = scanner.nextLine();

        String birthday;
        while (true) {
            System.out.print("Enter birthday(dd/MM/yyyy):   ");
            birthday = scanner.nextLine();

            if (isBirthday(birthday)) {
                for (AccountDetail accountDetail : accountDetails) {
                    if (accountDetail.getRole().equals(ConstantVars.LOGIN_AS_CUSTOMER)) {
                        Customer customer = (Customer) accountDetail;
                        if (customer.getAccountId().equals(updateAccountID)) {
                            customer.setBirthday(birthday);
                        }
                    }
                }
                break;
            } else {
                System.out.println("This birthday is not valid");
            }
        }

        System.out.println("Cap nhat birthday thanh cong!");
    }

    public static void adminUpdatePhonNum () {
        System.out.printf("Nhap Account ID: ");
        String updateAccountID = scanner.nextLine();

        String phoneNum;
        while (true) {
            System.out.print("Enter phone number:   ");
            phoneNum = scanner.nextLine();
            if (checkPhone(phoneNum) == false) {
                System.out.println("This phone number is not valid");
            } else if (hasPhone(phoneNum, accountDetails)){
                System.out.println("This phone number is already used");
            } else {
                break;
            }
         }

         for (AccountDetail accountDetail : accountDetails) {
            if (accountDetail.getRole().equals(ConstantVars.LOGIN_AS_CUSTOMER)) {
                Customer customer = (Customer) accountDetail;
                if (customer.getAccountId().equals(updateAccountID)) {
                    customer.setPhoneNum(phoneNum);
                }
            }
        }

        System.out.println("Cap nhat phoneNum thanh cong!");
    }

    public static void adminUpdateEmail () {
        System.out.printf("Nhap Account ID: ");
        String updateAccountID = scanner.nextLine();

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

         for (AccountDetail accountDetail : accountDetails) {
            if (accountDetail.getRole().equals(ConstantVars.LOGIN_AS_CUSTOMER)) {
                Customer customer = (Customer) accountDetail;
                if (customer.getAccountId().equals(updateAccountID)) {
                    customer.setEmail(email);
                }
            }
        }

        System.out.println("Cap nhat email thanh cong!");
    }

    public static void adminUpdateAdress () {
        System.out.printf("Nhap Account ID: ");
        String updateAccountID = scanner.nextLine();
        System.out.print("Enter address:  ");
        String address = scanner.nextLine();

        for (AccountDetail accountDetail : accountDetails) {
            if (accountDetail.getRole().equals(ConstantVars.LOGIN_AS_CUSTOMER)) {
                Customer customer = (Customer) accountDetail;
                if (customer.getAccountId().equals(updateAccountID)) {
                    customer.setAddress(address);
                }
            }
        }

        System.out.println("Cap nhat dia chi thanh cong!");

    }

    public static void adminUpdateGender () {
        System.out.printf("Nhap Account ID: ");
        String updateAccountID = scanner.nextLine();
        System.out.print("Enter gender:  ");
        String gender = scanner.nextLine();

        
        for (AccountDetail accountDetail : accountDetails) {
            if (accountDetail.getRole().equals(ConstantVars.LOGIN_AS_CUSTOMER)) {
                Customer customer = (Customer) accountDetail;
                if (customer.getAccountId().equals(updateAccountID)) {
                    customer.setGender(gender);
                }
            }
        }

        System.out.println("Cap nhat gioi tinh thanh cong!");
    }

    public static void adminUpdateAccountEnableDisable () {
        System.out.printf("Nhap Account ID: ");
        String updateAccountID = scanner.nextLine();
        System.out.print("Enter Account status (Enable: 1, Disable: 0): ");
        String accIsEnable = scanner.nextLine();


        boolean isEnableThisAccount = true;

        if (accIsEnable.equals("1")) {
            isEnableThisAccount = true;
        } else if (accIsEnable.equals("0")) {
            isEnableThisAccount = false;
        }

        for (AccountDetail accountDetail : accountDetails) {
            if (accountDetail.getRole().equals(ConstantVars.LOGIN_AS_CUSTOMER)) {
                Customer customer = (Customer) accountDetail;
                if (customer.getAccountId().equals(updateAccountID)) {
                    customer.setIsAccountEnabled(isEnableThisAccount);
                }
            }
        }

        System.out.println("Cap nhat trang thai account thanh cong!");
    }


    public static void adminChangeAccountInfor() {
        System.out.println("[1] Cap nhat Password");
        System.out.println("[2] Cap nhat Name");
        System.out.println("[3] Cap nhat Birthday");
        System.out.println("[4] Cap nhat phoneNum");
        System.out.println("[5] Cap nhat email");
        System.out.println("[6] Cap nhat dia chi");
        System.out.println("[7] Cap nhat gioi tinh");
        System.out.println("[8] Cap nhat trustlevel");
        System.out.println("[9] Cap nhat account Enable");
        System.out.println("[10] Quay lai-> trang Admin");

        int menu1;
        do {
            System.out.printf("Nhap lua chon: ");
            menu1 = Integer.parseInt(scanner.nextLine());

            switch (menu1) {
                case 1:{
                    adminUpdatePassword();
                    break;
                }
                case 2: {
                    adminUpdateName();
                    break;
                }
                case 3: {
                    adminUpdateBirthday();
                    break;
                }
                case 4: {
                    adminUpdatePhonNum();
                    break;
                }
                case 5:{
                    adminUpdateEmail();
                    break;
                }
                case 6: {
                    adminUpdateAdress();
                    break;
                }
                case 7: {
                    adminUpdateGender();
                    break;
                }
                case 8: {//trustlevel
                    adminUpdateTrustLevel();
                    break;
                }
                case 9: {
                    adminUpdateAccountEnableDisable();
                    break;
                }
                case 10: {
                    showAdminMenu();
                    break;
                }
                default:
                    System.exit(0);
            }
        } while (menu1 != 6);
    }

    public static void adminDeleteAccount() {
        System.out.printf("Nhap so AccountID can xoa: ");
        String accountIdDel = scanner.nextLine();

        Iterator<AccountDetail> iterator = accountDetails.iterator();

        while(iterator.hasNext()) {
            AccountDetail acc = iterator.next();

            if (acc.getAccountId().equals(accountIdDel)) {
                iterator.remove();
                break;
            }
        }
    }
    
    public static void showAdminMenu() {
        System.out.printf("\n\nChuc nang cua Admin\n");
        System.out.println("[1] Them Account customer");
        System.out.println("[2] Sua Account customer");
        System.out.println("[3] Xoa Account customer");
        System.out.println("[4] Quan ly lai suat tiet kiem");
        System.out.println("[5] Show thong tin Account List");
        System.out.println("[6] Quit");

        int menu1;
        do {
            System.out.printf("Nhap lua chon: ");
            menu1 = Integer.parseInt(scanner.nextLine());

            switch (menu1) {
                case 1:{
                    adminAddNewAccount();
                    break;
                }
                case 2: {
                    adminChangeAccountInfor();
                    break;
                }
                case 3: {
                    adminDeleteAccount();
                    break;
                }
                case 4: {
                    adminSavingInterestPage();
                    break;
                }
                case 5:{
                    adminShowAccountListInfor();
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

    public static void customerTransferMondey() {
        System.out.printf("Nhap so tai khoan chuyen den: ");
        String toAccID = scanner.nextLine();

        Customer customerTran = null;

        for (AccountDetail acc : accountDetails) {
            if (acc instanceof Customer) {
                Customer cus = (Customer) acc;

                if (cus.getAccountId().equals(accountIDLogin)) {
                    customerTran = cus;
                }
            }
        }

        boolean tranValid = false;
        int tranMonAmount = 0;

        do {
            System.out.printf("Nhap so tien can chuyen: ");
            tranMonAmount = Integer.parseInt(scanner.nextLine());
            tranValid = true;

            if (tranMonAmount > customerTran.getBalance()) {
                System.out.println("So du kha dung khong du. Xin nhap lai!");
                tranValid = false;
            }

        } while (tranValid == false);

        cusService.transferMoney(accountIDLogin, toAccID, accountDetails, tranMonAmount);

    }

    public static void customerShowTransactionHistory() {
        cusService.showCustomerTransHist(accountIDLogin, accountDetails);
    }

    public static void customerSaveMoney() {
        boolean tranValid = false;
        int tranMonAmount = 0;
        Customer customerTran = null;

        for (AccountDetail acc : accountDetails) {
            if (acc instanceof Customer) {
                Customer cus = (Customer) acc;

                if (cus.getAccountId().equals(accountIDLogin)) {
                    customerTran = cus;
                }
            }
        }

        do {
            System.out.printf("Nhap so tien gui tiet kiem: ");
            tranMonAmount = Integer.parseInt(scanner.nextLine());
            tranValid = true;

            if (tranMonAmount > customerTran.getBalance()) {
                System.out.println("So du kha dung khong du. Xin nhap lai!");
                tranValid = false;
            }

        } while (tranValid == false);

        System.out.printf("Nhap ky han (so nam): ");
        int depTermVal = Integer.parseInt(scanner.nextLine());

        cusService.savingDepositTerm(accountIDLogin, tranMonAmount, depTermVal, accountDetails, adminSeviceManager.getInterestRate());
    }

    public static void showDepositListInfor() {
        System.out.println("Tong so tien tiet kiem: " + cusService.getDepositTotalAmountFromService(accountIDLogin, accountDetails) + "VND");

        cusService.showCustomerDepositHist(accountIDLogin, accountDetails);
    }

    public static void withDrawSaveMoney() {
        boolean tranValid = false;
        int depID = 0;
        Customer customerTran = null;

        do {
            System.out.printf("Nhap ID so tiet kiem: ");
            depID = Integer.parseInt(scanner.nextLine());

            for (AccountDetail acc : accountDetails) {
                if (acc instanceof Customer) {
                    Customer cus = (Customer) acc;

                    if (cus.getAccountId().equals(accountIDLogin)) {
                        customerTran = cus;
                    }
                }
            }

            List<DepositDetail> depListCus = customerTran.getDepositList();

            for (DepositDetail depositDetail : depListCus) {
                if (depositDetail.getDepositID()==depID) {
                    tranValid = true;
                }
            }

            if (tranValid==false) {
                System.out.println("So ID so tiet kiem khong dung, Xin nhap lai!");
            }
        } while (tranValid == false);

        //rut so
        cusService.withDrawSaving(accountIDLogin, depID, accountDetails);

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
                    customerTransferMondey();
                    break;
                }
                case 2: {
                    customerSaveMoney();
                    break;
                }
                case 3: {
                    withDrawSaveMoney();
                    break;
                }
                case 4: {
                    showDepositListInfor();
                    break;
                }
                case 5:{
                    customerShowTransactionHistory();
                    break;
                }
                case 6:{
                    cusService.getCusBalance(accountIDLogin, accountDetails);
                    break;
                }
                case 7:{
                // Xem thong tin ca nhan
                    cusService.showInfoCustomer(accountIDLogin, accountDetails);
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


        for (AccountDetail acc : accountDetails) {
            if (acc instanceof Customer) {
                Customer cus = (Customer) acc;

                if (cus.getAccountId().equals(accountIDLogin)) {
                    if (cus.getEnabled()==false) {
                        System.out.println("Xin loi tai khoan nay chua duoc kich hoat, vui long kiem tra lai!");
                        mainLoop();
                    }
                }
            }
        }

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
        scanner.nextLine();
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
            } else if (hasPhone(phoneNum, accountDetails)){
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
                        //Apply KYC Verify new customer
                        AccountDetail customerDetail = (AccountDetail) regCus;
                        if (KnowYourCustomer.verifyCustomer(nationalId, accountDetails, customerDetail) == true) {
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

                            List<DepositDetail> depositListReg = new ArrayList<DepositDetail>();
                            List<TransactionHistory> transactionListReg = new ArrayList<TransactionHistory>();

                            regCus.setDepositList(depositListReg);
                            regCus.setTransactionList(transactionListReg);


                            System.out.println("\nWelcome to you have successfully registered!\n");
        
                            System.out.println("MYdebug--->" + accountDetails.toString());
                        } else {
                            System.out.println("Dang ky khong thanh cong do lich su tin dung cua ban khong dat.");
                            System.out.printf("\nYeu cau dang ky truc tiep tai phong giao dich\n\n");
                        }
  
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

                    List<DepositDetail> depositListReg2 = new ArrayList<DepositDetail>();
                    List<TransactionHistory> transactionListReg2 = new ArrayList<TransactionHistory>();

                    AccountDetail customerAcc = new Customer(accountId, password, "customer", 2000000, name, birthday, 
                                               phoneNum, email, nationalId, address, gender,
                                               50, true, 0, depositListReg2, transactionListReg2);
                         
                    accountDetails.add(customerAcc);
                    System.out.println(customerAcc.toString());
                    System.out.println("\nWelcome to you have successfully registered!\n");
                    System.out.println("MYdebug--->" + accountDetails.toString());
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

        //Test Acc1
        List<DepositDetail> depositListHoang = new ArrayList<DepositDetail>();
        List<TransactionHistory> transactionListHoang = new ArrayList<TransactionHistory>();

        accountDetails.add((AccountDetail) new Customer("123456", "Hoang@123", "customer", 15000000, "Hoang", "18/06/1998",
        "021932184", "Hoang@gmail.com", "123456", "address", "MALE", 10,
        true, 0, depositListHoang, transactionListHoang));

        //Test Acc2
        List<DepositDetail> depositList2 = new ArrayList<DepositDetail>();
        List<TransactionHistory> transactionList2 = new ArrayList<TransactionHistory>();

        accountDetails.add((AccountDetail) new Customer("666666", "Test@123", "customer", 15000000, "Hoang", "18/06/1998",
        "021932184", "Hoang@gmail.com", "123457", "address", "MALE", 90,
        true, 0, depositList2, transactionList2));
        //-----------

        openSession();
        
        mainLoop();

        closeSession();
    }
}