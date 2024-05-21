package dev.alejandro.transactionalproxydemo.account.application;

import dev.alejandro.transactionalproxydemo.account.domain.Accounts;
import dev.alejandro.transactionalproxydemo.account.domain.LowBalanceError;
import dev.alejandro.transactionalproxydemo.account.domain.TransferService;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

public class DefaultTransferService implements TransferService {

    private final Accounts repository;

    public DefaultTransferService(Accounts repository) {
        this.repository = repository;
    }

    @Override
    public void transfer(UUID accountIdFrom, UUID accountIdTo, BigDecimal amount) throws TransferToSameAccountError, AccountNotFoundForTransferError, LowBalanceError {

        if (accountIdFrom.equals(accountIdTo)) {
            throw new TransferToSameAccountError();
        }

        var accountFrom = repository.findAccountById(accountIdFrom).orElseThrow(() -> new AccountNotFoundForTransferError(accountIdFrom));
        var accountTo = repository.findAccountById(accountIdTo).orElseThrow(() -> new AccountNotFoundForTransferError(accountIdTo));

        if (!accountFrom.hasSufficientBalance(amount)) {
            throw new LowBalanceError();
        }

        var newAccountFrom = accountFrom.withdraw(amount);
        var newAccountTo = accountTo.deposit(amount);

        repository.persist(newAccountFrom);

        // This if block is present solely to throw an exception and test the transaction
        if((new Random().nextInt(10) + 1) < 8 ) {
            throw new RuntimeException("upss!");
        }

        repository.persist(newAccountTo);

    }
}
