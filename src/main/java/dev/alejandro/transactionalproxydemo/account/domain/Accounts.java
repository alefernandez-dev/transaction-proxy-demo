package dev.alejandro.transactionalproxydemo.account.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Accounts {
    void persist(Account account);
    Optional<Account> findAccountById(UUID accountId);
    Optional<Account> findByClientId(UUID clientId);
    List<Account> listWithLimit(int limit);
}
