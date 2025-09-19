package service;

import model.Account;
import model.User;
import repository.interfaces.AccountRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepo) {
        this.accountRepository = accountRepo;
    }
    public void creerAccount(User user) {
        if (user == null) {
            System.out.println("Erreur : aucun utilisateur connecté !");
            return;
        }

        Account account = new Account(user.getId());
        accountRepository.addAccount(account);
        System.out.println("Compte créé avec succès : " + account.getAccountId());
    }
    public void listerAccount(User user){
        System.out.println("**** Accounts List *****");
        ArrayList<Account> Accounts = accountRepository.findByOwnerId(user.getId());
        if(Accounts.isEmpty()){
            System.out.println("You have no Accounts");
        }else{
            int compt = 0;
            for(Account account : Accounts){
                System.out.println("************************");
                System.out.println("======= " + compt++ + "=========");
                System.out.println("ID: " + account.getAccountId());
                System.out.println("Balance: " + account.getBalance());
                System.out.println("Status: " + (account.isActive() ? "Active" : "Inactive"));
                System.out.println("Created at: " + account.getCreatedAt());
                System.out.println("************************");
            }
        }
    }
}
