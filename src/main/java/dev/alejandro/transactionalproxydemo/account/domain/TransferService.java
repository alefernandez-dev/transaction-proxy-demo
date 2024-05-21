package dev.alejandro.transactionalproxydemo.account.domain;

import dev.alejandro.transactionalproxydemo.account.application.AccountNotFoundForTransferError;
import dev.alejandro.transactionalproxydemo.account.application.TransferToSameAccountError;

import java.math.BigDecimal;
import java.util.UUID;

public interface TransferService {
    void transfer(UUID accountIdFrom, UUID accountIdTo, BigDecimal amount) throws TransferToSameAccountError, AccountNotFoundForTransferError, LowBalanceError;
}
