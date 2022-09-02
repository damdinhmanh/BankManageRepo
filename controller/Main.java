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
import model.Customer;
import model.DepositDetail;
import model.TransactionHistory;
import view.*;
import utils.*;

public class Main{
    public static Scanner scanner;
    static List<AccountDetail> accountDetails = new ArrayList<AccountDetail>();
    private static List<DepositDetail> depositList;
    private static List<TransactionHistory> transactionList;
    static AccountDetail acc1 =new Customer("123456", "123", "customer", 15000000, "Hoang", "18/06/1998",
                                            "021932184", "Hoang@gmail.com", "123456", "address", "MALE", 90,
                                            true, 200000, depositList, transactionList);
    static String ACCOUNTID_PATTERN = "{10,15}$";
    static String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()-_[{}]:;',?/*~$^+=<>]).{8,15}$";
    static String EMAIL_PATTERN = "^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$";
    public static Account listAcc;

    public static void openScanner() {
        scanner = new Scanner(System.in);
    }

    public static void closeScanner() {
        scanner.close();
    }
// ---------------------------------------------------------------------------- Hoang code

    static public int getRandomNumberUsingInts(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
          .findFirst()
          .getAsInt();
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
        accountDetails.add(acc1); //them tam de thu
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
                System.out.println("This nationalId is already used, can not register account with this nationalId");
            } else {
                while (true) {
                    System.out.println("Enter password: ");
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
                
                System.out.println("Enter password: ");
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
         
               
    }



    // ------------------------------------------------------------------------ The End

    public static void mainLoop() {
        int userOption = 0;
        userOption = scanner.nextInt();
        scanner.nextLine();

        while (true) {
            switch (userOption) {
                case ConstantVars.OPTION_LOGIN_PAGE: {
                    //Hieu code
                    break;
                }

                case ConstantVars.OPTION_REG_ONLINE_PAGE: {
                    registerAccount();
                    //Hoang code      
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