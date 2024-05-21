package dev.alejandro.transactionalproxydemo.account.adapter.http.proxy;

import dev.alejandro.transactionalproxydemo.account.application.AccountNotFoundForTransferError;
import dev.alejandro.transactionalproxydemo.account.application.TransferToSameAccountError;
import dev.alejandro.transactionalproxydemo.account.domain.LowBalanceError;
import dev.alejandro.transactionalproxydemo.account.domain.TransferService;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

public class TransactionalTransferServiceProxy implements TransferService {
    private final TransferService transferService;

    public TransactionalTransferServiceProxy(TransferService transferService) {
        this.transferService = transferService;
    }

    @Override
    @Transactional
    public void transfer(UUID accountIdFrom, UUID accountIdTo, BigDecimal amount) throws TransferToSameAccountError, AccountNotFoundForTransferError, LowBalanceError {
        transferService.transfer(accountIdFrom, accountIdTo, amount);
    }
}
