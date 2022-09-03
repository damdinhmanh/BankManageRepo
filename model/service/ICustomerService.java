package model.service;

import java.time.LocalDate;
import java.util.List;

import model.Account;
import model.AccountDetail;
import model.DepositDetail;
import model.TransactionHistory;
import utils.Gender;

public interface ICustomerService {
    //Cusomter can update
    public boolean updateAccPassword(String accountID, String newPassword, List<AccountDetail> accountDetails);
    public boolean updateAccName(String accountID, String newName, List<AccountDetail> accountDetails);
    public boolean updateAccBirthday(String accountID, LocalDate newBirthDay, List<AccountDetail> accountDetails);
    public boolean updateAccPhoneNum(String accountID, String newPhone, List<AccountDetail> accountDetails);
    public boolean updateAccEmail(String accountID, String newEmail, List<AccountDetail> accountDetails);
    public boolean updateAccAddress(String accountID, String newAddress, List<AccountDetail> accountDetails);
    public boolean updateAccGender(String accountID, String newGender, List<AccountDetail> accountDetails);

    //get account infor
    public Account getCusAccountInfor(String accountID);
    public int getCusBalance(String accountID);
    public boolean transferMoney(String fromAccID, String toAccID, List<TransactionHistory> transHist);
    public DepositDetail savingDepositTerm(int depAmount, LocalDate startDate, int depTerm);
    public int withDrawSaving(String accountID, int depID, LocalDate withDrawDate);
    public void getTransactionHistory(String accountID);
    public void getDepositDetails(String accountID);
    public boolean regAccountId(String name, LocalDate birthday, String phoneNum, String email, String nationalID, String address, Gender gender);
}
