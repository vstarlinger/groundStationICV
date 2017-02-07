package data;

import javax.mail.Address;

/**
 *
 * @author valentinstarlinger
 */
public class MailEvent {
    
    private Address[] from;
    private String subject, filename, text;
    private IridiumPackage iridiumPackage;
    private long timeStampGmail;
    private long timeStamp;

    public MailEvent(Address[] from, String subject, String filename, String text, long timeStamp, long timeStampGmail) {
        this.from = from;
        this.subject = subject;
        this.filename = filename;
        this.text = text;
        this.iridiumPackage = new IridiumPackage(text);
        this.timeStampGmail = timeStampGmail;
        this.timeStamp = timeStamp;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public Address[] getFrom() {
        return from;
    }

    public void setFrom(Address[] from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFilename() {
        return filename;
    }

    public long getTimeStampGmail() {
        return timeStampGmail;
    }

    public void setTimeStampGmail(long timeStampGmail) {
        this.timeStampGmail = timeStampGmail;
    }
    
    public void setFilename(String filename) {
        this.filename = filename;
    }

    public IridiumPackage getIridiumPackage() {
        return iridiumPackage;
    }

    public void setIridiumPackage(IridiumPackage iridiumPackage) {
        this.iridiumPackage = iridiumPackage;
    }
    
    public String getText(){
        return text;
    }

    @Override
    public String toString(){
        return timeStamp+"\n"+from.toString()+"\n"+subject+"\n"+filename+"\n"+text.toString()+"\n"+"\n";
    }
    
    
}
