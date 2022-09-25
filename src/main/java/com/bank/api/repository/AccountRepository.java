package com.bank.api.repository;

import com.bank.api.model.account.AccountData;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Interface that implements the Account Repository .
 */
public interface AccountRepository extends CrudRepository<AccountData, Long> {

    Optional<AccountData> findByAccountIdEquals(Long accountId);
}
