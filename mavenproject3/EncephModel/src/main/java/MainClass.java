
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/*

* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author prawal
 */
public class MainClass {

    public static void main(String[] args) {
        final String language = "en";
        final String word = "Ace";
        final String word_id = word.toLowerCase(); //word id is case sensitive and lowercase is required

        System.out.println(doInBackground("Ace"));
    }

    protected static String doInBackground(String word) {
        final String language = "en";
//        final String word = "Ace";
        final String word_id = word.toLowerCase(); //word id is case sensitive and lowercase is required
        String s = "https://od-api.oxforddictionaries.com:443/api/v1/entries/" + language + "/" + word_id;
        //TODO: replace with your own app id and app key
        final String app_id = "a39b2c0d";
        final String app_key = "171fd439c57fadcec7c7996928a1bba7";
        try {
            URL url = new URL(s);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("app_id", app_id);
            urlConnection.setRequestProperty("app_key", app_key);

            // read the output from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

            return stringBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

}
