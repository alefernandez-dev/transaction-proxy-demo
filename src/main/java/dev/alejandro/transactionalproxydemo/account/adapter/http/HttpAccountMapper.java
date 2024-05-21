package dev.alejandro.transactionalproxydemo.account.adapter.http;

import dev.alejandro.transactionalproxydemo.account.domain.Account;

final class HttpAccountMapper {
    private HttpAccountMapper() {
    }

    static AccountResponse toResponse(Account account) {
        return new AccountResponse(account.accountId(), account.clientId(), account.balance());
    }
}

