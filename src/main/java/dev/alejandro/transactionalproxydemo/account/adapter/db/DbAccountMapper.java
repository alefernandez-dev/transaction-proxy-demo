package dev.alejandro.transactionalproxydemo.account.adapter.db;

import dev.alejandro.transactionalproxydemo.account.domain.Account;

final class DbAccountMapper {
    private DbAccountMapper() {
    }

    static AccountJpa toAccountJpa(Account account) {
        var accountJpa = new AccountJpa();
        accountJpa.accountId = account.accountId();
        accountJpa.clientId = account.clientId();
        accountJpa.balance = account.balance();
        return accountJpa;
    }

    static Account toAccount(AccountJpa accountJpa) {
        return new Account(accountJpa.accountId, accountJpa.clientId, accountJpa.balance);
    }
}
