package lk.ijse.springboot.greenshadow.exception;

public class DataPersistFailedException extends RuntimeException {
    public DataPersistFailedException() {
    }
    public DataPersistFailedException(String message) {
        super(message);
    }
    public DataPersistFailedException(String message, Throwable cause){
        super(message, cause);
    }
}
