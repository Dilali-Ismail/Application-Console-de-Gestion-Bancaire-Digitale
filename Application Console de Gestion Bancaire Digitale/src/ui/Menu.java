package ui;

import model.User;
import service.AccountService;
import service.AuthService;
import service.TransactionService;

import java.util.Scanner;

public class Menu {
    private final AuthService authService;
    private final AccountService accountService;
    private final Scanner sc = new Scanner(System.in);
    private final TransactionService transactionService;

    public Menu(AuthService authService, AccountService accountService , TransactionService transactionService) {
        this.authService = authService;
        this.accountService = accountService;
        this.transactionService = transactionService;
    }
    public void afficherMenu() {
        while (true) {
            System.out.println("\n****** MENU PRINCIPAL ******");
            System.out.println("1. Inscription");
            System.out.println("2. Connexion");
            System.out.println("3. Quitter");
            System.out.print("Votre choix : ");
            int choix = sc.nextInt();
            sc.nextLine();

            switch (choix) {
                case 1 -> authService.register();
                case 2 -> {
                    authService.login();
                    if (authService.getLoggedInUser() != null) {
                        afficherMenuUtilisateur();
                    }
                }
                case 3 -> {
                    System.out.println("Au revoir 👋");
                    return;
                }
                default -> System.out.println("Choix invalide, réessayez !");
            }
        }
    }
    private void afficherMenuUtilisateur() {
        while (true) {
            User user = authService.getLoggedInUser();
            if (user == null) return;

            System.out.println("****** MENU UTILISATEUR ******");
            System.out.println("1. Créer un compte bancaire");
            System.out.println("2. Lister votre compts");
            System.out.println("3. Effectuer un depot");
            System.out.println("4. Effectuer un retrait");
            System.out.println("5. Effectuer un virement");
            System.out.println("6. Changer le mot de passe");
            System.out.println("7. Déconnexion");
            System.out.print("Votre choix : ");
            int choix = sc.nextInt();
            sc.nextLine();

            switch (choix) {
                case 1 -> accountService.creerAccount(user);
                case 2 -> {
                    accountService.listerAccount(user);
                }
                case 3 ->{ effectuerDepot(); }
                case 4 -> {effectuerRetrait();}
                case 5 -> {effectuerVirement();}
                case 6 ->{authService.changePassword();}
                case 7 ->{
                    authService.logout();
                    return;
                }
                default -> System.out.println("Choix invalide !");
            }
        }
    }
    private void effectuerDepot() {
        User user = authService.getLoggedInUser();
        if (user == null) {
            System.out.println("Aucun utilisateur connecté.");
            return;
        }

        System.out.println("***** DÉPÔT *****");
        accountService.listerAccount(user); // Lister les comptes de l'utilisateur

        System.out.print("Entrez l'ID du compte : ");
        String accountId = sc.nextLine();

        System.out.print("Montant à déposer : ");
        double amount = sc.nextDouble();
        sc.nextLine(); // Nettoyer le buffer
        transactionService.Deposit(accountId, amount);
    }
    private void effectuerRetrait() {
        User user = authService.getLoggedInUser();
        if (user == null) {
            System.out.println("Aucun utilisateur connecté.");
            return;
        }

        System.out.println("***** RETRAIT *****");
        accountService.listerAccount(user);

        System.out.print("Entrez l'ID du compte : ");
        String accountId = sc.nextLine();

        System.out.print("Montant à retirer : ");
        double amount = sc.nextDouble();
        sc.nextLine(); // Nettoyer le buffer

        transactionService.withdraw(accountId, amount);
    }
    private void effectuerVirement() {
        User user = authService.getLoggedInUser();
        if (user == null) {
            System.out.println("Aucun utilisateur connecté.");
            return;
        }

        System.out.println("***** VIREMENT *****");
        accountService.listerAccount(user);

        System.out.print("Entrez l'ID du compte source : ");
        String fromAccountId = sc.nextLine();

        System.out.print("Entrez l'ID du compte destination : ");
        String toAccountId = sc.nextLine();

        System.out.print("Montant à transférer : ");
        double amount = sc.nextDouble();
        sc.nextLine(); // Nettoyer le buffer

        transactionService.transfer(fromAccountId, toAccountId, amount);
    }
}
