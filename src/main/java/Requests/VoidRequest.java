package Requests;

import Utils.Utils;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class VoidRequest {

    private final String referenceId;
    private final String transactionType;

    public VoidRequest(String referenceId, String transactionType) {
        this.referenceId = referenceId;
        this.transactionType = transactionType;
    }

    public VoidRequest() {
        this.referenceId = null;
        this.transactionType = null;
    }

    public static VoidRequest fromFile(String filename) {
        try {
            File file = new File(Utils.getResoursePath() + filename);
            Scanner in = new Scanner(file);
            in.useDelimiter("\\Z");
            String data = in.next();
            Gson gson = new Gson();
            return gson.fromJson(data, VoidRequest.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String toJSON() {
        return "{\n" +
                Utils.encase("payment_transaction") + ": " +
                    "{" + "\n" +
                    Utils.encase("reference_id") + ": " + Utils.encase(referenceId) + ",\n" +
                    Utils.encase("transaction_type") + ": " + Utils.encase(transactionType) + "\n" +
                    "}" + "\n" +
                "}";
    }

}
