package ui;

import model.User;
import service.AccountService;
import service.AuthService;

import java.util.Scanner;

public class Menu {
    private final AuthService authService;
    private final AccountService accountService;
    private final Scanner sc = new Scanner(System.in);

    public Menu(AuthService authService, AccountService accountService) {
        this.authService = authService;
        this.accountService = accountService;
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
            System.out.println("3. Déconnexion");
            System.out.print("Votre choix : ");
            int choix = sc.nextInt();
            sc.nextLine();

            switch (choix) {
                case 1 -> accountService.creerAccount(user);
                case 2 -> {
                    accountService.listerAccount(user);
                }
                case 3 ->{
                    authService.logout();
                    return;
                }
                default -> System.out.println("Choix invalide !");
            }
        }
    }

}
