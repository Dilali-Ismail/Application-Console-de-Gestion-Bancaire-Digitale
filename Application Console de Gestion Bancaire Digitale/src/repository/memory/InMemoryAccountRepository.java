package repository.memory;

import model.Account;
import repository.interfaces.AccountRepository;
import service.AccountService;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class InMemoryAccountRepository implements AccountRepository {
    private final ArrayList<Account> accounts = new ArrayList<>();

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Optional<Account> findById(String accountId) {
        return accounts.stream()
                .filter(a -> a.getAccountId().equals(accountId))
                .findFirst();
    }
    public ArrayList<Account> findByOwnerId(UUID ownerId){
        return accounts.stream()
                .filter(acc -> acc.getOwnerUserId().equals(ownerId))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
