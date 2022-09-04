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
                   System.out.println("\nThong tin cua ban:");
                   System.out.println("Ten: " +cus.getName());
                   System.out.println("Ngay sinh: " +cus.getBirthday());
                   System.out.println("So dien thoai: " +cus.getPhoneNum());
                   System.out.println("Email: " +cus.getEmail());
                   System.out.println("Dia chi: " +cus.getAddress());
                   System.out.println("Gioi tinh: " +cus.getGender());
                   System.out.println("Mat khau: " +cus.getPassword());  //hiện pass để check, xóa sau!!!!
                   System.out.println("So du: " + cus.getBalance());  //hiện pass để check, xóa sau!!!!
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
        // TODO Auto-generated method stub
        String newPassword;
        while (true) {
            System.out.print("Nhap password moi:   ");
            newPassword = scanner.nextLine();
            if (checkPass(newPassword)) {
                break;
            } else {
                System.out.println("Mat khau nay khong dung dang");
            }
        }
        returnCustomer(accountID, accountDetails).setPassword(newPassword);
        System.out.println("Doi mat khau thanh cong!!!");
    }

    @Override
    public void updateAccName(String accountID, List<AccountDetail> accountDetails) {
        // TODO Auto-generated method stub
        System.out.print("Nhap ten moi: ");
        String newName = scanner.nextLine();
        returnCustomer(accountID, accountDetails).setName(newName);
        System.out.println("Ten cua ban da duoc thay doi thanh cong!!!");
    }

    @Override
    public void updateAccBirthday(String accountID, List<AccountDetail> accountDetails) {
        // TODO Auto-generated method stu
        String newBirthday;
        while (true) {
            System.out.print("Nhap ngay sinh moi (dạng dd/MM/yyyy):   ");
            newBirthday = scanner.nextLine();
            if (checkBirthday(newBirthday)) {
                break;
            } else {
                System.out.println("Ngay sinh phai dung dinh dang dd/MM/yyyy");
            }
        }
        returnCustomer(accountID, accountDetails).setBirthday(newBirthday);;
        System.out.println("Ngay sinh da duoc thay doi thanh cong!!!");
       
    }

    @Override
    public void updateAccPhoneNum(String accountID, List<AccountDetail> accountDetails) {
        // TODO Auto-generated method stub
        String newPhone;
        while (true) {
            System.out.print("Nhap so dien thoai moi:   ");
            newPhone = scanner.nextLine();
            if (checkPhone(newPhone) == false) {
                System.out.println("So dien thoai phai la mot day so!");
            } else if (hasEmail(newPhone, accountDetails)){
                System.out.println("So dien thoai nay da duoc su dung!");
            } else {
                break;
            }
         }
        returnCustomer(accountID, accountDetails).setPhoneNum(newPhone);;
        System.out.println("So dien thoai cua ban da duoc thay doi thanh cong!!!");
        
    }

    @Override
    public void updateAccEmail(String accountID, List<AccountDetail> accountDetails) {
        // TODO Auto-generated method stub
        String newEmail;
        while (true) {
            System.out.print("Nhap so dien thoai moi:   ");
            newEmail = scanner.nextLine();
            if (checkEmail(newEmail) == false) {
                System.out.println("Email vua nhap khong hop le!");
            } else if (hasEmail(newEmail, accountDetails)){
                System.out.println("Email nay da duoc su dung!");
            } else {
                break;
            }
         }
        returnCustomer(accountID, accountDetails).setEmail(newEmail);;
        System.out.println("Email cua ban da duoc thay doi thanh cong!!!");
        
    }

    @Override
    public void updateAccAddress(String accountID, List<AccountDetail> accountDetails) {
        // TODO Auto-generated method stub
        System.out.print("Nhap dia chi moi: ");
        String newAddress = scanner.nextLine();
        returnCustomer(accountID, accountDetails).setAddress(newAddress);;
        System.out.println("Thay doi dia chi thanh cong!!!");
    }

    @Override
    public void updateAccGender(String accountID, List<AccountDetail> accountDetails) {
        // TODO Auto-generated method stub
        System.out.print("Nhap gioi tinh moi: ");
        String newGender = scanner.nextLine();
        returnCustomer(accountID, accountDetails).setGender(newGender);
        System.out.println("Gioi tinh cua ban da duoc thay doi!!!");
        
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
                   System.out.println("\nThong tin cua ban:");
                   System.out.println("So du: " + cus.getBalance() + "VND");  //hiện pass để check, xóa sau!!!!
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
        String histContent = "Gui Tiet Kiem-STK: " + fromCustomer.getAccountId() + ", SD thay doi: -" + depAmount + "VND, luc " + curDateTime.toString() + ", So du: " + fromBalance + ", RefID: " + fromTranID;
        TransactionHistory fromTranHist = new TransactionHistory(fromTranID, curDateTime, histContent);
        fromCustomer.addTransactionHist(fromTranHist);

        DepositDetail depositDetail = new DepositDetail(fromTranID, depAmount, curDateTime, depTerm, interestRate);
        fromCustomer.addDepositDetailHist(depositDetail);
        int cusTotalDeposit = fromCustomer.getDepositTotalAmount();
        cusTotalDeposit += depAmount;
        fromCustomer.setDepositTotalAmount(cusTotalDeposit);

        System.out.println("Gui tiet kiem thanh cong!");
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
        String histContent = "Chuyen Khoan-Tu STK: " + fromCustomer.getAccountId() + " sang STK:" + toCustomer.getAccountId() + ", SD thay doi: -" + amount + "VND, luc " + curDateTime.toString() + ", So du: " + fromBalance + ", RefID: " + fromTranID;
        TransactionHistory fromTranHist = new TransactionHistory(fromTranID, curDateTime, histContent);
        fromCustomer.addTransactionHist(fromTranHist);


        int toTranID = getTransHistotryRefID();
        int toBalance = toCustomer.getBalance();
        toBalance += amount;
        toCustomer.setBalance(toBalance);
        String recvContent = "Nhan Chuyen Khoan- Tu STK: " + fromCustomer.getAccountId() + " sang STK:" + toCustomer.getAccountId() + ", SD thay doi: +" + amount + "VND, luc " + curDateTime.toString() + ", So du: " + toBalance + ", RefID: " + fromTranID;
        TransactionHistory toTranHist = new TransactionHistory(fromTranID, curDateTime, recvContent);
        toCustomer.addTransactionHist(toTranHist);


        System.out.println("Chuyen khoan thanh cong");
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

        DepositDetail depCustomer= null;

        List<DepositDetail> depListCus = fromCustomer.getDepositList();

        for (DepositDetail depositDetail : depListCus) {
            if (depositDetail.getDepositID()==depID) {
                depCustomer = depositDetail;
            }
        }

        int fromTranID = getTransHistotryRefID();
        LocalDateTime curDateTime = LocalDateTime.now();
        int fromBalance = fromCustomer.getBalance();
        fromBalance += (depCustomer.getDepositAmount() + depCustomer.getDepositAmount()*depCustomer.getDepositInterestRate());
        fromCustomer.setBalance(fromBalance);
        String histContent = "Rut Tiet Kiem-STK: " + fromCustomer.getAccountId() + ", SD thay doi: +" + (depCustomer.getDepositAmount()*depCustomer.getDepositInterestRate()) + "VND, luc " + curDateTime.toString() + ", So du: " + fromBalance + ", RefID: " + fromTranID;
        TransactionHistory fromTranHist = new TransactionHistory(fromTranID, curDateTime, histContent);
        fromCustomer.addTransactionHist(fromTranHist);

        int cusTotalDeposit = fromCustomer.getDepositTotalAmount();
        cusTotalDeposit -= depCustomer.getDepositAmount();
        fromCustomer.setDepositTotalAmount(cusTotalDeposit);

        Iterator<DepositDetail> iterator = depListCus.iterator();

        while(iterator.hasNext()) {
            DepositDetail aDept = iterator.next();
            if (aDept.getDepositID()==depID) {
                iterator.remove();
                break;
            }
        }

        System.out.println("Rut so tiet kiem thanh cong");
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
