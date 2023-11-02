package JDK.Proxy;

public class Main {
    public static void main(String[] args) {
        SmsService smsService = (SmsService) JDKProxyFactory.getProxy(new SmsServiceImpl());
        smsService.send("java");
    }
}
