package model;

import java.time.Instant;
import java.util.UUID;

public class Transaction {
    private final UUID id;
    private final Instant timestamp;
    private final String accountId;
    private final TransactionType type;
    private final double amount;
    private final String counterpartyAccountId;

    public Transaction(String accountId, TransactionType type, double amount) {
        this(accountId, type, amount, null); // Appel au nouveau constructeur
    }

     public Transaction(String accountId, TransactionType type, double amount , String counterpartyAccountId) {
        this.id = UUID.randomUUID();
        this.timestamp = Instant.now();
        this.accountId = accountId;
        this.type = type;
        this.amount = amount;
        this.counterpartyAccountId = counterpartyAccountId;
    }

    public UUID getId() { return id; }
    public Instant getTimestamp() { return timestamp; }
    public String getAccountId() { return accountId; }
    public TransactionType getType() { return type; }
    public double getAmount() { return amount; }
    public String getCounterpartyAccountId() { return counterpartyAccountId; }

    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", accountId='" + accountId + '\'' +
                ", type=" + type +
                ", amount=" + amount +
                ", counterpartyAccountId='" + counterpartyAccountId + '\'' +
                '}';
    }


}
