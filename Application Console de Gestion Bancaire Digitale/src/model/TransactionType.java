package model;

public enum TransactionType {
    DEPOSIT {
        @Override
        public void execute(Account account, double amount) {
            account.setBalance(account.getBalance() + amount);
        }
    },
    WITHDRAW {
        @Override
        public void execute(Account account, double amount) {
            if (account.getBalance() >= amount) {
                account.setBalance(account.getBalance() - amount);
            } else {
                throw new RuntimeException("Solde insuffisant !");
            }
        }
    },
    TRANSFERIN {
        @Override
        public void execute(Account account, double amount) {
            account.setBalance(account.getBalance() + amount);
        }
    },
    TRANSFEROUT {
        @Override
        public void execute(Account account, double amount) {
            if (account.getBalance() >= amount) {
                account.setBalance(account.getBalance() - amount);
            } else {
                throw new RuntimeException("Solde insuffisant !");
            }
        }
    };

    public abstract void execute(Account account, double amount);

}
