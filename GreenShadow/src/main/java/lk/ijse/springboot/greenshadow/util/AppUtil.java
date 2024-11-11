package lk.ijse.springboot.greenshadow.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

public class AppUtil {
    public static String generateStaffId() {
        return "S-" + UUID.randomUUID();
    }
    public static String generateFieldId() {
        return "F-" + UUID.randomUUID();
    }
    public static String generateCropCode(){
        return "C-" + UUID.randomUUID();
    }
    public static String generateVehicleCode(){
        return "V-" + UUID.randomUUID();
    }
    public static String generateEquipmentId(){
        return "E-" + UUID.randomUUID();
    }
    public static String toBase64Pic(MultipartFile image) {
        try{
            byte[] picBytes = image.getBytes();
            return Base64.getEncoder().encodeToString(picBytes);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
