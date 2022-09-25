package com.bank.api.web;

import com.bank.api.model.account.AccountData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.bank.api.utils.TestUtils.getAccountData1;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class that implements integration tests of the AccountController features
 */
@SpringBootTest
public class AccountControllerIT {

    @Autowired
    AccountController accountController;

    /**
     * Method to test service that search for an Account by the accountId
     */
    @Test
    public void shouldGetAccountInformation() {

        //assemble
        AccountData expectedAccountData = accountController.saveAccount(getAccountData1());

        //act
        AccountData actualAccountData = accountController.getAccountByAccountId(expectedAccountData.getAccountId());

        //assert
        assertEquals(expectedAccountData.getAccountType(), actualAccountData.getAccountType());
        assertEquals(expectedAccountData.getAccountId(), actualAccountData.getAccountId());
        assertEquals(expectedAccountData.getAmount(), actualAccountData.getAmount());
        assertEquals(expectedAccountData.getCurrency(), actualAccountData.getCurrency());
        assertEquals(expectedAccountData.getCustomerId(), actualAccountData.getCustomerId());
    }
}