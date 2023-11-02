package JDK.Proxy;

public class SmsProxy implements SmsService {

    private final SmsService smsService;

    public SmsProxy(SmsService smsService) {
        this.smsService = smsService;
    }


    @Override
    public String send(String message) {
        System.out.println("before send...");
        String result = smsService.send(message);
        System.out.println("after send..., result = " + result);
        return result;
    }
}
