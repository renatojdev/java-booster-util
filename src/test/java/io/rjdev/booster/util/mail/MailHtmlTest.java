package io.rjdev.booster.util.mail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MailHtmlTest {

    @BeforeAll
    public static void start() {
        System.out.println("=======Starting junit 5 tests========");
    }

    @Test
    public void executeTest(){
        MailHtml mailHtml = new MailHtml();
        mailHtml.setMailHost("sandbox.smtp.mailtrap.io");
        mailHtml.setUserName("becf5686f4c0e6");
        mailHtml.setPassword("ddf9577a460b79");
        mailHtml.setTo("renatojr@outlook.com");
        mailHtml.setFrom("renatojr@paulaeduardo.com");
        mailHtml.setSubject("Teste");
        mailHtml.setBody("<p><strong>TESTE</strong></p>");
        try {
            mailHtml.sendHtml();
        }catch(Exception e){
            e.printStackTrace(System.out);
        }
    }

}
