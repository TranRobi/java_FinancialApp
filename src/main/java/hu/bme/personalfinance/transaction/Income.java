package hu.bme.personalfinance.transaction;

import java.time.LocalDateTime;

public class Income extends Transaction {
    private static final long serialVersionUID = 1L;

    private final double taxRate;

    public Income(LocalDateTime date, String description, double amount, CurrencyType currency, double taxRate) {
        super(date, description, amount, currency);
        if (taxRate < 0.0 || taxRate > 1.0) {
            throw new IllegalArgumentException("Az adókulcsnak 0 és 1 között kell lennie (pl. 0.15 a 15%-hoz).");
        }
        this.taxRate = taxRate;
    }

    @Override
    public double getBalanceImpact() {
        return amount * (1.0 - taxRate);
    }

    public double getTaxRate() {
        return taxRate;
    }

    public double getTaxAmount() {
        return amount * taxRate;
    }
}
