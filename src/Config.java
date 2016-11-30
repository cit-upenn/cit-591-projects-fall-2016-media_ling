import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j //where are my streams?

/*
 * Created with code from Twitter4J
 * 
 */

/**
 * This class does the initial configuration to create Twitter and TwitterFactory objects
 * @author DannyR
 *
 *
 *
 */
public class Config {
	
	/*
	 * Creates new ConfigurationBuilder and sets the OAuth keys\
	 * 
	 * Returns: ConfigurationBuilder object
	 */
	private static ConfigurationBuilder configure() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		
		cb.setDebugEnabled(true).
			setOAuthConsumerKey("P7fXKwdRrdRopdrSIj8CRD81r").
			setOAuthConsumerSecret("uWzK0YajfVmCyDiefDLvJWTM7IlMZ7yIZItJe0yIZ0r15AeXGc").
			setOAuthAccessToken("803065202317070336-IDiD6lqmsZNc2YxIqWygmNXKGoWsrMs").
			setOAuthAccessTokenSecret("JAwKBXalrPQQ6hSuZLZCkv5xFjX3wgGchkCB485mFqfDD");
		
		return cb;
	}
	
	/**
	 * uses the configuration builder to create a TwitterFactory object
	 * @return a twitter factory object
	 */
	private static TwitterFactory createFactory() {
		ConfigurationBuilder cb = configure();
		TwitterFactory tf = new TwitterFactory(cb.build());
		
		return tf;
	}
	

	

	/*
	 * Creates a new TwitterStreamFactory object to be used to create a new TwitterStream
	 * object and connect the config settings to the Streaming API code.
	 */
	private static TwitterStreamFactory createStreamFactory() {
		ConfigurationBuilder cb = configure();
		TwitterStreamFactory tsf = new TwitterStreamFactory(cb.build());
		
		return tsf;
	}

	/*
	 * Creates new TwitterStream object to connect the configuration and OAuth information
	 * to the Streaming API classes.
	 */
	public static TwitterStream createStream() {
		TwitterStreamFactory tsf = createStreamFactory();
		TwitterStream twitterStream = tsf.getInstance();
		
		return twitterStream;
	}

	
	
	/**
	 * This method uses the TwitterFactory object to create a twitter object
	 * @return a twitter object
	 */
	public static Twitter createTwitterObject() {
		TwitterFactory tf = createFactory();
		Twitter twitter = tf.getInstance();
		
		return twitter;
	}
	
	
}