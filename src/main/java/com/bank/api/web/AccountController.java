package com.bank.api.web;

import com.bank.api.model.account.AccountData;
import com.bank.api.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * RestController that creates all service end-points related to the account.
 */
@Slf4j
@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * Method to get account by the id.
     *
     * @param accountId - the id of the account
     * @return AccountData object
     * <p>
     * HTTP Status:
     * <p>
     * 200 - OK: Everything worked as expected.
     * 400 - Bad Request: The request was unacceptable, often due to missing a required parameter.
     */
    @GetMapping("/{accountId}")
    public AccountData getAccountByAccountId(
            @PathVariable(value = "accountId") Long accountId) {
        log.info("Getting account details for account: {}", accountId);
        return accountService.getAccountByAccountId(accountId);
    }

    /**
     * Method to creates a account.
     *
     * @param accountData
     * @return AccountData object
     * <p>
     * HTTP Status:
     * <p>
     * 201 - Created: Everything worked as expected.
     * 400 - Bad Request: The request was unacceptable, often due to missing a required parameter.
     */
    @PostMapping
    public AccountData saveAccount(@RequestBody AccountData accountData) {
        log.info("Creating account for customerId {}", accountData.getCustomerId());
        return accountService.saveAccount(accountData);
    }
}
