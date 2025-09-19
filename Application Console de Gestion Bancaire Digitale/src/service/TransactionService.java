package service;

import model.Account;
import model.Transaction;
import model.TransactionType;
import repository.interfaces.AccountRepository;
import repository.interfaces.TransactionRepository;

import java.util.Optional;

public class TransactionService {
    private final TransactionRepository repository;
    private final AccountRepository accountRepository;
    public TransactionService(AccountRepository accountRepository,TransactionRepository repository) {
        this.repository = repository;
        this.accountRepository = accountRepository;

    }
    public void Deposit(String accountId , double amount){
        if(amount <=0){
            System.out.println("amount invalable");
        }
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
       try{
           if (optionalAccount.isEmpty()) {
               System.out.println("Compte introuvable !");
               return;
           }
           Account acc = optionalAccount.get();
           TransactionType.DEPOSIT.execute(acc, amount);
           Transaction transaction = new Transaction(accountId, TransactionType.DEPOSIT, amount);
           repository.save(transaction);
           System.out.println("Dépôt de " + amount + " effectué sur le compte " + accountId);

       }catch (RuntimeException e) {
           System.out.println("Erreur : " + e.getMessage());
       }

    }

    public void withdraw(String accountId, double amount) {
        if (amount <= 0) {
            System.out.println("Montant invalide !");
            return;
        }

        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if (optionalAccount.isEmpty()) {
            System.out.println("Compte introuvable !");
            return;
        }

        Account acc = optionalAccount.get();
        if (!acc.isActive()) {
            System.out.println("Le compte est inactif.");
            return;
        }

        try {
            TransactionType.WITHDRAW.execute(acc, amount);
            Transaction transaction = new Transaction(accountId, TransactionType.WITHDRAW, amount);
            repository.save(transaction);
            System.out.println("Retrait de " + amount + " effectue sur le compte " + accountId);
        } catch (RuntimeException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public void transfer(String fromAccountId, String toAccountId, double amount) {
        if (amount <= 0) {
            System.out.println("Montant invalide !");
            return;
        }

        Optional<Account> optionalFromAccount = accountRepository.findById(fromAccountId);
        Optional<Account> optionalToAccount = accountRepository.findById(toAccountId);

        if (optionalFromAccount.isEmpty() || optionalToAccount.isEmpty()) {
            System.out.println("Un des comptes est introuvable !");
            return;
        }

        Account fromAccount = optionalFromAccount.get();
        Account toAccount = optionalToAccount.get();

        if (!fromAccount.isActive() || !toAccount.isActive()) {
            System.out.println("Un des comptes est inactif !");
            return;
        }

        try {

            TransactionType.TRANSFEROUT.execute(fromAccount, amount);
            Transaction outTransaction = new Transaction(fromAccountId, TransactionType.TRANSFEROUT, amount, toAccountId);
            repository.save(outTransaction);

            TransactionType.TRANSFERIN.execute(toAccount, amount);
            Transaction inTransaction = new Transaction(toAccountId, TransactionType.TRANSFERIN, amount, fromAccountId);
            repository.save(inTransaction);

            System.out.println("Virement de " + amount + " effectué de " + fromAccountId + " vers " + toAccountId);

        } catch (RuntimeException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

}
