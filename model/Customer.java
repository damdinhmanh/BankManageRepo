package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Customer extends AccountDetail {
    private int balance;
    private String name;
    private LocalDate birthday;
    private String phoneNum;
    private String email;
    private String nationalId;
    private String address;
    private String gender;
    private int trustLevel;
    private boolean isAccountEnabled;
    private int depositTotalAmount;
    private List<DepositDetail> depositList = new ArrayList<DepositDetail>();
    private List<TransactionHistory> transactionList = new ArrayList<TransactionHistory>();
    
    public Customer(String accountId, String password, String role, int balance, String name, String birthday,
            String phoneNum, String email, String nationalId, String address, String gender, int trustlevel,
            boolean isAccountEnabled, int depositTotalAmount, List<DepositDetail> depositList,
            List<TransactionHistory> transactionList) {
        super(accountId, password, role);
        this.balance = balance;
        this.name = name;
        this.phoneNum = phoneNum;
        this.email = email;
        this.nationalId = nationalId;
        this.address = address;
        this.gender = gender;
        this.trustLevel = trustlevel;
        this.isAccountEnabled = isAccountEnabled;
        this.depositTotalAmount = depositTotalAmount;
        this.depositList = depositList;
        this.transactionList = transactionList;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate birth = LocalDate.parse(birthday, formatter);
        this.birthday = birth;
    }

    /**
     * @return int return the balance
     */
    public int getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return LocalDate return the birthday
     */
    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * @param birthday the birthday to set
     */
    public void setBirthday(String birthday) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate birth = LocalDate.parse(birthday, formatter);
        this.birthday = birth;
    }

    /**
     * @return String return the phoneNum
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * @param phoneNum the phoneNum to set
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    /**
     * @return String return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return String return the nationalId
     */
    public String getNationalId() {
        return nationalId;
    }

    /**
     * @param nationalId the nationalId to set
     */
    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    /**
     * @return String return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return String return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return String return the trustLevel
     */
    public int getTrustLevel() {
        return trustLevel;
    }

    /**
     * @param trustLevel the trustLevel to set
     */
    public void setTrustLevel(int trustLevel) {
        this.trustLevel = trustLevel;
    }

    /**
     * @return boolean return the isAccountEnabled
     */
    public boolean getEnabled() {
        return isAccountEnabled;
    }

    /**
     * @param isAccountEnabled the isAccountEnabled to set
     */
    public void setIsAccountEnabled(boolean isAccountEnabled) {
        this.isAccountEnabled = isAccountEnabled;
    }

    /**
     * @return int return the depositTotalAmount
     */
    public int getDepositTotalAmount() {
        return depositTotalAmount;
    }

    /**
     * @param depositTotalAmount the depositTotalAmount to set
     */
    public void setDepositTotalAmount(int depositTotalAmount) {
        this.depositTotalAmount = depositTotalAmount;
    }

    /**
     * @return List<DepositDetail> return the depositList
     */
    public List<DepositDetail> getDepositList() {
        return depositList;
    }

    /**
     * @return List<TransactionHistory> return the transactionList
     */
    public List<TransactionHistory> getTransactionList() {
        return transactionList;
    }

    public boolean isAccountEnabled() {
        return isAccountEnabled;
    }

}
