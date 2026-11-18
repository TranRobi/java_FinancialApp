package hu.bme.personalfinance.gui;

import javax.swing.table.AbstractTableModel;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import hu.bme.personalfinance.transaction.Transaction;
import hu.bme.personalfinance.transaction.Income;
import hu.bme.personalfinance.transaction.Expense;
import hu.bme.personalfinance.transaction.InvestmentTransaction;

public class TransactionTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");

    private List<Transaction> transactions;
    private final String[] columnNames = {"Dátum", "Leírás", "Típus", "Összeg", "Pénznem", "Részletek"};

    public TransactionTableModel() {
        this.transactions = new ArrayList<>();
    }

    public TransactionTableModel(List<Transaction> transactions) {
        this.transactions = transactions != null ? transactions : new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return transactions != null ? transactions.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override 
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return String.class; // Dátum
            case 1: return String.class; // Leírás
            case 2: return String.class; // Típus
            case 3: return Double.class; // Összeg
            case 4: return String.class; // Pénznem
            case 5: return String.class; // Részletek
            default: return Object.class;
        }
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (transactions == null || rowIndex < 0 || rowIndex >= transactions.size()) {
            return null;
        }
        Transaction transaction = transactions.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return transaction.getDate() != null ? transaction.getDate().format(DATE_FORMATTER) : "";
            case 1:
                return transaction.getDescription();
            case 2:
                if (transaction instanceof Income) return "Bevétel";
                if (transaction instanceof Expense) return "Kiadás";
                if (transaction instanceof InvestmentTransaction) return "Befektetés";
                return "Egyéb";
            case 3:
                return transaction.getAmount();
            case 4:
                return transaction.getCurrency() != null ? transaction.getCurrency().toString() : "";
            case 5:
                if (transaction instanceof Income) {
                    Income inc = (Income) transaction;
                    return String.format("Adó: %.0f%% (Nettó: %.2f)", inc.getTaxRate() * 100, inc.getBalanceImpact());
                } else if (transaction instanceof Expense) {
                    Expense exp = (Expense) transaction;
                    return exp.getCategory() + (exp.isEssential() ? " (Létfontosságú)" : "");
                } else if (transaction instanceof InvestmentTransaction) {
                    InvestmentTransaction inv = (InvestmentTransaction) transaction;
                    return String.format("Hozam: %.1f%%, %d hó (Lejáratkor: %.2f)",
                            inv.getAnnualYieldRate(), inv.getDurationMonths(), inv.calculateMaturityValue());
                }
                return "-";
            default:
                return null;
        }
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions != null ? transactions : new ArrayList<>();
        fireTableDataChanged();
    }

    public Transaction getTransactionAt(int rowIndex) {
        if (transactions != null && rowIndex >= 0 && rowIndex < transactions.size()) {
            return transactions.get(rowIndex);
        }
        return null;
    }
}
