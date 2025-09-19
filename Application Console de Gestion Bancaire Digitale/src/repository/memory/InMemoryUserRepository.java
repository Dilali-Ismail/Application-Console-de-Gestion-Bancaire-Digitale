package repository.memory;

import model.User;
import repository.interfaces.UserRepository;

import java.util.ArrayList;
import java.util.Optional;

public class InMemoryUserRepository implements UserRepository {
    private final ArrayList<User> users = new ArrayList<>();

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return users.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }
    @Override
    public void updateUser(User updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getId().equals(updatedUser.getId())) {
                users.set(i, updatedUser);
                return;
            }
        }
        System.out.println("Utilisateur non trouve pour la mise a jour");
    }
}
