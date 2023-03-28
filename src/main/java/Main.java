import API.APIService;
import Requests.*;
import Utils.Utils;

public class Main {
    public static void main(String[] args) {
        Utils.loadConfig("configOK");
        System.out.println(APIService.paymentTransaction(new PaymentRequest("4200000000000000", "321", "06/2022", "500",
                "Coffeemaker", "sale", "Panda Panda", "panda@example.com", "Panda Street, China")));
        System.out.println(APIService.voidTransaction(new VoidRequest("3617999cbbc616de5c6abbfa5ea05219", "void")));

    }
}

