package com.bank.api.service;

import com.bank.api.model.account.AccountData;

/**
 * Interface that provides methods for Account objects.
 */
public interface AccountService {

    /**
     * Method to find an account by accountId in the database.
     *
     * @param accountId
     * @return AccountData object
     */
    AccountData getAccountByAccountId(Long accountId);

    /**
     * Method that saves an account in the database.
     *
     * @param accountData
     * @return AccountData object
     */
    AccountData saveAccount(AccountData accountData);
}
