package com.n26.transactionstatistics.repositories.impl;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import com.n26.transactionstatistics.exceptions.ExpiredTransactionException;
import com.n26.transactionstatistics.models.Transaction;
import com.n26.transactionstatistics.repositories.TransactionRepository;

import static com.n26.transactionstatistics.utils.TransactionsUtils.MAX_ALLOWED_SECONDS;
import static com.n26.transactionstatistics.utils.TransactionsUtils.isValidTransaction;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

/**
 * Repo for transactions and statistics.
 * Created by npsaradhhi
 */
@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

    private ConcurrentNavigableMap<Long, Transaction> transactionsMap = new ConcurrentSkipListMap<>();

    /**
     * Adds transaction to transactionsMap if the transaction made in valid time.
     * Else throw ExpiredTransactionException
     * @param transaction
     */
    @Override
    public void addTransaction(Transaction transaction) {
        Long transactionTimeStamp = transaction.getTimestamp();
        if(!isValidTransaction(transactionTimeStamp)) {
            throw new ExpiredTransactionException("Invalid transaction time");
        }
        long toSeconds = Instant.ofEpochMilli(transactionTimeStamp).getEpochSecond();
        //ToDo: There is possibility multiple transactions can happen in a seconds.
        transactionsMap.put(toSeconds, transaction);
    }

    /**
     * Gets all the transaction for last 60 seconds.
     * @return list of {@link Transaction}
     */
    @Override
    public List<Transaction> getAllTransactions() {
        return getTransactionsFromLastSixtySeconds()
                .values()
                .stream()
                .collect(Collectors.toList());
    }

    /**
     * Removes older transactions for every fix seconds.
     */
    @Scheduled(fixedDelay = 5 * 1000)
    public void removeOlderTransaction() {
        final ConcurrentNavigableMap<Long, Transaction> olderTransaction = getTransactionOlderThanSixtySeconds();
        if(olderTransaction.size() > 0) {
            olderTransaction.clear();
        }
    }

    private ConcurrentNavigableMap<Long, Transaction> getTransactionsFromLastSixtySeconds() {
        return transactionsMap.tailMap(Instant.now().minusSeconds(MAX_ALLOWED_SECONDS).getEpochSecond());
    }

    private ConcurrentNavigableMap<Long, Transaction> getTransactionOlderThanSixtySeconds() {
        return transactionsMap.headMap(Instant.now().minusSeconds(MAX_ALLOWED_SECONDS).getEpochSecond());
    }
    
    /**
     * Deleted transaction from the transactionsMap.
     * Else throw InvalidTimeException
     * @param transaction
     */
    @Override
    public void deleteTransaction(Transaction transaction) {
        Long transactionTimeStamp = transaction.getTimestamp();
        long toSeconds = Instant.ofEpochMilli(transactionTimeStamp).getEpochSecond();
        transactionsMap.remove(toSeconds);
    }
}
