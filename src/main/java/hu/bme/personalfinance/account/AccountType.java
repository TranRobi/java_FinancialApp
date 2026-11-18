package hu.bme.personalfinance.account;

public enum AccountType {
    BANK_ACCOUNT("Bank Account"),
    CASH_ACCOUNT("Cash Account"),
    INVESTMENT_ACCOUNT("Investment Account"),
    CREDIT_CARD_ACCOUNT("Credit Card Account");

    private final String displayName;

    AccountType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
    
    @Override 
    public String toString() {
        return displayName;
    }
}
