import API.APIService;
import Requests.PaymentRequest;
import Response.PaymentResponse;
import org.junit.jupiter.api.Test;

import static Utils.Utils.loadConfig;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentTestSuite {
    @Test
    public void testPaymentOK(){
        loadConfig("configOK");
        PaymentRequest request = PaymentRequest.fromFile("PaymentOK");
        PaymentResponse response = APIService.paymentTransaction(request);
        assertEquals(200,response.getHttpCode());
    }
    @Test
    public void testPaymentAuthenticationError(){
        loadConfig("configBadLogin");
        PaymentRequest request = PaymentRequest.fromFile("PaymentOK");
        PaymentResponse response = APIService.paymentTransaction(request);
        assertEquals(401,response.getHttpCode());
    }

    @Test
    public void testPaymentBadRequest(){
        loadConfig("configOK");
        PaymentRequest request = PaymentRequest.fromFile("PaymentBad");
        PaymentResponse response = APIService.paymentTransaction(request);
        assertEquals(422,response.getHttpCode());
    }
    
}
