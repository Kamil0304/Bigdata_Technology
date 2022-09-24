CREATE EXTERNAL TABLE tweets(
	id Bigint,
	username String,
	text String,
	is_retweet boolean,
	time_stamp TIMESTAMP,
	lang String,
	in_reply_to_status_id String,
	hashtags STRING
)
STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler'
WITH SERDEPROPERTIES (
	"hbase.columns.mapping" = ":key, tweet-info:text, tweet-info:is_retweet, tweet-info:reply_to, tweet-info:hashtags, general-info:username, general-info:timestamp_ms, general-info:lang"
)

TBLPROPERTIES(
	"hbase.table.name" = "tweets",
	"hbase.mapred.output.outputtable" = "tweets"
);
