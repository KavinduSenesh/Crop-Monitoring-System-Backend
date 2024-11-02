package lk.ijse.springboot.greenshadow.util;

import java.util.UUID;

public class AppUtil {
    public static String generateStaffId() {
        return "S-" + UUID.randomUUID();
    }
}
