package lk.ijse.gdse.aad67.posbackendspring.util;

import java.util.UUID;

public class AppUtil {
    public static String generateCustomerId() {
        return "CUS-" + UUID.randomUUID();
    }

    public static String generateItemId() {
        return "ITEM-" + UUID.randomUUID();
    }
}
