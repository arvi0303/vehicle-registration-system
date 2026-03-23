package vehicleregistrationsystem;

public class Finance {
    private double balance;
    private String database;

    public Finance(double balance, String database) {
        this.balance = balance;
        this.database = database;
    }

    public String PoolingOfFunds() {
        return "Available balance: Rs. " + balance;
    }

    public String EconomicDevelopment() {
        return "Finance data is supporting economic development.";
    }

    public String BetterDecisionMaking() {
        return "Finance data helps in better decision making.";
    }

    public double getBalance() {
        return balance;
    }

    public String getDatabase() {
        return database;
    }
}
