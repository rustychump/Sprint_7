package cards;

public class ResponseLoginCourier {
    private int id;
    private int code;
    private String message;

    public ResponseLoginCourier(int id) {
        this.id = id;
    }

    public ResponseLoginCourier(int code, String message) {
        this.code = code;
        this.message = message;
    }

//    public ResponseLoginCourier() {
//    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
