package com.n26.transactionstatistics.respositories;

import org.junit.Assert;
import org.junit.Test;

import com.n26.transactionstatistics.exceptions.ExpiredTransactionException;
import com.n26.transactionstatistics.models.Transaction;
import com.n26.transactionstatistics.repositories.TransactionRepository;
import com.n26.transactionstatistics.repositories.impl.TransactionRepositoryImpl;

import java.time.Instant;

/**
 * Created by npsaradhhi
 */
public class TransactionRepositoryTest {

    TransactionRepository transactionRepository = new TransactionRepositoryImpl();

    @Test
    public void shouldAbleToAddTransactionsWithSixtySecondRange() throws InterruptedException{
        //Given
        Transaction transactionOne = new Transaction(12.3, Instant.now().toEpochMilli());
        Thread.sleep(1000);
        Transaction transactionTwo = new Transaction(12.3, Instant.now().toEpochMilli());

        //when
        transactionRepository.addTransaction(transactionOne);
        transactionRepository.addTransaction(transactionTwo);

        //Then
        Assert.assertEquals(2, transactionRepository.getAllTransactions().size());
    }

    @Test(expected = ExpiredTransactionException.class)
    public void shouldNotAbleToAddTransactionsWithExpiredTimeRange() {
        //Given
        Transaction transactionOne = new Transaction(12.3, Instant.now().minusSeconds(60).toEpochMilli());

        //when
        transactionRepository.addTransaction(transactionOne);
    }

}
