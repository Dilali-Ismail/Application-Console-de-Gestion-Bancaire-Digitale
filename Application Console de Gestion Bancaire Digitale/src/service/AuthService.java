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

    public void logout() {
        loggedInUser = null;
        System.out.println("Déconnecté !");
    }

}
