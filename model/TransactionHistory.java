package model;

import java.time.LocalDateTime;

public class TransactionHistory {
    private int transactionID;
    private LocalDateTime transactionDateTime;
    private String transactionContent;

    public TransactionHistory(int transactionID, LocalDateTime transactionDateTime, String transactionContent) {
        this.transactionID = transactionID;
        this.transactionDateTime = transactionDateTime;
        this.transactionContent = transactionContent;
    }

    @Override
    public String toString() {
        return "\nTransactionHistory [Content=" + transactionContent + ", DateTime="
                + transactionDateTime + ", RefID=" + transactionID + "]";
    }
}
