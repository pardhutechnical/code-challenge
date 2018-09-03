package com.n26.transactionstatistics.services;

import com.n26.transactionstatistics.models.Transaction;

/**
 * Created by npsaradhhi
 */
public interface TransactionService {

    void addTransaction(Transaction transaction);
    
    void deleteTransaction(Transaction transaction);

}
