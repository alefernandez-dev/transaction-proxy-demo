package dev.alejandro.transactionalproxydemo.account.adapter.http;

import dev.alejandro.transactionalproxydemo.account.application.AccountNotFoundForTransferError;
import dev.alejandro.transactionalproxydemo.account.application.TransferToSameAccountError;
import dev.alejandro.transactionalproxydemo.account.domain.LowBalanceError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    private final static Logger log = LoggerFactory.getLogger(ErrorHandler.class);

    @ExceptionHandler({LowBalanceError.class, AccountNotFoundForTransferError.class, TransferToSameAccountError.class})
    public ResponseEntity<MessageResponse> errorHandler(RuntimeException e) {

        return switch (e) {
            case LowBalanceError lbe -> ResponseEntity.badRequest().body(new MessageResponse("insufficient balance to perform the operation", 400));
            case AccountNotFoundForTransferError anf -> ResponseEntity.badRequest().body(new MessageResponse("transfer account with id: " + anf.getAccountId() + "not found", 404));
            case TransferToSameAccountError tae -> ResponseEntity.badRequest().body(new MessageResponse("same account to transfer", 400));
            default -> throw new IllegalStateException(e);
        };

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageResponse> handlerException(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.internalServerError().build();
    }

}
