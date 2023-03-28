package API;

import Requests.PaymentRequest;
import Requests.VoidRequest;
import Response.PaymentResponse;
import Response.VoidResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static Authenticator.Authenticator.getBasicAuthenticationHeader;

public class APIService {
    public static String SERVER_URL;

    public static PaymentResponse paymentTransaction(PaymentRequest paymentRequest) {
        HttpClient client = HttpClient.newBuilder().build();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(paymentRequest.toJSON()))
                    .uri(URI.create(SERVER_URL))
                    .header("Authorization", getBasicAuthenticationHeader())
                    .header("content-type", "application/json;charset=UTF-8")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return PaymentResponse.buildFromResponse(response);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static VoidResponse voidTransaction(VoidRequest voidRequest) {
        HttpClient client = HttpClient.newBuilder().build();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(voidRequest.toJSON()))
                    .uri(URI.create(SERVER_URL))
                    .header("Authorization", getBasicAuthenticationHeader())
                    .header("content-type", "application/json;charset=UTF-8")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return VoidResponse.buildFromResponse(response);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}

