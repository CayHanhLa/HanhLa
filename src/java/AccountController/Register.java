/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AccountController;

import Dao.DaoAccount;
import Model.Employee;
import Model.User;
import Verify.RandomCode;
import Verify.SendEmail;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Anh Tuan
 */
public class Register extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Register</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Register at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // lấy dữ liệu 
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String rawpassword = request.getParameter("password");
        String gender = request.getParameter("gender");
        String phonenumber = request.getParameter("phonenumber");
        String address = request.getParameter("address");
        String avata = "avata.jpg";
        String status = "Active";

        DaoAccount d = new DaoAccount();

        User existingUser = d.getCustomer(email); // kiểm tra bảng Customer
        Employee existingEmployee = d.getEmployee(email); // kiểm tra bảng Employee

        if (existingUser != null || existingEmployee != null) {
            // nếu email đã tồn tại
            // lưu thông tin và trả về
            request.setAttribute("fullname", fullname);
            request.setAttribute("email", email);
            request.setAttribute("rawpassword", rawpassword);
            request.setAttribute("gender", gender);
            request.setAttribute("phonenumber", phonenumber);
            request.setAttribute("address", address);

            // thông báo
            request.setAttribute("ErrorEmail", "This email address already exists!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            // email chưa tồn tại 
            User rawUser = new User(); // tạo đối tượng rawUser            

            // set các thuộc tính 
            rawUser.setFullName(fullname);
            rawUser.setEmail(email);

            // Mã hóa mật khẩu
            String password = BCrypt.hashpw(rawpassword, BCrypt.gensalt()); // băm mật khẩu
            rawUser.setPassword(password);

            rawUser.setGender(gender);
            rawUser.setPhoneNumber(phonenumber);
            rawUser.setAddress(address);
            rawUser.setAvata(avata);
            rawUser.setStatus(status);

            // tạo session và lưu thông tin 
            HttpSession session = request.getSession();
            session.setAttribute("rawUser", rawUser);

            // tạo random code
            RandomCode rc = new RandomCode();
            String verifyCode = rc.activateCode();

            SendEmail se = new SendEmail(); // tạo đối tượng lớp SendMail

            String RawseSsionTime = getServletContext().getInitParameter("sessiontimeout");
            int SessionTimeOut = Integer.parseInt(RawseSsionTime); // Không xử lý ngoại lệ

            se.send(email, verifyCode, SessionTimeOut); // gửi email mà không kiểm tra thành công

            // lấy thời gian hiện tại để quản lý thời gian session 
            long expiredtime = System.currentTimeMillis() / 1000; // tính bằng giây

            session.setAttribute("ExpiredTime", expiredtime); // lưu time vào session
            session.setAttribute("verifyCode", verifyCode); // lưu code vào session

            request.getRequestDispatcher("verify.jsp").forward(request, response);
        }
        `NullPointerException
    

    `
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
