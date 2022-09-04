package model.serviceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

import model.Account;
import model.AccountDetail;
import model.Customer;
import model.DepositDetail;
import model.TransactionHistory;
import model.service.ICustomerService;
import model.serviceImpl.CustomerService;
import utils.Gender;

public class CustomerService implements ICustomerService {
    private Scanner scanner;

    public void openScanner(Scanner mainScanner) {
        this.scanner = mainScanner;
    }

    public void closeScanner() {
    }

    public boolean checkPass(String password) {
        String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()-_[{}]:;',?/*~$^+=<>]).{8,15}$";
        return Pattern.matches(PASSWORD_PATTERN, password);
    }

    public boolean checkBirthday(String birthday) {
        String BIRTHDAY_PATTERN = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$";
        return Pattern.matches(BIRTHDAY_PATTERN, birthday);
    }

    public static boolean checkEmail(String email) {
        String EMAIL_PATTERN = "^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$";
        return Pattern.matches(EMAIL_PATTERN, email);
    }

    public static boolean checkPhone(String phoneNum) {
        String PHONE_PATTERN = "^[0-9]*$";
        return Pattern.matches(PHONE_PATTERN, phoneNum);
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

    public void showInfoCustomer(String accountID, List<AccountDetail> accountDetails) {
        for (AccountDetail acc : accountDetails) {
            if (acc instanceof Customer) {
                Customer cus = (Customer) acc;
                if (cus.getAccountId().equals(accountID)) {
                    System.out.println(">---------------------------------------------------->");
                    System.out.println("\nYour account infors:");
                    System.out.println("Name: " + cus.getName());
                    System.out.println("Birthday: " + cus.getBirthday());
                    System.out.println("Phonenum: " + cus.getPhoneNum());
                    System.out.println("Email: " + cus.getEmail());
                    System.out.println("Address: " + cus.getAddress());
                    System.out.println("Gender: " + cus.getGender());
                    System.out.println("Password: " + cus.getPassword()); 
                    System.out.println("Balance: " + cus.getBalance());
                }
            }
        }
    }

    public Customer returnCustomer(String accountID, List<AccountDetail> accountDetails) {
        Customer customerReturn = null;
        for (AccountDetail acc : accountDetails) {
            if (acc instanceof Customer) {
                Customer cus = (Customer) acc;
                if (cus.getAccountId().equals(accountID)) {
                    customerReturn = cus;
                    break;
                }
            }
        }
        return customerReturn;
    }

    @Override
    public void updateAccPassword(String accountID, List<AccountDetail> accountDetails) {

        String newPassword;
        while (true) {
            System.out.print("Input new password: ");
            newPassword = scanner.nextLine();
            if (checkPass(newPassword)) {
                break;
            } else {
                System.out.println("Password wrong format");
            }
        }
        returnCustomer(accountID, accountDetails).setPassword(newPassword);
        System.out.println("Update password sucessfully!");
    }

    @Override
    public void updateAccName(String accountID, List<AccountDetail> accountDetails) {

        System.out.print("Input new Name: ");
        String newName = scanner.nextLine();
        returnCustomer(accountID, accountDetails).setName(newName);
        System.out.println("Update name sucessfully!");
    }

    @Override
    public void updateAccBirthday(String accountID, List<AccountDetail> accountDetails) {

        String newBirthday;
        while (true) {
            System.out.print("Input new birthday (dd/MM/yyyy): ");
            newBirthday = scanner.nextLine();
            if (checkBirthday(newBirthday)) {
                break;
            } else {
                System.out.println("Birthday need format as dd/MM/yyyy");
            }
        }
        returnCustomer(accountID, accountDetails).setBirthday(newBirthday);

        System.out.println("Update birthday sucessfully!");
    }

    @Override
    public void updateAccPhoneNum(String accountID, List<AccountDetail> accountDetails) {
  
        String newPhone;
        while (true) {
            System.out.print("Input new phonenum:   ");
            newPhone = scanner.nextLine();
            if (checkPhone(newPhone) == false) {
                System.out.println("Phonenum need a numeric format!");
            } else if (hasPhone(newPhone, accountDetails)) {
                System.out.println("Phonenum used for other account!");
            } else {
                break;
            }
        }
        returnCustomer(accountID, accountDetails).setPhoneNum(newPhone);

        System.out.println("Update phonenum sucessfully!");

    }

    @Override
    public void updateAccEmail(String accountID, List<AccountDetail> accountDetails) {

        String newEmail;
        while (true) {
            System.out.print("Input new email:   ");
            newEmail = scanner.nextLine();
            if (checkEmail(newEmail) == false) {
                System.out.println("Email is wrong format!");
            } else if (hasEmail(newEmail, accountDetails)) {
                System.out.println("Email used by other account!");
            } else {
                break;
            }
        }
        returnCustomer(accountID, accountDetails).setEmail(newEmail);
        
        System.out.println("Update email sucessfully!");

    }

    @Override
    public void updateAccAddress(String accountID, List<AccountDetail> accountDetails) {

        System.out.print("Input new address: ");
        String newAddress = scanner.nextLine();
        returnCustomer(accountID, accountDetails).setAddress(newAddress);

        System.out.println("Update address sucessfully!");
    }

    @Override
    public void updateAccGender(String accountID, List<AccountDetail> accountDetails) {

        System.out.print("Input new gender: ");
        String newGender = scanner.nextLine();
        returnCustomer(accountID, accountDetails).setGender(newGender);
        System.out.println("Update gender sucessfully!");
    }

    @Override
    public Account getCusAccountInfor(String accountID) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean regAccountId(String name, LocalDate birthday, String phoneNum, String email, String nationalID,
            String address, Gender gender) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getCusBalance(String accountID, List<AccountDetail> accountDetails) {

        for (AccountDetail acc : accountDetails) {
            if (acc instanceof Customer) {
                Customer cus = (Customer) acc;
                if (cus.getAccountId().equals(accountID)) {
                    System.out.println(">---------------------------------------------------->");
                    System.out.println("\nYour account balance:");
                    System.out.println("Balance: " + cus.getBalance() + "VND");
                }
            }
        }

        return 0;
    }

    @Override
    public void getDepositDetails(String accountID, List<AccountDetail> accountDetails) {
        // TODO Auto-generated method stub

    }

    @Override
    public void getTransactionHistory(String accountID, List<AccountDetail> accountDetails) {
        // TODO Auto-generated method stub

    }

    @Override
    public void savingDepositTerm(String accountID, int depAmount, int depTerm,
            List<AccountDetail> accountDetails, double interestRate) {

        Customer fromCustomer = null;

        for (AccountDetail acc : accountDetails) {
            if (acc instanceof Customer) {
                Customer cus = (Customer) acc;

                if (cus.getAccountId().equals(accountID)) {
                    fromCustomer = cus;
                }
            }
        }

        int fromTranID = getTransHistotryRefID();
        LocalDateTime curDateTime = LocalDateTime.now();
        int fromBalance = fromCustomer.getBalance();
        fromBalance -= depAmount;
        fromCustomer.setBalance(fromBalance);
        String histContent = "Saving Deposit-AccID: " + fromCustomer.getAccountId() + ", Balance changed: -" + depAmount
                + "VND, at " + curDateTime.toString() + ", Balance: " + fromBalance + ", RefID: " + fromTranID;
        TransactionHistory fromTranHist = new TransactionHistory(fromTranID, curDateTime, histContent);
        fromCustomer.addTransactionHist(fromTranHist);

        DepositDetail depositDetail = new DepositDetail(fromTranID, depAmount, curDateTime, depTerm, interestRate);
        fromCustomer.addDepositDetailHist(depositDetail);
        int cusTotalDeposit = fromCustomer.getDepositTotalAmount();
        cusTotalDeposit += depAmount;
        fromCustomer.setDepositTotalAmount(cusTotalDeposit);

        System.out.println("Saving deposit sucessfully!");
        System.out.println(">---------------------------------------------------->");
        System.out.println(histContent);

        return;
    }

    static public int getTransHistotryRefID() {
        Random random = new Random();
        return random.ints(100000000, 999999999)
                .findFirst()
                .getAsInt();
    }

    @Override
    public boolean transferMoney(String fromAccID, String toAccID, List<AccountDetail> accountDetails, int amount) {

        Customer fromCustomer = null;
        Customer toCustomer = null;

        for (AccountDetail acc : accountDetails) {
            if (acc instanceof Customer) {
                Customer cus = (Customer) acc;

                if (cus.getAccountId().equals(fromAccID)) {
                    fromCustomer = cus;
                }

                if (cus.getAccountId().equals(toAccID)) {
                    toCustomer = cus;
                }
            }
        }

        int fromTranID = getTransHistotryRefID();
        LocalDateTime curDateTime = LocalDateTime.now();
        int fromBalance = fromCustomer.getBalance();
        fromBalance -= amount;
        fromCustomer.setBalance(fromBalance);
        String histContent = "Transfer-From AccID: " + fromCustomer.getAccountId() + " To AccID:"
                + toCustomer.getAccountId() + ", Balance changed: -" + amount + "VND, at " + curDateTime.toString()
                + ", Balance: " + fromBalance + ", RefID: " + fromTranID;
        TransactionHistory fromTranHist = new TransactionHistory(fromTranID, curDateTime, histContent);
        fromCustomer.addTransactionHist(fromTranHist);

        int toTranID = getTransHistotryRefID();
        int toBalance = toCustomer.getBalance();
        toBalance += amount;
        toCustomer.setBalance(toBalance);
        String recvContent = "Received-From AccID: " + fromCustomer.getAccountId() + " to AccID:"
                + toCustomer.getAccountId() + ", Balance changed: +" + amount + "VND, at " + curDateTime.toString()
                + ", Balance: " + toBalance + ", RefID: " + fromTranID;
        TransactionHistory toTranHist = new TransactionHistory(fromTranID, curDateTime, recvContent);
        toCustomer.addTransactionHist(toTranHist);

        System.out.println("Transfer successully!");
        System.out.println(">---------------------------------------------------->");
        System.out.println(histContent);

        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int withDrawSaving(String accountID, int depID, List<AccountDetail> accountDetails) {
        Customer fromCustomer = null;

        for (AccountDetail acc : accountDetails) {
            if (acc instanceof Customer) {
                Customer cus = (Customer) acc;

                if (cus.getAccountId().equals(accountID)) {
                    fromCustomer = cus;
                }

            }
        }

        DepositDetail depCustomer = null;

        List<DepositDetail> depListCus = fromCustomer.getDepositList();

        for (DepositDetail depositDetail : depListCus) {
            if (depositDetail.getDepositID() == depID) {
                depCustomer = depositDetail;
            }
        }

        int fromTranID = getTransHistotryRefID();
        LocalDateTime curDateTime = LocalDateTime.now();
        int fromBalance = fromCustomer.getBalance();
        fromBalance += (depCustomer.getDepositAmount()
                + depCustomer.getDepositAmount() * depCustomer.getDepositInterestRate());
        fromCustomer.setBalance(fromBalance);
        String histContent = "Withdraw Deposit-From AccID: " + fromCustomer.getAccountId() + ", Balance changed: +"
                + (depCustomer.getDepositAmount() + depCustomer.getDepositAmount() * depCustomer.getDepositInterestRate()) + "VND, at "
                + curDateTime.toString() + ", Balance: " + fromBalance + ", RefID: " + fromTranID;
        TransactionHistory fromTranHist = new TransactionHistory(fromTranID, curDateTime, histContent);
        fromCustomer.addTransactionHist(fromTranHist);

        int cusTotalDeposit = fromCustomer.getDepositTotalAmount();
        cusTotalDeposit -= depCustomer.getDepositAmount();
        fromCustomer.setDepositTotalAmount(cusTotalDeposit);

        Iterator<DepositDetail> iterator = depListCus.iterator();

        while (iterator.hasNext()) {
            DepositDetail aDept = iterator.next();
            if (aDept.getDepositID() == depID) {
                iterator.remove();
                break;
            }
        }

        System.out.println("Withdraw Deposit Sucessfully!");
        System.out.println(">---------------------------------------------------->");
        System.out.println(histContent);
        return 0;
    }

    public int getDepositTotalAmountFromService(String accountID, List<AccountDetail> accountDetails) {
        for (AccountDetail acc : accountDetails) {
            if (acc instanceof Customer) {
                Customer cus = (Customer) acc;
                if (cus.getAccountId().equals(accountID)) {
                    return cus.getDepositTotalAmount();
                }
            }
        }

        return 0;
    }

    public void showCustomerDepositHist(String accountID, List<AccountDetail> accountDetails) {

        for (AccountDetail acc : accountDetails) {
            if (acc instanceof Customer) {
                Customer cus = (Customer) acc;
                if (cus.getAccountId().equals(accountID)) {
                    cus.showDepositHistList();
                    return;
                }
            }
        }

        return;
    }

    public void showCustomerTransHist(String accountID, List<AccountDetail> accountDetails) {

        for (AccountDetail acc : accountDetails) {
            if (acc instanceof Customer) {
                Customer cus = (Customer) acc;
                if (cus.getAccountId().equals(accountID)) {
                    cus.showTransactionHistList();
                    return;
                }
            }
        }

        return;
    }
}
