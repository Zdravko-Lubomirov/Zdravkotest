import API.APIService;
import Requests.PaymentRequest;
import Requests.VoidRequest;
import Response.PaymentResponse;
import Response.VoidResponse;
import org.junit.jupiter.api.Test;

import static Utils.Utils.loadConfig;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VoidTestSuite {

    @Test
    public void testVoidOk() {
        loadConfig("configOK");
        PaymentRequest paymentRequest = PaymentRequest.fromFile("PaymentOK");
        PaymentResponse paymentResponse = APIService.paymentTransaction(paymentRequest);
        VoidRequest request = new VoidRequest(paymentResponse.getUnique_id(),"void");
        VoidResponse response = APIService.voidTransaction(request);
        assertEquals(200,response.getHttpCode());
    }

    @Test
    public void testVoidAuthenticationError() {
        loadConfig("configBadLogin");
        PaymentRequest paymentRequest = PaymentRequest.fromFile("PaymentOK");
        PaymentResponse paymentResponse = APIService.paymentTransaction(paymentRequest);
        VoidRequest request = new VoidRequest(paymentResponse.getUnique_id(),"void");
        VoidResponse response = APIService.voidTransaction(request);
        assertEquals(401,response.getHttpCode());
    }

    @Test
    public void testVoidNonExistentPayment() {
        loadConfig("configOK");
        VoidRequest request = new VoidRequest("0","void");
        VoidResponse response = APIService.voidTransaction(request);
        assertEquals(422,response.getHttpCode());
    }

    @Test
    public void testVoidAlreadyVoided() {
        loadConfig("configOK");
        PaymentRequest paymentRequest = PaymentRequest.fromFile("PaymentOK");
        PaymentResponse paymentResponse = APIService.paymentTransaction(paymentRequest);
        VoidRequest request = new VoidRequest(paymentResponse.getUnique_id(),"void");
        APIService.voidTransaction(request);
        VoidResponse response = APIService.voidTransaction(request);
        assertEquals(422,response.getHttpCode());
    }

}
