# Google analytics design questions and answers:

High level design of analytic system to handle large read/write volume

## Requirements:
-   Handle large write volume: Billions write events per day. 
-   Handle large read volume: Read/Query patterns with time series related metrics. 
-   Provide metrics with customers with at most one hour delay.
-   Run with minimum downtime.
-   Ability to reprocess historical data in case of bugs in the processing logic. 

We can easily achieve this by using Kafka and InfluxDB for the time-series database. 
1. Kafka by default can handle writing a large volume even on commodity hardware. 
    We can use our own hosted Kafka or any Cloud providers one (AWS is what I am most 
    familiar with) Check out Kafka benchmark on [here](https://engineering.linkedin.com/kafka/benchmarking-apache-kafka-2-million-writes-second-three-cheap-machines)
    
