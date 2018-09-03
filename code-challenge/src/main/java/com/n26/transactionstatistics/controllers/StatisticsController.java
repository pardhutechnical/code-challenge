package com.n26.transactionstatistics.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.n26.transactionstatistics.models.Statistics;
import com.n26.transactionstatistics.services.StatisticsService;

/**
 * Statistics controller.
 * Created by npsaradhhi.
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    /**
     * Returns transaction statistics
     * @return
     */
    @GetMapping
    public Statistics getStatistics() {
        return statisticsService.getStatistics();
    }
}
