package usecase.practica5;

public class Pruebas {

    public static void main(String[] args) {
        TweetAnalysis analysis = new TweetAnalysis("usecase/practica5/tweets");

        for (Tweet t : analysis.bestTweets(0.3)) {
            System.out.println(t.toString());
        }
    }

}
