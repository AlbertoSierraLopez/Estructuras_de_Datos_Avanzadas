package usecase.practica5;

public class Tweet {

	private String screenname; // Sreenname that posted the tweet
	private String text; // Content of the tweet
	private int retweets; // Number of retweets of this tweet
	private int favorite; // Number of times that this tweet has been marked as favorite
	
	public Tweet(String user, String text, int retweets, int favorite) {
		super();
		this.screenname = user;
		this.text = text;
		this.retweets = retweets;
		this.favorite = favorite;
	}
	
	public String getScreenname() {
		return screenname;
	}
	public String getText() {
		return text;
	}
	public int getRetweets() {
		return retweets;
	}
	public int getFavorite() {
		return favorite;
	}
	
	
}
