package model.service;

import java.time.LocalDate;
import java.util.List;

import model.Account;
import model.AccountDetail;
import model.DepositDetail;
import model.TransactionHistory;
import utils.Gender;

public interface ICustomerService {
    // Cusomter can update
    public void updateAccPassword(String accountID, List<AccountDetail> accountDetails);

    public void updateAccName(String accountID, List<AccountDetail> accountDetails);

    public void updateAccBirthday(String accountID, List<AccountDetail> accountDetails);

    public void updateAccPhoneNum(String accountID, List<AccountDetail> accountDetails);

    public void updateAccEmail(String accountID, List<AccountDetail> accountDetails);

    public void updateAccAddress(String accountID, List<AccountDetail> accountDetails);

    public void updateAccGender(String accountID, List<AccountDetail> accountDetails);

    // get account infor
    public Account getCusAccountInfor(String accountID);

    public int getCusBalance(String accountID, List<AccountDetail> accountDetails);

    public boolean transferMoney(String fromAccID, String toAccID, List<AccountDetail> accountDetails, int transAmount);

    public void savingDepositTerm(String accountID, int depAmount, int depTerm, List<AccountDetail> accountDetails,
            double interestRate);

    public int withDrawSaving(String accountID, int depID, List<AccountDetail> accountDetails);

    public void getTransactionHistory(String accountID, List<AccountDetail> accountDetails);

    public void getDepositDetails(String accountID, List<AccountDetail> accountDetails);

    public boolean regAccountId(String name, LocalDate birthday, String phoneNum, String email, String nationalID,
            String address, Gender gender);
}
