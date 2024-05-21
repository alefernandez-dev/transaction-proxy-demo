package dev.alejandro.transactionalproxydemo.account.adapter.http;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public record AccountResponse(UUID accountId, UUID clientId, BigDecimal balance) implements Serializable {
}
