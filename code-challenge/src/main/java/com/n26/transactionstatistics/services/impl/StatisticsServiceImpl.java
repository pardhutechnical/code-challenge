package com.n26.transactionstatistics.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n26.transactionstatistics.models.Statistics;
import com.n26.transactionstatistics.models.Transaction;
import com.n26.transactionstatistics.repositories.TransactionRepository;
import com.n26.transactionstatistics.services.StatisticsService;

import java.util.DoubleSummaryStatistics;
import java.util.List;

/**
 * Statistics service.
 * Created by npsaradhhi
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * Get all the transaction for last 60 seconds and calculates
     * statistics using {@link DoubleSummaryStatistics}
     * @return
     */
    @Override
    public Statistics getStatistics() {
        List<Transaction> transactionList = transactionRepository.getAllTransactions();
        DoubleSummaryStatistics summaryStatistics = transactionList.stream()
                .mapToDouble(Transaction::getAmount)
                .summaryStatistics();
        return new Statistics(summaryStatistics.getSum(),
                summaryStatistics.getAverage(),
                summaryStatistics.getMax(),
                summaryStatistics.getMin(),
                summaryStatistics.getCount());

    }

    public void setTransactionRepository(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public TransactionRepository getTransactionRepository() {
        return transactionRepository;
    }
}
