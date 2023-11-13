public class Email {

    String mailFrom;
    String rcptTo;
    String data;

    Email(String mailFrom, String rcptTo, String data){
        this.mailFrom = mailFrom;
        this.rcptTo = rcptTo;
        this.data = data;
    }
}
