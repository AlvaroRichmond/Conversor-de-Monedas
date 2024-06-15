import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIrequest {
    public Monedas buscaMonedas(String denominacion) throws IOException, InterruptedException {
        String url = "https://v6.exchangerate-api.com/v6/46d52c9cea0f0e599797079e/latest/" + denominacion;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();

        Gson gson = new Gson();
        return gson.fromJson(responseBody, Monedas.class);
    }
}
