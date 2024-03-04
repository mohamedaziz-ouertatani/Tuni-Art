package tn.esprit.services;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
public class sendsmsService {
  public static final String ACCOUNT_SID = "AC4567d620edb107dd9542e49b4f27c2c9";
  public static final String AUTH_TOKEN = "5609e98430789a80eb5539712dc7c98f";

  public static void SendSms(String numero, String s) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    Message message = Message
                          .creator(new PhoneNumber(numero),
                                   new PhoneNumber("+19253784368"), s)
                          .create();
  }
}
