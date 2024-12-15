package utils;

import java.time.LocalDate;
import java.util.Random;

public class IdGenerator {
	public static String generateUniqueId(String symbol) {
        int currentYear = LocalDate.now().getYear();
        String yearPrefix = String.valueOf(currentYear).substring(2);
        Random random = new Random();
        int randomNumber = 100000 + random.nextInt(900000);
        return yearPrefix + symbol + randomNumber;
    }

}
