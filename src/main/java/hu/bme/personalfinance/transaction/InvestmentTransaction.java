package hu.bme.personalfinance.transaction;

import java.time.LocalDateTime;

public class InvestmentTransaction extends Transaction {
    private static final long serialVersionUID = 1L;

    private final double annualYieldRate; // Éves hozam százalékban (pl. 8.5 = 8.5%)
    private final int durationMonths;     // Futamidő hónapokban

    public InvestmentTransaction(LocalDateTime date, String description, double amount, CurrencyType currency,
                                 double annualYieldRate, int durationMonths) {
        super(date, description, amount, currency);
        if (annualYieldRate < 0.0) {
            throw new IllegalArgumentException("Az éves hozamráta nem lehet negatív.");
        }
        if (durationMonths <= 0) {
            throw new IllegalArgumentException("A futamidőnek legalább 1 hónapnak kell lennie.");
        }
        this.annualYieldRate = annualYieldRate;
        this.durationMonths = durationMonths;
    }

    /**
     * Kiszámítja a lejáratkori várható összértéket kamatos kamattal.
     * Képlet: Tőke * (1 + kamatláb)^(hónapok / 12)
     */
    public double calculateMaturityValue() {
        double rateAsDecimal = annualYieldRate / 100.0;
        double years = durationMonths / 12.0;
        return amount * Math.pow(1.0 + rateAsDecimal, years);
    }

    /**
     * Kiszámítja a befektetésen elért várható nettó hozamot (kamatnyereség).
     */
    public double calculateExpectedProfit() {
        return calculateMaturityValue() - amount;
    }

    @Override
    public double getBalanceImpact() {
        return calculateExpectedProfit();
    }

    public double getAnnualYieldRate() {
        return annualYieldRate;
    }

    public int getDurationMonths() {
        return durationMonths;
    }
}
