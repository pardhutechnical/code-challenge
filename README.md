# code-challenge
# Transactionstatistics
Calculate​ ​ realtime​ ​ statistic​ ​ from​ ​ the​ ​ last​ ​ 60​ ​ seconds

# Why ConcurrentSkipListMap?
1. API​ ​ have​ ​ to​ ​ be​ ​ threadsafe​ ​ with​ ​ concurrent​ ​ requests
2. In​ ​ memory​ ​ solution​ ​ without​ ​ database
3. Endpoints​ ​ have​ ​ to​ ​ execute​ ​ in​ ​ constant​ ​ time​ ​ and​ ​ memory​

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
"amount":45.3,
"timestamp":1526755536459
}

# For /statistics call

Response

{
	"sum": 93.9,
	"avg": 31.3,
	"max": 45.3,
	"min": 24.3,
	"count": 3
}
