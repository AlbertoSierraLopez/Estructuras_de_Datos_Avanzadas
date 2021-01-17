package usecase.practica5;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import material.ordereddictionary.AVLOrderedDict;
import material.ordereddictionary.Entry;
import org.json.JSONArray;
import org.json.JSONObject;

public class TweetAnalysis {

	private AVLOrderedDict<Integer, Tweet> tweetDict;	// AVL porque las consultas son más rápidas y las inserciones y borrados más lentos, y sólo hago inserciones en el constructor, por lo que lo importante son las consultas
	private int MIN_SCORE = (int) Double.POSITIVE_INFINITY;
	private int MAX_SCORE = (int) Double.NEGATIVE_INFINITY;

	public TweetAnalysis(String path) {
		tweetDict = new AVLOrderedDict<>();

		File directory = new File(path);
		File[] listOfFiles = directory.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			try {
				addFile(listOfFiles[i].getPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Adds a new set of tweets to the tree from the given file
	 */
	public void addFile(String tweetFile) throws IOException {
		// Pasar el archivo a String
		BufferedReader bf = new BufferedReader(new FileReader(tweetFile));
		String line = null;
		String json = "";
		while ((line = bf.readLine()) != null) {
			json += line;
		}
		bf.close();
		// Transformar el String a JSON
		JSONObject obj = new JSONObject(json);
		// Sacar los tweets del JSON
		JSONArray tweets = obj.getJSONArray("statuses");

		for (int i = 0; i < tweets.length(); i++) {
			JSONObject t = tweets.getJSONObject(i);

			// Sacar la información relevante sobre el tweet
			String text = t.get("text").toString();
			String username =  t.getJSONObject("user").get("name").toString();
			int retweets = Integer.valueOf(t.get("retweet_count").toString());
			int favorites = Integer.valueOf(t.get("favorite_count").toString());
			int score = retweets + favorites;

			// Insertar el tweet en el diccionario
			Tweet tweet = new Tweet(username, text, retweets, favorites);
			tweetDict.insert(score, tweet);

			// Actualizar MIN_SCORE y MAX_SCORE
			if (score < MIN_SCORE) MIN_SCORE = score;
			if (score > MAX_SCORE) MAX_SCORE = score;
		}
	}
	
	/**
	 * Recovers all the tweets with score larger or equal than min and smaller or equal than max
	 */
	public Iterable<Tweet> findTweets(int min, int max) {
		Iterable<Entry<Integer, Tweet>> range = tweetDict.findRange(min, max);
		List<Tweet> list = new ArrayList<>();
		for (Entry<Integer, Tweet> entry : range) {
			list.add(entry.getValue());
		}
		return list;
	}
	
	/**
	 * Recovers all the tweets with score smaller or equal than percent*MAX_SCORE
	 */
	public Iterable<Tweet> worstTweets(double percent) {
		return findTweets(MIN_SCORE , (int) (percent*MAX_SCORE));
	}
	
	/**
	 * Recovers all the tweets with score larger or equal than percent*MAX_SCORE
	 */
	public Iterable<Tweet> bestTweets(double percent) {
		return findTweets((int) (percent*MAX_SCORE), MAX_SCORE);
	}

}
