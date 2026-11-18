package hu.bme.personalfinance.account;

import java.io.Serializable;
import hu.bme.personalfinance.transaction.CurrencyType;
import java.util.Objects;

public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String name;
    private final AccountType type;
    private final CurrencyType currency;

    public Account(String name, AccountType type, CurrencyType currency) {
        if (name == null || name.trim().isEmpty() || type == null || currency == null) {
            throw new IllegalArgumentException("A név, típus és pénznem megadása kötelező.");
        }
        this.name = name.trim();
        this.type = type;
        this.currency = currency;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Account other = (Account) obj;
        return name.equals(other.name) && type == other.type && currency == other.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, currency);
    }

    @Override
    public String toString() {
        return name + " (" + type + ", " + currency + ")";
    }
    
    public String getName() {
        return name;
    }

    public AccountType getType() {
        return type;
    }

    public CurrencyType getCurrency() {
        return currency;
    }

}
