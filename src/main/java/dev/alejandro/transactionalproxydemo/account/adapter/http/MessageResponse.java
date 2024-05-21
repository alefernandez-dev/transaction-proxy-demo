package dev.alejandro.transactionalproxydemo.account.adapter.http;

import java.io.Serializable;

public record MessageResponse(String message, int code) implements Serializable {
}
