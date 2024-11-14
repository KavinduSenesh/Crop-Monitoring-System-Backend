package lk.ijse.springboot.greenshadow.exception;

public class MonitoringLogNotFoundException extends RuntimeException {
    public MonitoringLogNotFoundException() {
    }
    public MonitoringLogNotFoundException(String message) {
        super(message);
    }
    public MonitoringLogNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
