package model.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import model.Account;
import model.AccountDetail;
import model.Customer;
import model.DepositDetail;
import model.TransactionHistory;
import model.service.ICustomerService;
import utils.Gender;

public class CustomerService implements ICustomerService {
    private Scanner scanner;

    public void openScanner() {
         this.scanner = new Scanner(System.in);
    }
    public void closeScanner() {
         this.scanner.close();
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
               if (cus.getNationalId().equals(accountID)) {
                   System.out.println("\nThong tin cua ban:");
                   System.out.println("Ten: " +cus.getName());
                   System.out.println("Ngay sinh: " +cus.getBirthday());
                   System.out.println("So dien thoai: " +cus.getPhoneNum());
                   System.out.println("Email: " +cus.getEmail());
                   System.out.println("Dia chi: " +cus.getAddress());
                   System.out.println("Gioi tinh: " +cus.getGender());
                   System.out.println("Mat khau: " +cus.getPassword());  //hiện pass để check, xóa sau!!!!
                }
            }
        }
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
        for (AccountDetail acc : accountDetails) {
            if (acc.getAccountId().equals(accountID)) {
                acc.setPassword(newPassword);
                break;
        }
       
    }
        System.out.println("Doi mat khau thanh cong!!!");
    }

    @Override
    public void updateAccName(String accountID, List<AccountDetail> accountDetails) {
        // TODO Auto-generated method stub
        System.out.print("Nhap ten moi: ");
        String newName = scanner.nextLine();
        for (AccountDetail acc : accountDetails) {
            if (acc instanceof Customer) {
                Customer cus = (Customer) acc;
                if (cus.getNationalId().equals(accountID)) {
                    cus.setName(newName);
                    break;
                }
            }
        }
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
        for (AccountDetail acc : accountDetails) {
            if (acc instanceof Customer) {
                Customer cus = (Customer) acc;
                if (cus.getNationalId().equals(accountID)) {
                    cus.setBirthday(newBirthday);
                    break;
                }
            }
        }
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
         for (AccountDetail acc : accountDetails) {
            if (acc instanceof Customer) {
                Customer cus = (Customer) acc;
                if (cus.getNationalId().equals(accountID)) {
                    cus.setPhoneNum(newPhone);
                    break;
                }
            }
        }
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
         for (AccountDetail acc : accountDetails) {
            if (acc instanceof Customer) {
                Customer cus = (Customer) acc;
                if (cus.getNationalId().equals(accountID)) {
                    cus.setEmail(newEmail);
                    break;
                }
            }
        }
        System.out.println("Email cua ban da duoc thay doi thanh cong!!!");
        
    }

    @Override
    public void updateAccAddress(String accountID, List<AccountDetail> accountDetails) {
        // TODO Auto-generated method stub
        System.out.print("Nhap dia chi moi: ");
        String newAddress = scanner.nextLine();
        for (AccountDetail acc : accountDetails) {
            if (acc instanceof Customer) {
                Customer cus = (Customer) acc;
                if (cus.getNationalId().equals(accountID)) {
                    cus.setAddress(newAddress);
                    break;
                }
            }
        }
        System.out.println("Thay doi dia chi thanh cong!!!");
    }

    @Override
    public void updateAccGender(String accountID, List<AccountDetail> accountDetails) {
        // TODO Auto-generated method stub
        System.out.print("Nhap gioi tinh moi: ");
        String newGender = scanner.nextLine();
        for (AccountDetail acc : accountDetails) {
            if (acc instanceof Customer) {
                Customer cus = (Customer) acc;
                if (cus.getNationalId().equals(accountID)) {
                    cus.setGender(newGender);
                    break;
                }
            }
        }
        System.out.println("Gioi tinh cua ban da duoc thay doi!!!");
        
    }

    @Override
    public Account getCusAccountInfor(String accountID) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getCusBalance(String accountID) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean transferMoney(String fromAccID, String toAccID, List<TransactionHistory> transHist) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public DepositDetail savingDepositTerm(int depAmount, LocalDate startDate, int depTerm) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int withDrawSaving(String accountID, int depID, LocalDate withDrawDate) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void getTransactionHistory(String accountID) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void getDepositDetails(String accountID) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean regAccountId(String name, LocalDate birthday, String phoneNum, String email, String nationalID,
            String address, Gender gender) {
        // TODO Auto-generated method stub
        return false;
    }


}
