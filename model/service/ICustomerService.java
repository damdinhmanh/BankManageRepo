package model.service;

import java.time.LocalDate;
import java.util.List;

import model.Account;
import model.DepositDetail;
import model.TransactionHistory;

public interface ICustomerService {
    //Cusomter can update
    public boolean updateAccPassword(String accountID, String newPassword);
    public boolean updateAccName(String accountID, String newName);
    public boolean updateAccBirthday(String accountID, LocalDate newBirthDay);
    public boolean updateAccPhoneNum(String accountID, String newPhone);
    public boolean updateAccEmail(String accountID, String newEmail);
    public boolean updateAccAddress(String accountID, String newAddress);
    public boolean updateAccGender(String accountID, String newGender);

    //get account infor
    public Account getCusAccountInfor(String accountID);
    public int getCusBalance(String accountID);
    public boolean transferMoney(String fromAccID, String toAccID, List<TransactionHistory> transHist);
    public DepositDetail savingDepositTerm(int depAmount, LocalDate startDate, int depTerm);
    public int withDrawSaving(String accountID, int depID, LocalDate withDrawDate);
    public void getTransactionHistory(String accountID);
    public void getDepositDetails(String accountID);
}
