package usecase.practica5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class TweetAnalysis {
	
	/**
	 * Adds a new set of tweets to the tree from the given file
	 */
	public void addFile(String tweetFile) {
		throw new RuntimeException("Not yet implemented");
	}
	
	/**
	 * Recovers all the tweets with score larger or equal than min and smaller or equal than max
	 */
	public Iterable<Tweet> findTweets(int min, int max) {
		throw new RuntimeException("Not yet implemented");
	}
	
	/**
	 * Recovers all the tweets with score smaller or equal than percent*MAX_SCORE
	 */
	public Iterable<Tweet> worstTweets(double percent) {
		throw new RuntimeException("Not yet implemented");
	}
	
	/**
	 * Recovers all the tweets with score larger or equal than percent*MAX_SCORE
	 */
	public Iterable<Tweet> bestTweets(double percent) {
		throw new RuntimeException("Not yet implemented");
	}

}
