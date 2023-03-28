package Requests;

import Utils.Utils;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PaymentRequest {

    private final String cardNumber;
    private final String cvv;
    private final String expirationDate;
    private final String amount;
    private final String usage;
    private final String transactionType;
    private final String cardHolder;
    private final String email;
    private final String address;

    public PaymentRequest(String cardNumber, String cvv, String expirationDate, String amount, String usage,
                          String transactionType, String cardHolder, String email, String address) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
        this.amount = amount;
        this.usage = usage;
        this.transactionType = transactionType;
        this.cardHolder = cardHolder;
        this.email = email;
        this.address = address;
    }

    public PaymentRequest() {
        this.cardNumber = null;
        this.cvv = null;
        this.expirationDate = null;
        this.amount = null;
        this.usage = null;
        this.transactionType = null;
        this.cardHolder = null;
        this.email = null;
        this.address = null;
    }

    public static PaymentRequest fromFile(String filename) {
        try {
            File file = new File(Utils.getResoursePath() + filename);
            Scanner in = new Scanner(file);
            in.useDelimiter("\\Z");
            String data = in.next();
            Gson gson = new Gson();
            return gson.fromJson(data, PaymentRequest.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String toJSON() {
        return "{\n" +
                Utils.encase("payment_transaction") + ":" +
                    "{" + '\n' +
                    Utils.encase("card_number") + ": " + Utils.encase(cardNumber) + ",\n" +
                    Utils.encase("cvv") + ": " + Utils.encase(cvv) + ",\n" +
                    Utils.encase("expiration_date") + ": " + Utils.encase(expirationDate) + ",\n" +
                    Utils.encase("amount") + ": " + Utils.encase(amount) + ",\n" +
                    Utils.encase("usage") + ": " + Utils.encase(usage) + ",\n" +
                    Utils.encase("transaction_type") + ": " + Utils.encase(transactionType) + ",\n" +
                    Utils.encase("card_holder") + ": " + Utils.encase(cardHolder) + ",\n" +
                    Utils.encase("email") + ": " + Utils.encase(email) + ",\n" +
                    Utils.encase("address") + ": " + Utils.encase(address) + "\n" +
                    "}" + "\n"+
                "}";
    }
}
