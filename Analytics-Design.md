# Google analytics design questions and answers:

High level design of analytic system to handle large read/write volume

## Requirements:
-   Handle large write volume: Billions write events per day. 
-   Handle large read volume: Read/Query patterns with time series related metrics. 
-   Provide metrics to customers with at most one hour delay.
-   Run with minimum downtime.
-   Ability to reprocess historical data in case of bugs in the processing logic. 

We can easily achieve this by using Kafka and InfluxDB for the time-series database. 
1.  Kafka by design can handle writing a large volume even on commodity hardware. 
    We can use our own hosted Kafka or any Cloud providers one (AWS is what I am most 
    familiar with) Check out Kafka benchmark on [here](https://engineering.linkedin.com/kafka/benchmarking-apache-kafka-2-million-writes-second-three-cheap-machines)
    From his benchmark, Kafka can handle up to 2 million writes per second on three cheap machines. 
    Or about 193MB/sec with three producers and 3 async replication. With both producer and 
    consumer running about the same time, they get about 800k records or 75.8MB/sec. This would
    definitely fulfill our first requirement. 
2.  For this second requirement, I would suggest using influxDB for its built-in time series 
    capabilities. We could use `Kafka Connect` to connect our Kafka brokers into influxDB. 
    InfluxDB has a high performance datastore written specifically for time series data. 
    The downside, maybe we need the enterprise edition to eliminate single point of failure
    or we have to do the replication logic and partitioning ourselves. Sample performance
    comparison vs Cassandra in [here](https://www.influxdata.com/blog/influxdb-vs-cassandra-time-series/)
3.  We can treat our connector to InfluxDB as Kafka consumer, based on how rapid our data 
    rate is, we can see what would be the consumer offset for one hour. We have to make
    sure that all the Kafka consumer are consuming within the threshold(consumer lag) 
    and we can setup an alert in case it falls below that threshold. 
    Because Kafka and InfluxDB is horizontally scalable, we can easily scale 
    them just in case we get an sudden increase in data. 
    By doing this, we are ensuring that customer will have access to one hour worth of data
    or newer. We can also connect our InfluxDB to Grafana for easy visualization and give
    that access to our customer so they can easily monitor their analytics there. 
4.  By ensuring our Kafka replication factor is high enough (at least 3), and then 
    replicating it by using MirrorMaker over various AZ(Availability Zones) that 
    would make it resilient from any single point of failure. In doing this, for example
    whenever one of the AWS Availability zone went down, we will be fine since we will be
    replicating our data in multiple AZs. If we are doing a self-hosting or using a different
    cloud providers then we also have to keep this in mind to minimize any possible downtime. 
5.  We can always reprocess Kafka data since we can set its retention time. We can reset 
    the offset of the Kafka consumer and change its consumer group to reprocess data again.
    There's more details and options in [here](https://www.confluent.io/blog/data-reprocessing-with-kafka-streams-resetting-a-streams-application/)
    
    
