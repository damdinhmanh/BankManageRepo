package model.serviceImpl;
import java.time.LocalDate;
import java.util.List;

import model.Account;
import model.service.IAdminService;
import utils.ConstantVars;
import model.*;

public class AdminService implements IAdminService{
    SavingInterestRate savingInterestRate;

    public void initAdminService() {
        savingInterestRate = new SavingInterestRate();
    }

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
    public void updateInterestRate(double newInterestRate) {
        savingInterestRate.setInterestRate(newInterestRate);
    }

    @Override
    public double getInterestRate() {
        return savingInterestRate.getInterestRate();
    }

    @Override
    public void updateTrustLevelbyNationalID(List<AccountDetail> accountDetails, String nationalID, int newTrustLevel) {
        for (AccountDetail accountDetail : accountDetails) {
            if (accountDetail.getRole().equals(ConstantVars.LOGIN_AS_CUSTOMER)) {
                Customer cus = (Customer) accountDetail;
    
                if (nationalID.equals(cus.getNationalId())) {
                    cus.setTrustLevel(newTrustLevel);
                }
            }
        }
    }
    
}
