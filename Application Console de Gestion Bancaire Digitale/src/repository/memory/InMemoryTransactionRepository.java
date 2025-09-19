package repository.memory;

import model.Transaction;
import repository.interfaces.TransactionRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTransactionRepository implements TransactionRepository {
    private final Map<String, Transaction> transactions = new HashMap<>();

    public void save(Transaction transaction) {
        transactions.put(transaction.getId().toString(), transaction);
    }
    public List<Transaction> findByAccountId(String accountId) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : transactions.values()) {
            if (t.getAccountId().equals(accountId)) {
                result.add(t);
            }
        }
        return result;
    }
}
