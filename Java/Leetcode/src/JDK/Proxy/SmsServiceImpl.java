package JDK.Proxy;

public class SmsServiceImpl implements SmsService {

    @Override
    public String send(String message) {
        System.out.println(message);
        return message + " over";
    }
}
