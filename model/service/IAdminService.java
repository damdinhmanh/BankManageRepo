package model.service;

import java.time.LocalDate;

import model.Account;
import model.Customer;

public interface IAdminService {
    //Account manage
    public boolean addAcc(Account account);
    public boolean updateAccId(String AccountId, String newAccId);
    public boolean updateAccPassword(String AccountId, String newPassword);
    public boolean updateAccbalance(String AccountId, int newBalance);
    public boolean updateAccName(String AccountId, String newName);
    public boolean updateAccBirthday(String AccountId, LocalDate newBirthDay);
    public boolean updateAccPhoneNum(String AccountId, String newPhone);
    public boolean updateAccEmail(String AccountId, String newEmail);
    public boolean updateAccNationalID(String AccountId, String newNationalID);
    public boolean updateAccAddress(String AccountId, String newAddress);
    public boolean updateAccGender(String AccountId, String newGender);
    public boolean updateAccTrustLevel(String AccountId, int newTrustLevel);
    public boolean updateAccIsEnable(String AccountId, boolean isAccEnable);
    public boolean updateAccDepositTotalAmount(String AccountId, int newTotalDepositAmount);

    //update interest rate 
    public void updateInterestRate(double newInterestRate);

    //update customer trust level
    public void updateCustomerTrustLevel(int newLevel, String nationalID);
}
