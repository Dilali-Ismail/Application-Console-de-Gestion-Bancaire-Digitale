package service;

import model.User;
import repository.interfaces.UserRepository;

import java.util.Scanner;

public class AuthService {
    private final UserRepository userRepository;
    private User loggedInUser;
    private final Scanner sc = new Scanner(System.in);

    public AuthService(UserRepository userRepo) {
        this.userRepository = userRepo;
    }
    public void register() {
        System.out.println("***** INSCRIPTION *****");
        System.out.print("Nom complet: ");
        String name = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Mot de passe: ");
        String password = sc.nextLine();

        User user = new User(name, email, password);
        userRepository.addUser(user);
        System.out.println("Utilisateur créé avec succès !");
    }
    public void login() {
        System.out.println("***** CONNEXION *****");
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Mot de passe: ");
        String password = sc.nextLine();

        userRepository.findByEmail(email).ifPresentOrElse(user -> {
            if (user.getPassword().equals(password)) {
                loggedInUser = user;
                System.out.println("Connexion réussie ! Bonjour " + user.getFullName());
            } else {
                System.out.println("Mot de passe incorrect !");
            }
        }, () -> System.out.println("Utilisateur non trouvé !"));
    }
    public User getLoggedInUser() {
        return loggedInUser;
    }
    public void changePassword() {
        if (loggedInUser == null) {
            System.out.println("Aucun utilisateur connecté.");
            return;
        }

        System.out.println("***** CHANGEMENT DE MOT DE PASSE *****");
        System.out.print("Ancien mot de passe : ");
        String oldPassword = sc.nextLine();

        if (!loggedInUser.getPassword().equals(oldPassword)) {
            System.out.println("Ancien mot de passe incorrect !");
            return;
        }

        System.out.print("Nouveau mot de passe (min 6 caractères) : ");
        String newPassword = sc.nextLine();

        if (newPassword.length() < 6) {
            System.out.println("Le mot de passe doit contenir au moins 6 caractères !");
            return;
        }

        // Dans une vraie application, on mettrait à jour le repository
        // Pour l'instant, on modifie directement l'objet utilisateur
        // (Dans une version finale, il faudrait un UserRepository.updateUser())
        loggedInUser = new User(
                loggedInUser.getFullName(),
                loggedInUser.getEmail(),
                newPassword
        );

        System.out.println("Mot de passe changé avec succès !");
    }
    public void logout() {
        loggedInUser = null;
        System.out.println("Déconnecté !");
    }

}
