package com.n26.transactionstatistics.repositories;

import java.util.List;

import com.n26.transactionstatistics.models.Transaction;

/**
 * Created by npsaradhhi
 */
public interface TransactionRepository {

    void addTransaction(Transaction transaction);

    List<Transaction> getAllTransactions();
    
    void deleteTransaction(Transaction transaction);
}
