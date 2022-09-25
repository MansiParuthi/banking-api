package com.bank.api.service;

import com.bank.api.exception.AccountNotFoundException;
import com.bank.api.exception.PaymentExecutionException;
import com.bank.api.model.account.AccountData;
import com.bank.api.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.bank.api.utils.TestUtils.getAccountData1;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class that implements tests of the AccountService features.
 */
@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {

    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AccountRepository accountRepository;

    /**
     * Method to test service that search for an Account by the accountId
     */
    @Test
    public void shouldGetAccountDetails() {

        //assemble
        AccountData expectedAccountData = getAccountData1();
        Long accountId = expectedAccountData.getAccountId();
        Mockito.when(accountRepository.findByAccountIdEquals(accountId)).thenReturn(Optional.of(expectedAccountData));

        //act
        AccountData actualAccountData = accountService.getAccountByAccountId(accountId);

        //assert
        assertEquals(expectedAccountData.getAccountType(), actualAccountData.getAccountType());
        assertEquals(expectedAccountData.getAccountId(), actualAccountData.getAccountId());
        assertEquals(expectedAccountData.getAmount(), actualAccountData.getAmount());
        assertEquals(expectedAccountData.getCurrency(), actualAccountData.getCurrency());
        assertEquals(expectedAccountData.getCustomerId(), actualAccountData.getCustomerId());
    }

    /**
     * Method to test service that search for an Account by the accountId when account doesn't exist.
     */
    @Test()
    public void shouldThrowExceptionForEmptyAccountData() {

        //assemble
        Long accountId = Long.valueOf("12");
        Mockito.when(accountRepository.findByAccountIdEquals(accountId)).thenReturn(Optional.empty());

        //act
        try {
            accountService.getAccountByAccountId(accountId);
        } catch (AccountNotFoundException e) {
            assertEquals(e.getMessage(), "Account not found for account id : 12");
        }

    }

    /**
     * Method to test the creation of an Account.
     */
    @Test
    public void shouldSaveAccount() {

        //assemble
        AccountData expectedAccountData = getAccountData1();
        Mockito.when(accountRepository.save(expectedAccountData)).thenReturn(expectedAccountData);

        //act
        AccountData actualAccountData = accountService.saveAccount(expectedAccountData);

        //assert
        assertEquals(expectedAccountData.getAccountType(), actualAccountData.getAccountType());
        assertEquals(expectedAccountData.getAccountId(), actualAccountData.getAccountId());
        assertEquals(expectedAccountData.getAmount(), actualAccountData.getAmount());
        assertEquals(expectedAccountData.getCurrency(), actualAccountData.getCurrency());
        assertEquals(expectedAccountData.getCustomerId(), actualAccountData.getCustomerId());

    }
}