package dev.alejandro.transactionalproxydemo.account.adapter.http;

import java.io.Serializable;
import java.math.BigDecimal;

public record TransferRequest(String accountIdFrom, String accountIdTo, BigDecimal amount) implements Serializable {
    public boolean isValid() {
        return accountIdFrom != null && !accountIdFrom.isBlank() && accountIdTo != null && !accountIdTo.isBlank() && amount() != null && amount.compareTo(BigDecimal.ZERO) > 0;
    }
}
