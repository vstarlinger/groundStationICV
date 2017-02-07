package data;

/**
 *
 * @author valentinstarlinger
 */
public class SerialEvent {
    
    private String msg;
    private String port;
    private long timeStamp;

    public SerialEvent(String msg, String port, long timeStamp){
        this.msg = msg;
        this.port = port;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
    
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
    
    
}
