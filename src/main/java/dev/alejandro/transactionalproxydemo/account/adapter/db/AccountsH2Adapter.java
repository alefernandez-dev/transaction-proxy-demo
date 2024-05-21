package dev.alejandro.transactionalproxydemo.account.adapter.db;

import dev.alejandro.transactionalproxydemo.account.domain.Account;
import dev.alejandro.transactionalproxydemo.account.domain.Accounts;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AccountsH2Adapter implements Accounts {

    private final AccountsJpa repository;

    public AccountsH2Adapter(AccountsJpa repository) {
        this.repository = repository;
    }

    @Override
    public void persist(Account account) {
        repository.save(DbAccountMapper.toAccountJpa(account));
    }

    @Override
    public Optional<Account> findAccountById(UUID accountId) {
        return repository.findById(accountId).map(DbAccountMapper::toAccount);
    }

    @Override
    public Optional<Account> findByClientId(UUID clientId) {
        return repository.findByClientId(clientId).map(DbAccountMapper::toAccount);
    }

    @Override
    public List<Account> listWithLimit(int limit) {
        return repository.listWithLimit(limit).stream().map(DbAccountMapper::toAccount).toList();
    }


}
