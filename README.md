# Bigdata_Technology
I have two producer and consumer. 

For consumer part , below are cmy configuration informations where required tools are setup

  kafaka zookeeper - 
    -cd "D:\MIU\serverBD\kafka_2.10-0.9.0.1\bin\windows"
    - zookeeper-server-start.bat D:\MIU\serverBD\kafka_2.10-0.9.0.1\config\zookeeper.properties
    
Kafaka server - 
  - cd "D:\MIU\serverBD\kafka_2.10-0.9.0.1\bin\windows"
  - kafka-server-start D:\MIU\serverBD\kafka_2.10-0.9.0.1\config\server.properties
  
  
Creating kafka tpoic - 
- cd "D:\MIU\serverBD\kafka_2.10-0.9.0.1\bin\windows"
- kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic tweets
