package model.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import model.Account;
import model.AccountDetail;
import model.DepositDetail;
import model.TransactionHistory;
import model.service.ICustomerService;
import utils.Gender;

public class CustomerService implements ICustomerService {

    @Override
    public boolean updateAccPassword(String accountID, String newPassword, List<AccountDetail> accountDetails) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean updateAccName(String accountID, String newName, List<AccountDetail> accountDetails) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean updateAccBirthday(String accountID, LocalDate newBirthDay, List<AccountDetail> accountDetails) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean updateAccPhoneNum(String accountID, String newPhone, List<AccountDetail> accountDetails) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean updateAccEmail(String accountID, String newEmail, List<AccountDetail> accountDetails) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean updateAccAddress(String accountID, String newAddress, List<AccountDetail> accountDetails) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean updateAccGender(String accountID, String newGender, List<AccountDetail> accountDetails) {
        // TODO Auto-generated method stub
        return false;
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
