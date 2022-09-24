package cs523.tweets;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.io.compress.Compression.Algorithm;

public class TweetHbaseTable {
	private final String TABLE_NAME = "tweets";
	private final String CF_DEFAULT = "tweet-info";
	private final String CF_GENERAL = "general-info";

	private final byte[] CF_DEFAULT_BYTES = CF_DEFAULT.getBytes();
	private final byte[] CF_GENERAL_BYTES = CF_GENERAL.getBytes();

	private Table tweets;

	public TweetHbaseTable() throws IOException {
		Configuration config = HBaseConfiguration.create();
		
		try (Connection conn = ConnectionFactory.createConnection(config);
			Admin admin = conn.getAdmin()) {

				System.out.print("Creating table.... ");

				HTableDescriptor table = new HTableDescriptor(
						TableName.valueOf(TABLE_NAME));

				table.addFamily(new HColumnDescriptor(CF_DEFAULT)
						.setCompressionType(Algorithm.NONE));
				table.addFamily(new HColumnDescriptor("general-info"));

				if (!admin.tableExists(table.getTableName()))
					admin.createTable(table);

				tweets = conn.getTable(TableName.valueOf(TABLE_NAME));

				System.out.println(" Done!");
		
		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	public void putTweet(Tweet tweet) throws IOException {

		Put row = new Put(tweet.getId().getBytes());

		row.addColumn(CF_DEFAULT_BYTES, "text".getBytes(), tweet.getText()
				.getBytes());
		row.addColumn(CF_DEFAULT_BYTES, "hashtags".getBytes(),
				String.join(", ", tweet.getHashTags()).getBytes());
		row.addColumn(CF_DEFAULT_BYTES, "is_retweet".getBytes(), String
				.valueOf(tweet.isRetweet()).getBytes());

		if (tweet.getInReplyToStatusId() != null
				&& "null".equals(tweet.getInReplyToStatusId()))
			row.addColumn(CF_DEFAULT_BYTES, "reply_to".getBytes(), tweet
					.getInReplyToStatusId().getBytes());

		row.addColumn(CF_GENERAL_BYTES, "username".getBytes(), tweet
				.getUsername().getBytes());
		row.addColumn(CF_GENERAL_BYTES, "timestamp_ms".getBytes(), tweet
				.getTimeStamp().getBytes());
		row.addColumn(CF_GENERAL_BYTES, "lang".getBytes(), tweet.getLang()
				.getBytes());

		tweets.put(row);
	}
}
