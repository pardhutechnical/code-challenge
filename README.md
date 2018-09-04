# code-challenge
# Transactionstatistics
Calculate​ ​ realtime​ ​ statistic​ ​ from​ ​ the​ ​ last​ ​ 60​ ​ seconds

# To run the test
mvn test

# DB used
  ConcurrentSkipListMap
  
# Technology used
1. Spring-boot
2. Java-8
3. Spring-Mock-MVC


# For /transactions call

Request Payload

{
  "amount": "12.3343",
  "timestamp": "2018-07-17T09:59:51.312Z"
}

# For /statistics call

Response

{
  "sum": "1000.00",
  "avg": "100.53",
  "max": "200000.49",
  "min": "50.23",
  "count": 10
}
