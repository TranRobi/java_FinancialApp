package hu.bme.personalfinance.transaction;

import java.time.LocalDateTime;

public class Expense extends Transaction {
    private static final long serialVersionUID = 1L;

    private final ExpenseCategory category;
    private final boolean isEssential;

    public Expense(LocalDateTime date, String description, double amount, CurrencyType currency,
                   ExpenseCategory category, boolean isEssential) {
        super(date, description, amount, currency);
        if (category == null) {
            throw new IllegalArgumentException("A költségkategória nem lehet null.");
        }
        this.category = category;
        this.isEssential = isEssential;
    }

    @Override
    public double getBalanceImpact() {
        return -amount;
    }

    public ExpenseCategory getCategory() {
        return category;
    }

    public boolean isEssential() {
        return isEssential;
    }
}
