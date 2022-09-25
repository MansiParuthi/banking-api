package com.bank.api.service;

import com.bank.api.exception.AccountNotFoundException;
import com.bank.api.model.account.AccountData;
import com.bank.api.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Class to implement methods for Account objects.
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    /**
     * Method to find an account by accountId in the database.
     *
     * @param accountId
     * @return AccountData object
     */
    @Override
    public AccountData getAccountByAccountId(Long accountId) {
        Optional<AccountData> accountData = accountRepository.findByAccountIdEquals(accountId);
        if (accountData.isPresent())
            return accountData.get();
        else {
            log.info("Account not found for account id : {}", accountId);
            throw new AccountNotFoundException(String.format("Account not found for account id : %s", accountId.toString()));
        }
    }

    /**
     * Method that saves an account in the database.
     *
     * @param accountData
     * @return AccountData object
     */
    @Override
    public AccountData saveAccount(AccountData accountData) {

        return accountRepository.save(accountData);
    }
}
