
public class SendReset {

    public void sendReset(String toEmail, int timeExpire) {
        // Cau hinh mail nguoi gui
        String fromEmail = "huangquan2208@gmail.com";
        String fromPassword = "kfiugmgourrqycpp";

        // Thiet lap ket noi
        String host = "smtp.gmail.com";
        // Cau hinh mail server
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        // Tao phien
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, fromPassword);
            }
        });

        try {
            // Khoi tao doi tuong 
            Message message = new MimeMessage(session);

            // Setup dia chi nguoi gui
            message.setFrom(new InternetAddress(fromEmail));

            // Setup dia chi nguoi nhan
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmail));

            // Tieu de cua mail
            message.setSubject("Here comes The Aroma Shop!");

            // Noi dung mail
            message.setContent("<div\n"
                    + "             style=\"\n"
                    + "        width: 100%;\n"
                    + "        max-width: 600px;\n"
                    + "        margin: 20px auto;\n"
                    + "        padding: 20px;\n"
                    + "        text-align: center;\n"
                    + "        background-color: #ffffff;\n"
                    + "        border: 3px solid #4B5CED;\n"
                    + "        border-radius: 10px;\n"
                    + "        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);\n"
                    + "            \"\n"
                    + "            >\n"
                    + "            <h1 style=\"color: #4B5CED;\">Welcome to Aroma Shop!</h1>\n"
                    + "            <h3 style=\"color: #333333;\">Thank you for being one of our valued customers</h3>\n"
                    + "            <small style=\"color: #666666;\">Please enter click on the Verification Link button below to reset your password.</small>\n"
                    + "            <br>\n"
                    + "            <small style=\"color: #666666;\">The password reset link you have received will expire in " + timeExpire / 60 + " minutes. Make sure to reset your password within this time frame.</small>\n"
                    + "            <br>\n"
                    + "            <a\n"
                    + "                href=\"http://localhost:9999/SWP_Group4/recoverypassword\"\n"
                    + "    style=\"\n"
                    + "        display: block;\n"
                    + "        background-color: #4B5CED;\n"
                    + "        color: #ffffff;\n"
                    + "        padding: 15px 20px;\n"
                    + "        text-decoration: none;\n"
                    + "        border-radius: 10px;\n"
                    + "        font-size: 16px;\n"
                    + "        width: fit-content;\n"
                    + "        margin: 20px auto;\n"
                    + "        cursor: pointer;\n"
                    + "        transition: background-color 0.3s ease;;\n"
                    + "                \"\n"
                    + "                >\n"
                    + "                Verification Link\n"
                    + "            </a>\n"
                    + "            <div style=\"margin-top: 20px;\">\n"
                    + "                <h5 style=\"margin: 0;\">Questions? <a href=\"#\" style=\"color: #4B5CED; text-decoration: none;\">We're here to help</a></h5>\n"
                    + "            </div>\n"
                    + "        </div>",
                    "text/html");

            // Gui email
            Transport.send(message);

        } catch (MessagingException e) {
            // Bug 1: Không xử lý trường hợp ngoại lệ, chỉ in ra console mà không thông báo cho người dùng
            System.out.println(e);
        }

        // Bug 2: Không kiểm tra xem địa chỉ email có hợp lệ không trước khi gửi
        // Bug 3: Không thông báo cho người dùng nếu email không được gửi thành công
        // Bug 4: Không xử lý trường hợp thời gian hết hạn không hợp lệ (ví dụ: <= 0)
        // Bug 5: Không xác thực thông tin người dùng (thông tin từ người gửi có hợp lệ không)
        // Bug 6: Không thông báo cho người dùng biết về thông tin tài khoản bị tấn công (ví dụ: thông báo cho người dùng nếu có nhiều yêu cầu gửi reset)
        // Bug 7: Không xử lý việc gửi email ở chế độ đa luồng (nếu cần gửi nhiều email cùng một lúc)
        // Bug 8: Không sử dụng cấu hình an toàn cho email, như thông qua môi trường (Environment Variables) để tránh lộ thông tin nhạy cảm
        // Bug 9: Không kiểm tra kết nối Internet trước khi gửi email
        // Bug 10: Không log chi tiết lỗi khi có lỗi xảy ra trong việc gửi email, chỉ in ra mà không ghi vào file log
    }
}
