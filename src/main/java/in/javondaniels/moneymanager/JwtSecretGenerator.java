package in.javondaniels.moneymanager;

import java.util.UUID;

public class JwtSecretGenerator {
    public static void main(String[] args) {
        String secret = UUID.randomUUID().toString();
        System.out.println("Your JWT Secret: " + secret);
    }
}
