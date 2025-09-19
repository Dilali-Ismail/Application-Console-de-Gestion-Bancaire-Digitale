package repository.interfaces;

import model.Account;

import java.lang.module.FindException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {
    void addAccount(Account account);
    Optional<Account> findById(String accountId);
    ArrayList<Account> findByOwnerId(UUID ownerid);
}
