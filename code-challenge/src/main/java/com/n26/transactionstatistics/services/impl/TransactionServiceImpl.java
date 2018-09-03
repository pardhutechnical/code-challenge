package com.n26.transactionstatistics.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n26.transactionstatistics.models.Transaction;
import com.n26.transactionstatistics.repositories.TransactionRepository;
import com.n26.transactionstatistics.services.TransactionService;

/**
 * Transactions service.
 * Created by npsaradhhi
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void addTransaction(Transaction transaction) {
        transactionRepository.addTransaction(transaction);
    }
    
    @Override
    public void deleteTransaction(Transaction transaction) {
        transactionRepository.deleteTransaction(transaction);
    }
}
