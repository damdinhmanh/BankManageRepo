package model.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import model.Account;
import model.DepositDetail;
import model.TransactionHistory;
import model.service.ICustomerService;

public class CustomerService implements ICustomerService {

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
    public void getDepositDetails(String accountID) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void getTransactionHistory(String accountID) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public DepositDetail savingDepositTerm(int depAmount, LocalDate startDate, int depTerm) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean transferMoney(String fromAccID, String toAccID, List<TransactionHistory> transHist) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean updateAccAddress(String accountID, String newAddress) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean updateAccBirthday(String accountID, LocalDate newBirthDay) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean updateAccEmail(String accountID, String newEmail) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean updateAccGender(String accountID, String newGender) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean updateAccName(String accountID, String newName) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean updateAccPassword(String accountID, String newPassword) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean updateAccPhoneNum(String accountID, String newPhone) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int withDrawSaving(String accountID, int depID, LocalDate withDrawDate) {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
