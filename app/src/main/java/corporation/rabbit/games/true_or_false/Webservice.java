package corporation.rabbit.games.true_or_false;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;


public class Webservice {

    public static String readUrl(String url, ArrayList<NameValuePair> params) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost method = new HttpPost(url);

            if (params != null) {
                method.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            }

            HttpResponse response = client.execute(method);

            InputStream inputStream = response.getEntity().getContent();
            String result = convertInputStreamToString(inputStream);

            return result;
        }
        catch (ClientProtocolException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    private static String convertInputStreamToString(InputStream inputStream) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();

            String line = "";

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            return builder.toString();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
