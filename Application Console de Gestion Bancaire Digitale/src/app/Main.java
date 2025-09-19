package app;

import repository.memory.InMemoryAccountRepository;
import repository.memory.InMemoryTransactionRepository;
import repository.memory.InMemoryUserRepository;
import service.AccountService;
import service.AuthService;
import service.TransactionService;
import ui.Menu;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        InMemoryUserRepository userRepo = new InMemoryUserRepository();
        InMemoryAccountRepository accountRepo = new InMemoryAccountRepository();
        InMemoryTransactionRepository transactionRepo = new InMemoryTransactionRepository();

        AuthService authService = new AuthService(userRepo);
        AccountService accountService = new AccountService(accountRepo);
        TransactionService transactionService = new TransactionService(accountRepo, transactionRepo);

        Menu menu = new Menu(authService, accountService ,transactionService);
        menu.afficherMenu();
    }
}