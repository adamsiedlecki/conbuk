package pl.adamsiedlecki.conbuk.apiCalls;

import pl.adamsiedlecki.conbuk.config.Config;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class CelebrateAddConcept extends Thread {
    @Override
    public void run() {
        super.run();
        HttpURLConnection con = null;
        try {
            URL url = new URL(Config.CELEBRATE_API_ADDRESS);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(700);
            int responseCode = con.getResponseCode();
            System.out.println("CELEBRATE RESPONSE CODE: " + responseCode);
            con.disconnect();

        } catch (IOException e) {
            System.out.println("There is a problem with addConcept celebration: " + e.toString());
        }
    }
}
