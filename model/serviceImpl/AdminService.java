package model.serviceImpl;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.List;
import model.Admin;
import model.Customer;
import model.Account;
import model.AccountDetail;
import model.service.IAdminService;
import java.util.ArrayList;

public class AdminService implements IAdminService{
    // static Scanner scanner;
    // static List<AccountDetail> accountDetails = new ArrayList<AccountDetail>();
    @Override
    public boolean addAcc(Account account) {
        
        return false;
    }

    @Override
    public boolean updateAccAddress(String AccountId, String newAddress) {

        return false;
    }

    @Override
    public boolean updateAccBirthday(String AccountId, LocalDate newBirthDay) {

        return false;
    }

    @Override
    public boolean updateAccDepositTotalAmount(String AccountId, int newTotalDepositAmount) {

        return false;
    }

    @Override
    public boolean updateAccEmail(String AccountId, String newEmail) {

        return false;
    }

    @Override
    public boolean updateAccGender(String AccountId, String newGender) {

        return false;
    }

    @Override
    public boolean updateAccId(String AccountId, String newAccId) {

        return false;
    }

    @Override
    public boolean updateAccIsEnable(String AccountId, boolean isAccEnable) {

        return false;
    }

    @Override
    public boolean updateAccName(String AccountId, String newName) {

        return false;
    }

    @Override
    public boolean updateAccNationalID(String AccountId, String newNationalID) {
        return false;
    }

    @Override
    public boolean updateAccPassword(String AccountId, String newPassword) {

        return false;
    }

    @Override
    public boolean updateAccPhoneNum(String AccountId, String newPhone) {

        return false;
    }

    @Override
    public boolean updateAccTrustLevel(String AccountId, int newTrustLevel) {

        return false;
    }

    @Override
    public boolean updateAccbalance(String AccountId, int newBalance) {

        return false;
    }

    @Override
    public void updateCustomerTrustLevel(int newLevel, String nationalID) {

        
    }

    @Override
    public void updateInterestRate(double newInterestRate) {
        
    }
    
        // if (!checkExistAccountDetail) {
        //     int choose;
        //     System.out.println("1. Dang nhap lai");
        //     System.out.println("2. Quen mat khau");
        //     do {
        //         choose = Integer.parseInt(scanner.nextLine());

        //         switch (choose) {
        //             case 1:
        //                 dangNhap();
        //                 return;
        //             default:
        //                 quenPass();
        //                 return;
        //         }
        //     } while (choose != 1);
        // }

    //     dangNhapSuccess(accountDetails, role);
    // }
    //  // Quên mật khẩu
    // public static void quenPass() {

    //     boolean checkExistAccountDetail = false;

    //     System.out.print("Nhap Role : ");
    //     String role = scanner.nextLine();
    //     System.out.println();

    //     for (AccountDetail accountDetail : accountDetails) {
    //         if (role.equals(accountDetail.getRole()) && accountDetails.equals(accountDetail.getAccountId())) {
    //             checkExistAccountDetail = true;
    //             thaoTacDoiPass();
    //             dangNhapSuccess(accountDetails, accountDetail.getAccountId());
    //             break;
    //         }
    //     }

    //     if (!checkExistAccountDetail) {
    //         System.out.println("Email khong ton tai");
    //         dangNhap();
    //     }
    // }
    // public static void thaoTacDoiPass() {
    // }

    // public static void dangNhapSuccess(List<AccountDetail> accountDetails, String role) {
    //     System.out.println("Chuc nang cua" + role );
    //     System.out.println("[1] Them Account customer");
    //     System.out.println("[2] Sua Account customer");
    //     System.out.println("[3] Xoa Account customer");
    //     System.out.println("[4] Quan li lai suat tiet kiem");
    //     System.out.println("[5] Kiem tra nguoi dung co phai khac hang tiem nang");
    //     System.out.println("[6] Quit");
    //     int menu;
    //     do {
    //         menu = Integer.parseInt(scanner.nextLine());
    //         scanner.nextLine();
    //         switch (menu) {
    //             case 1:{
    //                 // addAcc();
    //                 break;
    //             }
    //             case 2: {
    //                 // changeAccount();
    //                 break;
    //             }
    //             case 3: {
    //                 // deleteAccount();
    //                 break;
    //             }
    //             case 4: {
    //                 // interestRate();
    //             }
    //             case 5:{
    //                 // customerTrustlevel();
    //             }
    //             default:
    //                 System.exit(0);
    //         }
    //     } while (menu != 5);
    // }

  
}
