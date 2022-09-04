package model;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private List<AccountDetail> accountList = new ArrayList<AccountDetail>();

    /**
     * @return List<AccountDetail> return the accountList
     */
    public List<AccountDetail> getAccountList() {
        return accountList;
    }

    /**
     * @param accountDetail add to the List
     */
    public void addToAccountList(AccountDetail accountDetail) {
        this.accountList.add(accountDetail);
    }

}
