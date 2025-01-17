package Verify;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class SendEmail {

    public void send(String toEmail, String verifyCode, int SessinTimeOut) {
        //cau hinh mail nguoi gui
        String fromEmail ;
        String fromPassword ;

        //thiet lap ket noi

        //cau hinh mail server
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        //tao phien
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, fromPassword);
            }
        });

        try {
            //khoi tao doi tuong 
            Message message = new MimeMessage(session);

            //setup dia chi nguoi gui
            message.setFrom(new InternetAddress(fromEmail));

            //setup dia chi nguoi nhan
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmail));

            //tieu de cua mail
            message.setSubject("Here comes The Aroma Shop!");

            //noi dung mail
            message.setContent("<div\n"
                    + "            style=\"\n"
                    + "            width: 50vw;\n"
                    + "            max-width: 600px;\n"
                    + "            margin: 20px auto;\n"
                    + "            padding: 20px;\n"
                    + "            text-align: center;\n"
                    + "            background-color: #ffffff;\n"
                    + "            border: 3px solid #4B5CED;\n"
                    + "            border-radius: 10px;\n"
                    + "            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);\n"
                    + "            \"\n"
                    + "            >\n"
                    + "            <h1 style=\"color: #4B5CED;\">Welcome to Aroma Shop!</h1>\n"
                    + "            <h3 style=\"color: #333333;\">Thank you for being one of our valued customers</h3>\n"
                    + "            <small style=\"color: #666666;\">Please enter this code in the verification link to confirm your identity.</small>\n"
                    + "            <div\n"
                    + "                style=\"\n"
                    + "                background-color: #FFFFFF;\n"
                    + "                border-radius: 10px;\n"
                    + "                padding: 20px;\n"
                    + "                width: 50%;\n"
                    + "                margin: 20px auto;\n"
                    + "                font-size: 24px;\n"
                    + "                font-weight: bold;\n"
                    + "                letter-spacing: 5px;\n"
                    + "                color: #333333;\n"
                    + "                \"\n"
                    + "                >\n"
                    + "                <h1 style=\"margin: 0;\">" + verifyCode + "</h1>\n"
                    + "            </div>\n"
                    + "            <small style=\"color: #666666;\">Your verification code is valid for the next " + SessinTimeOut/60 + " minutes. Please use it before it expires.</small>\n"
                    + "            <br>\n"
                    + "            <small style=\"color: #666666;\">If you didn't request a code, you can safely ignore this email.</small>\n"
                    + "            <br>\n"
                    + "            <a\n"
                    + "                href=\"http://localhost:9999/SWP_Group4/verify\"\n"
                    // chinh URL tuy theo dia chi cong minh su dung
                    + "                style=\"\n"
                    + "                display: inline-block;\n"
                    + "                background-color: #4B5CED;\n"
                    + "                color: #ffffff;\n"
                    + "                padding: 15px 20px;\n"
                    + "                text-decoration: none;\n"
                    + "                border-radius: 10px;\n"
                    + "                font-size: 16px;\n"
                    + "                margin: 20px auto;\n"
                    + "                cursor: pointer;\n"
                    + "                transition: background-color 0.3s ease;\n"
                    + "                \"\n"
                    + "                >\n"
                    + "                Verification Link\n"
                    + "            </a>\n"
                    + "            <div style=\"margin-top: 20px;\">\n"
                    + "                <h5 style=\"margin: 0;\">Questions? <a href=\"#\" style=\"color: #4B5CED; text-decoration: none;\">We're here to help</a></h5>\n"
                    + "            </div>\n"
                    + "        </div>",
                    +);

            //gui email
            Transport.send(message);

        } catch (MessagingException e) {
            System.out.println(e);
        }
    }
}
