package model.serviceImpl;

import java.time.LocalDate;

import model.Account;
import model.service.IAdminService;

public class AdminService implements IAdminService{

    @Override
    public boolean addAcc(Account account) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean updateAccAddress(String AccountId, String newAddress) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean updateAccBirthday(String AccountId, LocalDate newBirthDay) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean updateAccDepositTotalAmount(String AccountId, int newTotalDepositAmount) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean updateAccEmail(String AccountId, String newEmail) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean updateAccGender(String AccountId, String newGender) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean updateAccId(String AccountId, String newAccId) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean updateAccIsEnable(String AccountId, boolean isAccEnable) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean updateAccName(String AccountId, String newName) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean updateAccNationalID(String AccountId, String newNationalID) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean updateAccPassword(String AccountId, String newPassword) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean updateAccPhoneNum(String AccountId, String newPhone) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean updateAccTrustLevel(String AccountId, int newTrustLevel) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean updateAccbalance(String AccountId, int newBalance) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void updateCustomerTrustLevel(int newLevel, String nationalID) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateInterestRate(double newInterestRate) {
        // TODO Auto-generated method stub
        
    }
    
}
