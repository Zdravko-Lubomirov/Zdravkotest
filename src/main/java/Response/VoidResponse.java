package Response;

import com.google.gson.Gson;

import java.net.http.HttpResponse;

public class VoidResponse {

    private int httpCode;
    private String unique_id;
    private String status;
    private String usage;
    private String amount;
    private String transaction_time;
    private String message;

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public String getUnique_id() {
        return unique_id;
    }

    public void setUnique_id(String unique_id) {
        this.unique_id = unique_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTransaction_time() {
        return transaction_time;
    }

    public void setTransaction_time(String transaction_time) {
        this.transaction_time = transaction_time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static VoidResponse buildFromResponse(HttpResponse<String> response) {
        VoidResponse voidResponse = new VoidResponse();
        if (response.statusCode() != 200) {
            voidResponse.setHttpCode(response.statusCode());
            voidResponse.setMessage(response.body());
            return voidResponse;
        } else {
            Gson gson = new Gson();
            voidResponse = gson.fromJson(response.body(), VoidResponse.class);
            voidResponse.setHttpCode(response.statusCode());
            return voidResponse;
        }
    }

    @Override
    public String toString() {
        String str = "VoidResponse{" +
                "httpCode=" + httpCode;
        if (unique_id != null) str += ", unique_id='" + unique_id + '\'';
        if (status != null) str += ", status='" + status + '\'';
        if (usage != null) str += ", usage='" + usage + '\'';
        if (amount != null) str += ", amount='" + amount + '\'';
        if (transaction_time != null) str += ", transaction_time='" + transaction_time + '\'';
        if (message != null) str += ", message='" + message + '\'';
        return str + '}';
    }
}
