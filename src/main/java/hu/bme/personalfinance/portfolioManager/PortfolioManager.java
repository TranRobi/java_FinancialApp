package hu.bme.personalfinance.portfolioManager;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import hu.bme.personalfinance.transaction.Transaction;
import hu.bme.personalfinance.account.Account;
import hu.bme.personalfinance.portfolio.PortfolioData;

public class PortfolioManager implements Serializable{
    private static final long serialVersionUID = 1L;

    private final PortfolioData portfolioData;

    public PortfolioManager() {
        this.portfolioData = new PortfolioData();
    }

    public void addAccount(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Az account nem lehet null.");
        }
        if (!portfolioData.getAccountTransactions().containsKey(account)) {
            portfolioData.getAccountTransactions().put(account, new java.util.ArrayList<>());
        } else {
            throw new IllegalArgumentException("Az account már létezik.");
        }
    }

    public void removeAccount(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Az account nem lehet null.");
        }
        portfolioData.getAccountTransactions().remove(account);
    }

    public Map<Account, List<Transaction>> getAccounts() {
        return portfolioData.getAccountTransactions();
    }

    public Set<Account> getAccountSet() {
        return portfolioData.getAccountTransactions().keySet();
    }

    public void addTransaction(Account account, Transaction transaction) {
        if (account == null || transaction == null) {
            throw new IllegalArgumentException("Az account és a transaction nem lehet null.");
        }
        List<Transaction> transactions = portfolioData.getAccountTransactions().get(account);
        if (transactions != null) {
            transactions.add(transaction);
        } else {
            throw new IllegalArgumentException("Az account nem található.");
        }
    }

    public void removeTransaction(Account account, Transaction transaction) {
        if (account == null || transaction == null) {
            throw new IllegalArgumentException("Az account és a transaction nem lehet null.");
        }
        List<Transaction> transactions = portfolioData.getAccountTransactions().get(account);
        if (transactions != null) {
            transactions.remove(transaction);
        } else {
            throw new IllegalArgumentException("Az account nem található.");
        }
    }

    public List<Transaction> getTransactions(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Az account nem lehet null.");
        }
        List<Transaction> transactions = portfolioData.getAccountTransactions().get(account);
        if (transactions != null) {
            return transactions;
        } else {
            throw new IllegalArgumentException("Az account nem található.");
        }
    }

    public double getAccountBalance(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Az account nem lehet null.");
        }
        List<Transaction> transactions = portfolioData.getAccountTransactions().get(account);
        if (transactions != null) {
            return transactions.stream().mapToDouble(Transaction::getBalanceImpact).sum();
        } else {
            throw new IllegalArgumentException("Az account nem található.");
        }
    }

    public double getTotalBalanceInHuf() {
        return portfolioData.getAccountTransactions().keySet().stream()
                .mapToDouble(account -> getAccountBalance(account) * portfolioData.getExchangeRate(account.getCurrency()))
                .sum();
    }

    public void saveToFile(String filename) throws IOException {
        if (filename == null || filename.trim().isEmpty()) {
            throw new IllegalArgumentException("A fájlnév nem lehet üres.");
        }
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(portfolioData);
        }
    }

    public void loadFromFile(String filename) throws IOException, ClassNotFoundException {
        if (filename == null || filename.trim().isEmpty()) {
            throw new IllegalArgumentException("A fájlnév nem lehet üres.");
        }
        try (java.io.ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            PortfolioData loadedData = (PortfolioData) ois.readObject();
            portfolioData.setAccountTransactions(loadedData.getAccountTransactions());
            portfolioData.setCurrencyExchangeRates(loadedData.getCurrencyExchangeRates());
        }
    }
}
