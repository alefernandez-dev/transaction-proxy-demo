package dev.alejandro.transactionalproxydemo.account.application;

import dev.alejandro.transactionalproxydemo.account.domain.Account;
import dev.alejandro.transactionalproxydemo.account.domain.Accounts;

import java.util.List;

public class ListAccountsService {

    private final Accounts repository;

    public ListAccountsService(Accounts repository) {
        this.repository = repository;
    }


    public List<Account> list(int limit) {
            return repository.listWithLimit(limit);
    }

}
