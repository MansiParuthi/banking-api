package com.bank.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * Class for Base Entity for Concurrency Locks.
 */
@Setter
@Getter
@MappedSuperclass
public class BaseEntity {

    @Version
    private Long version;
}

