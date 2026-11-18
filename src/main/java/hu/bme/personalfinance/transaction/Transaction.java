package hu.bme.personalfinance.transaction;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;

    protected LocalDateTime date;
    protected String description;
    protected double amount;
    protected CurrencyType currency;

    protected Transaction(LocalDateTime date, String description, double amount, CurrencyType currency) {
        if (date == null || description == null || description.trim().isEmpty() || currency == null) {
            throw new IllegalArgumentException("Dátum, leírás és pénznem megadása kötelező.");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Az összeg nem lehet negatív.");
        }
        this.date = date;
        this.description = description.trim();
        this.amount = amount;
        this.currency = currency;
    }

    public abstract double getBalanceImpact();

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        if (date == null) {
            throw new IllegalArgumentException("A dátum nem lehet null.");
        }
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("A leírás nem lehet üres.");
        }
        this.description = description.trim();
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Az összeg nem lehet negatív.");
        }
        this.amount = amount;
    }

    public CurrencyType getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyType currency) {
        if (currency == null) {
            throw new IllegalArgumentException("A pénznem nem lehet null.");
        }
        this.currency = currency;
    }

    @Override
    public String toString() {
        return String.format("%s - %s: %.2f %s", date, description, amount, currency);
    }
}
