package com.n26.transactionstatistics.utils;

import java.time.Duration;
import java.time.Instant;

/**
 * Created by npsaradhhi
 */
public class TransactionsUtils {

    public static final int MAX_ALLOWED_SECONDS = 60;

    /**
     * Returns true if the transactionTime is less then MAX_ALLOWED_SECONDS.
     *
     * @param transactionTime
     * @return
     */
    public static boolean isValidTransaction(Long transactionTime) {
        return Duration.between(Instant.ofEpochMilli(transactionTime), Instant.now())
                .getSeconds() < MAX_ALLOWED_SECONDS;
    }
}
