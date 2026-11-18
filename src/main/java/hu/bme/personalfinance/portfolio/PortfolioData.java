package hu.bme.personalfinance.portfolio;

import java.io.Serializable;
import java.util.List;

import hu.bme.personalfinance.account.Account;
import hu.bme.personalfinance.transaction.Transaction;
import hu.bme.personalfinance.transaction.CurrencyType;

import java.util.Map;
import java.util.EnumMap;
import java.util.HashMap;

public class PortfolioData implements Serializable{
    private static final long serialVersionUID = 1L;

    private final Map<Account, List<Transaction>> accountTransactions;
    private final EnumMap<CurrencyType, Double> currencyExchangeRates;

    public PortfolioData() {
        this.accountTransactions = new HashMap<>();
        this.currencyExchangeRates = new EnumMap<>(CurrencyType.class);
        initializeDefaultExchangeRates();
    }

    //segédmetódus a default árfolyamok inicializálásához
    private void initializeDefaultExchangeRates() {
        currencyExchangeRates.put(CurrencyType.HUF, 1.0);
        currencyExchangeRates.put(CurrencyType.EUR, 395.0);
        currencyExchangeRates.put(CurrencyType.USD, 365.0);
    }

    //Getterek
    public Map<Account, List<Transaction>> getAccountTransactions() {
        return accountTransactions;
    }
    
    public EnumMap<CurrencyType, Double> getCurrencyExchangeRates() {
        return currencyExchangeRates;
    }

    //Setterek 
    public void setAccountTransactions(Map<Account, List<Transaction>> accountTransactions) {
        if (accountTransactions == null) {
            throw new IllegalArgumentException("Az accountTransactions nem lehet null.");
        }
        this.accountTransactions.clear();
        this.accountTransactions.putAll(accountTransactions);
    }


    public void setCurrencyExchangeRates(EnumMap<CurrencyType, Double> currencyExchangeRates) {
        if (currencyExchangeRates == null){
            throw new IllegalArgumentException("Az árfolyamok nem lehetnek null.");
        }
        this.currencyExchangeRates.clear();
        this.currencyExchangeRates.putAll(currencyExchangeRates);
    }

    public double getExchangeRate(CurrencyType currency) {
        return currencyExchangeRates.getOrDefault(currency, 1.0);
    }
}