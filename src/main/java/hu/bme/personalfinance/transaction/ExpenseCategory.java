package hu.bme.personalfinance.transaction;

/**
 * Költségkategóriák a kiadásokhoz.
 */
public enum ExpenseCategory {
    FOOD("Élelmiszer"),
    HOUSING("Lakhatás"),
    UTILITIES("Rezsi"),
    TRANSPORT("Közlekedés"),
    ENTERTAINMENT("Szórakozás"),
    OTHER("Egyéb");

    private final String displayName;

    ExpenseCategory(String displayName) {
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
