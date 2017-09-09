import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

public final class IFTTT {

    public static String IFTTT_KEY = "";
    
    public static void triggerEvent(String name, String... text) {

	System.out.println("Sending event to IFTTT...");
	String s = "https://maker.ifttt.com/trigger/" + name + "/with/key/" + IFTTT_KEY;
	try {
	    Map<String, String> json = new LinkedHashMap<>();
	    json.put("value1", text.length > 0 ? text[0] : "");
	    json.put("value2", text.length > 1 ? text[1] : "");
	    json.put("value3", text.length > 2 ? text[2] : "");
	    IFTTT.httpRequest(new URL(s), json);
	    System.out.println("Event sent");
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    public static String httpRequest(URL url, Map<String, String> json) throws IOException {

	HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	connection.setConnectTimeout(5000);
	connection.setReadTimeout(5000);

	connection.setRequestMethod("POST");
	connection.setDoOutput(true);
	connection.setRequestProperty("Content-Type", "application/json");

	if (json != null) {
	    OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
	    StringBuffer sb = new StringBuffer();
	    sb.append("{ ");
	    int i = 0;
	    for (String key : json.keySet()) {
		i++;
		sb.append("\"" + key + "\" : \"" + json.get(key) + "\"");
		if (i != json.size())
		    sb.append(", ");
	    }
	    sb.append(" }");
	    out.write(sb.toString());
	    out.flush();
	    out.close();
	}

	InputStream is = connection.getInputStream();
	BufferedReader br = new BufferedReader(new InputStreamReader(is));
	String line = null;
	while ((line = br.readLine()) != null) {}
	connection.disconnect();

	return line;

    }

}
