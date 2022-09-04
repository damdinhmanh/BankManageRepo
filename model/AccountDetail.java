package model;

public class AccountDetail {
    private String accountId;
    private String password;
    private String role;

    public AccountDetail(String accountId, String password, String role) {
        this.accountId = accountId;
        this.password = password;
        this.role = role;
    }

    /**
     * @return String return the accountId
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * @param accountId the accountId to set
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    /**
     * @return String return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return String return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "\nAccountDetail [accountId=" + accountId + ", password=" + password + ", role=" + role + "]";
    }

}
