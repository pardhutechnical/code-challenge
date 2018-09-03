package com.n26.transactionstatistics.exceptions;

/**
 * Expired Transaction Exception.
 * Created by npsaradhhi
 */
public class ExpiredTransactionException extends RuntimeException {
    public ExpiredTransactionException(String errorMessage) {
        super(errorMessage);
    }
}
