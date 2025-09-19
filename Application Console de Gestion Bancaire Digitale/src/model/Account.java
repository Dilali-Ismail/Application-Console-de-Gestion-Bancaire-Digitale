package model;

import java.time.Instant;
import java.util.UUID;

public class Account {
    private String accountId;
    private UUID ownerUserId;
    private double balance;
    private Instant createdAt;
    private boolean active;

    public Account(UUID ownerUserId) {
        this.ownerUserId = ownerUserId;
        this.accountId = randomAccountId();
        this.balance = 0.0;
        this.createdAt = Instant.now();
        this.active = true;
    }

    private String randomAccountId() {
        int r = (int) (Math.random() * 1000) + 900;
        return "BankId-" + r;
    }

    public String getAccountId() { return accountId; }
    public UUID getOwnerUserId() { return ownerUserId; }
    public double getBalance() { return balance; }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public boolean isActive() {
        return active;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
