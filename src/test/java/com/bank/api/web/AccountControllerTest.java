package com.bank.api.web;

import com.bank.api.model.account.AccountData;
import com.bank.api.service.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.bank.api.utils.TestUtils.getAccountData1;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class that implements tests of the AccountController features
 */
@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountServiceImpl accountService;

    /**
     * Method to test service that search for an Account by the accountId
     */
    @Test
    public void shouldGetAccountDetails() {

        //assemble
        AccountData expectedAccountData = getAccountData1();
        Long accountId = expectedAccountData.getAccountId();
        Mockito.when(accountService.getAccountByAccountId(accountId)).thenReturn(expectedAccountData);

        //act
        AccountData actualAccountData = accountController.getAccountByAccountId(accountId);

        //assert
        assertEquals(expectedAccountData.getAccountType(), actualAccountData.getAccountType());
        assertEquals(expectedAccountData.getAccountId(), actualAccountData.getAccountId());
        assertEquals(expectedAccountData.getAmount(), actualAccountData.getAmount());
        assertEquals(expectedAccountData.getCurrency(), actualAccountData.getCurrency());
        assertEquals(expectedAccountData.getCustomerId(), actualAccountData.getCustomerId());
    }

    /**
     * Method to test the creation of an Account.
     */
    @Test
    public void shouldSaveAccount() {

        //assemble
        AccountData expectedAccountData = getAccountData1();
        Mockito.when(accountService.saveAccount(expectedAccountData)).thenReturn(expectedAccountData);

        //act
        AccountData actualAccountData = accountController.saveAccount(expectedAccountData);

        //assert
        assertEquals(expectedAccountData.getAccountType(), actualAccountData.getAccountType());
        assertEquals(expectedAccountData.getAccountId(), actualAccountData.getAccountId());
        assertEquals(expectedAccountData.getAmount(), actualAccountData.getAmount());
        assertEquals(expectedAccountData.getCurrency(), actualAccountData.getCurrency());
        assertEquals(expectedAccountData.getCustomerId(), actualAccountData.getCustomerId());

    }
}
