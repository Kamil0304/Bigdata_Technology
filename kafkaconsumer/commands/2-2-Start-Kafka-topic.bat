cd "D:\MIU\serverBD\kafka_2.10-0.9.0.1\bin\windows"
kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic tweets


