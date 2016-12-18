import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Calendar.Builder;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;


/*
 * Code template taken from Twitter4J example 'search'
 * 
 */

/**
 * This class will gather tweets 
 * 
 * @author DannyR
 *
 */
public class TweetSearch {
	
	private final int TWEET_CAP = 100;
	private ArrayList<String> tweetList = new ArrayList<String>();
//	private Date date;
	private String yesterday;
	private String twoDaysAgo;
	
	
	public void restTweet(){
//		Twitter twitter = new TwitterFactory().getInstance();
//		Config config = new Config();
		yesterday = dateBuilder(1); 
	    twoDaysAgo = dateBuilder(2);
		
		
		Twitter twitter = Config.createTwitterObject();
        try {
            Query query = new Query("%20");  //searching empty space to get most 'random' possible distribution of tweets  
            query.setLang("en");
            query.since(twoDaysAgo); 
            query.until(yesterday); 
            query.setCount(10); //the number of tweets per 'page'
            QueryResult result; 
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                	tweetList.add(tweet.getText().toLowerCase()); //seems to be working, @TODO test here
                	if(tweetList.size() > TWEET_CAP) {
                		break;
                	}
 //                   System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                }
                
            } while ((query = result.nextQuery()) != null);
//            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
		
	
	}
	/**
	 * Accessor method for tweetList ArrayList
	 * @return tweetList
	 */
	public ArrayList<String> getTweetList(){
		return tweetList;
	}
	
	/**
	 * This method allows for the tweet search to stay within the timeing of Twitter's limited search window
	 * @param offset a positive number for how many days previous to today
	 * @return a modified date
	 */
	public String dateBuilder(int offset){
		Calendar c = Calendar.getInstance(); //today
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		c.add(Calendar.DATE, -offset);
		String newDate = sdf.format(c.getTime());
		return newDate;
		
	}
	
	/**
	 * Accessor method for yesterday's date
	 * @return String of yesterday's date
	 */
	public String getYesterday(){
		return yesterday;
	}
	
	/**
	 * Accessor method for the date from two days ago
	 * @return String date from two days ago
	 */
	public String getTwoDaysAgo(){
		return twoDaysAgo;
	}

}
