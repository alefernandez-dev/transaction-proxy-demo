package dev.alejandro.transactionalproxydemo.account.configuration;

import dev.alejandro.transactionalproxydemo.account.adapter.db.AccountsH2Adapter;
import dev.alejandro.transactionalproxydemo.account.adapter.db.AccountsJpa;
import dev.alejandro.transactionalproxydemo.account.adapter.http.proxy.TransactionalTransferServiceProxy;
import dev.alejandro.transactionalproxydemo.account.application.DefaultTransferService;
import dev.alejandro.transactionalproxydemo.account.application.ListAccountsService;
import dev.alejandro.transactionalproxydemo.account.domain.Accounts;
import dev.alejandro.transactionalproxydemo.account.domain.TransferService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountBeansConfiguration {

    @Bean
    Accounts accounts(AccountsJpa accountJpa) {
        return new AccountsH2Adapter(accountJpa);
    }

    @Bean
    ListAccountsService listAccountsService(Accounts accounts) {
        return new ListAccountsService(accounts);
    }

    @Bean
    TransferService transferService(Accounts accounts) {
        return new DefaultTransferService(accounts);
    }

    @Bean(name = "transactional")
    TransferService transferServiceTransactional(TransferService transferService) {
        return new TransactionalTransferServiceProxy(transferService);
    }

}
