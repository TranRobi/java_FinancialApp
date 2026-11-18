package hu.bme.personalfinance.transaction;

/**
 * Támogatott pénznemek (Enum: HUF, EUR, USD).
 */
public enum CurrencyType {
    HUF("HUF"),
    EUR("EUR"),
    USD("USD");

    private final String code;

    CurrencyType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code;
    }
}
