package model;

import java.time.LocalDateTime;

public class DepositDetail {
    private int depositID;
    private int depositAmount;
    private LocalDateTime deposiDateTime;
    private int depositTerm;
    private double depositInterestRate;

    public DepositDetail(int depositID, int depositAmount, LocalDateTime deposiDateTime, int depositTerm,
            double depositInterestRate) {
        this.depositID = depositID;
        this.depositAmount = depositAmount;
        this.deposiDateTime = deposiDateTime;
        this.depositTerm = depositTerm;
        this.depositInterestRate = depositInterestRate;
    }

    

    @Override
    public String toString() {
        return "\nDepositDetail [DateTime=" + deposiDateTime + ", Amount=" + depositAmount + "VND, RefID="
                + depositID + ", InterestRate=" + depositInterestRate + ", Term=" + depositTerm + " years]";
    }

    /**
     * @return int return the depositID
     */
    public int getDepositID() {
        return depositID;
    }

    /**
     * @param depositID the depositID to set
     */
    public void setDepositID(int depositID) {
        this.depositID = depositID;
    }

    /**
     * @return int return the depositAmount
     */
    public int getDepositAmount() {
        return depositAmount;
    }

    /**
     * @param depositAmount the depositAmount to set
     */
    public void setDepositAmount(int depositAmount) {
        this.depositAmount = depositAmount;
    }

    /**
     * @return LocalDateTime return the deposiDateTime
     */
    public LocalDateTime getDeposiDateTime() {
        return deposiDateTime;
    }

    /**
     * @param deposiDateTime the deposiDateTime to set
     */
    public void setDeposiDateTime(LocalDateTime deposiDateTime) {
        this.deposiDateTime = deposiDateTime;
    }

    /**
     * @return int return the depositTerm
     */
    public int getDepositTerm() {
        return depositTerm;
    }

    /**
     * @param depositTerm the depositTerm to set
     */
    public void setDepositTerm(int depositTerm) {
        this.depositTerm = depositTerm;
    }

    /**
     * @return double return the depositInterestRate
     */
    public double getDepositInterestRate() {
        return depositInterestRate;
    }

    /**
     * @param depositInterestRate the depositInterestRate to set
     */
    public void setDepositInterestRate(double depositInterestRate) {
        this.depositInterestRate = depositInterestRate;
    }

}
