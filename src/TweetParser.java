import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class will gather the tweets and parse it by words
 * @author DannyR
 *
 */
public class TweetParser {

	private ArrayList<String> tweets;
	private ArrayList<String> words = new ArrayList<String>();
	private double freq;
	private String testWord;

	/**
	 * Deprecated
	 * Constructor for the Twitter rest API caller if a user is searching for a word from the console

	 * @param rest just a variation to overload the constructor,

	 * @param tw the word to test
	 */
	public TweetParser(String rest, String tw){
		testWord = tw;
		TweetSearch ts = new TweetSearch();
		ts.restTweet();

		tweets = ts.getTweetList(); //@TODO test for this
		parse();

	}


	/**
	 * Empty constructor to do a sample rest API call to get random corpus of words
	 */
	public TweetParser(){
		TweetSearch ts = new TweetSearch();
		ts.restTweet();

		tweets = ts.getTweetList(); //@TODO test for this
		parse();

	}


	/**
	 * This is the constructor for using the Stream Sample API
	 * @param tw 'test word' - the word you want to know the frequency of
	 */
	public TweetParser(String tw){
		SampleStream allTweets = new SampleStream();
		allTweets.beginStream();
		tweets = allTweets.getTweets();
		testWord = tw;
//		parse(); //asynchronicity problems?


	}
	/**
	 * This method splits the long tweet Strings into individual words, separated by spaces
	 */
	public void parse(){
		ArrayList<String> temp = new ArrayList<String>(); //to avoid comod exception
		for(String t : tweets){
			temp.addAll(Arrays.asList(t.split(" ")));
		}
		words = cleanTweets(temp);
//		 wordFreq(testWord);


	}


	/**
	 * Deprecated - Calculates the frequency in which the word appears in the text
	 * @param target the word you're finding the frequency of
	 */
	public void wordFreq(String target){
		double counter = 0.0;
		for (String w : words){
//			System.out.println(w);

			if(w.equals(target)){

				counter++;
			}

		}
		 freq = counter/words.size();

	}
	/**
	 * Accessor method for frequency
	 * @return frequency
	 */
	public double getFreq(){
		return freq;
	}

	/**
	 * Takes in a ArrayList of Strings removes punctuation and links
	 * @return ArrayList of Strings of 'clean' words
	 */ 
	public ArrayList<String> cleanTweets(ArrayList<String> dirtyWords){
		ArrayList<String> cleanWords = new ArrayList<String>(); //to avoid comod exception
		String tempWord = new String();
		String cleanWord = new String();

		for(String dirtyTweet : dirtyWords){ //doesn't work as is
			tempWord = dirtyTweet.replaceAll("\\W", " ");
			cleanWord = tempWord.replaceAll("http[^ ]+$?", "");
			cleanWords.add(cleanWord);
		}

		return cleanWords;
//		cleanTweet = dirtyTweet.replaceAll("@[^ ]+", "");
//		cleanTweet = dirtyTweet.replaceAll("#[^ ]+$?", "");
//		cleanTweet = dirtyTweet.replaceAll("http[^ ]+$?", "");
//		cleanTweet = dirtyTweet.replaceAll("RT[ ]+", "");
//		cleanTweet = dirtyTweet.replaceAll(":\\)", "");
//		cleanTweet = dirtyTweet.replaceAll(":\\(", "");
//		cleanTweet = dirtyTweet.replaceAll("[^a-zA-Z ]", "");
////		dirtyTweet = dirtyTweet.toLowerCase();
//		cleanTweet = dirtyTweet.trim();
	//	System.out.println(cleanWords);

	}

	/**
	 * Accessor method for the word list
	 * @return ArrayList of strings
	 */
	public ArrayList<String> getWordList(){
		return words;
	}

	/**
	 * Accessor method for the inital array list of tweets
	 * @return
	 */
	public ArrayList<String> getTweetList(){
		return tweets;
	}

}