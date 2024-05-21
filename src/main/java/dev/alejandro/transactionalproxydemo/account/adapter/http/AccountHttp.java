package dev.alejandro.transactionalproxydemo.account.adapter.http;

import dev.alejandro.transactionalproxydemo.account.application.ListAccountsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountHttp {

    private final ListAccountsService listAccountsService;

    public AccountHttp(ListAccountsService listAccountsService) {
        this.listAccountsService = listAccountsService;
    }


    @GetMapping
    public ResponseEntity<List<AccountResponse>> list(@RequestParam(required = false, defaultValue = "10") int limit) {
        return ResponseEntity.ok(listAccountsService.list(limit).stream().map(HttpAccountMapper::toResponse).toList());
    }

}
