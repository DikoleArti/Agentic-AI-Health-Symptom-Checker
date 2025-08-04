import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpClientTest {
    public static void main(String[] args) throws IOException {

        // 🔹 1. Set your IBM Cloud API key
        String API_KEY = "<YOUR_IBM_CLOUD_API_KEY>";

        // 🔹 2. Get IAM Token
        String iamToken = getIamToken(API_KEY);

        // 🔹 3. Send request to your Granite deployment
        sendGraniteRequest(iamToken);
    }

    // ==================== Get IAM Token ====================
    public static String getIamToken(String apiKey) throws IOException {
        URL tokenUrl = new URL("https://iam.cloud.ibm.com/identity/token");
        HttpURLConnection tokenConnection = (HttpURLConnection) tokenUrl.openConnection();
        tokenConnection.setDoOutput(true);
        tokenConnection.setRequestMethod("POST");
        tokenConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        tokenConnection.setRequestProperty("Accept", "application/json");

        String body = "grant_type=urn:ibm:params:oauth:grant-type:apikey&apikey=" + apiKey;
        try (OutputStream os = tokenConnection.getOutputStream()) {
            os.write(body.getBytes(StandardCharsets.UTF_8));
        }

        String response = readResponse(tokenConnection);
        String token = response.split("\"access_token\":\"")[1].split("\"")[0];
        return "Bearer " + token;
    }

    // ==================== Send Granite Request ====================
    public static void sendGraniteRequest(String iamToken) throws IOException {
        // Replace with your own deployment URL from IBM WML
        String deploymentUrl = "https://us-south.ml.cloud.ibm.com/ml/v4/deployments/<YOUR_DEPLOYMENT_ID>/ai_service_stream?version=2021-05-01";

        URL scoringUrl = new URL(deploymentUrl);
        HttpURLConnection scoringConnection = (HttpURLConnection) scoringUrl.openConnection();
        scoringConnection.setDoOutput(true);
        scoringConnection.setRequestMethod("POST");
        scoringConnection.setRequestProperty("Accept", "application/json");
        scoringConnection.setRequestProperty("Authorization", iamToken);
        scoringConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

        // Example symptom checker prompt
        String payload = "{\"messages\":[{\"role\":\"user\",\"content\":\"I have a headache and fever\"}]}";

        try (OutputStream os = scoringConnection.getOutputStream()) {
            os.write(payload.getBytes(StandardCharsets.UTF_8));
        }

        String response = readResponse(scoringConnection);
        System.out.println("Granite AI Response:\n" + response);
    }

    // ==================== Read API Response ====================
    public static String readResponse(HttpURLConnection connection) throws IOException {
        BufferedReader reader;
        if (connection.getResponseCode() >= 200 && connection.getResponseCode() < 300) {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
        } else {
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream(), StandardCharsets.UTF_8));
        }

        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        return response.toString();
    }
}
