package Hash;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;

public class HashingFunction {
    public static String hash(String stringToHash) {
        byte[] inputBytes = stringToHash.getBytes();
        String hashValue = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(inputBytes);
            byte[] digestedBytes = messageDigest.digest();
            hashValue = DatatypeConverter.printHexBinary(digestedBytes).toLowerCase();
        } catch (Exception e) {
        }
        return hashValue;

    }
}