package com.bank.api.repository;

import com.bank.api.model.account.AccountData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static com.bank.api.utils.TestUtils.getAccountData1;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class that implements tests of the AccountRepository functionalities
 */
@SpringBootTest
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    /**
     * Method that test the repository that search for an Account by the accountId.
     */
    @Test
    public void shouldFindAccountByAccountId() {
        //assemble
        AccountData expectedAccountData = accountRepository.save(getAccountData1());

        //act
        Optional<AccountData> accountData = accountRepository.findByAccountIdEquals(expectedAccountData.getAccountId());
        AccountData actualAccountData = accountData.get();

        //assert
        assertEquals(expectedAccountData.getAccountType(), actualAccountData.getAccountType());
        assertEquals(expectedAccountData.getAccountId(), actualAccountData.getAccountId());
        assertEquals(expectedAccountData.getAmount(), actualAccountData.getAmount());
        assertEquals(expectedAccountData.getCurrency(), actualAccountData.getCurrency());
        assertEquals(expectedAccountData.getCustomerId(), actualAccountData.getCustomerId());
    }

    /**
     * Method that test the repository that search for an Account by the accountId when account does not exist.
     */
    @Test
    public void shouldReturnNullWhenAccountDoesNOTExist() {

        //act
        Optional<AccountData> actualAccountData = accountRepository.findByAccountIdEquals(Long.valueOf("12"));
        assertEquals(actualAccountData, Optional.empty());

    }
}
