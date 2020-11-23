package pl.adamsiedlecki.conbuk.tools;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.adamsiedlecki.conbuk.pojo.Stat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

public class GetCurrentLinesOfCodeOnGithubRepo {

    public static List<Stat> get() {

        try {
            URL url = new URL("https://api.codetabs.com/v1/loc?github=adamsiedlecky/conbuk");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();

            ObjectMapper mapper = new ObjectMapper();
            List<Stat> stats = mapper.readValue(content.toString(), new TypeReference<List<Stat>>() {
            });
            System.out.println(stats);
            return stats;


        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return List.of();
    }
}
