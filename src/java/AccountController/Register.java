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

        // lay du lieu 
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String rawpassword = request.getParameter("password");
        String gender = request.getParameter("gender");
        String phonenumber = request.getParameter("phonenumber");
        String address = request.getParameter("address");
        String avata = "avata.jpg";
        String status = "Active";

        DaoAccount d = new DaoAccount();

        User existingUser = d.getCustomer(email); // kiem tra bang Customer
        Employee existingEmployee = d.getEmployee(email); // kiem tra bang Employee

        if (existingUser != null || existingEmployee != null) {

            // neu email da ton tai
            // luu thong tin r tra ve
            request.setAttribute("fullname", fullname);
            request.setAttribute("email", email);
            request.setAttribute("rawpassword", rawpassword);
            request.setAttribute("gender", gender);
            request.setAttribute("phonenumber", phonenumber);
            request.setAttribute("address", address);

            // thong bao
            request.setAttribute("ErrorEmail", "This email address already exists!");
            request.getRequestDispatcher("register.jsp").forward(request, response);

        } else {

            // email chau ton tai 
            User rawUser = new User(); // tao doi tuong rawUser            

            // set cac thuoc tinh 
            rawUser.setFullName(fullname);
            rawUser.setEmail(email);

            String password = BCrypt.hashpw(rawpassword, BCrypt.gensalt()); // bam password
            rawUser.setPassword(password);

            rawUser.setGender(gender);
            rawUser.setPhoneNumber(phonenumber);
            rawUser.setAddress(address);
            rawUser.setAvata(avata);
            rawUser.setStatus(status);

            // tao session va luu thong tin 
            HttpSession session = request.getSession();
            session.setAttribute("rawUser", rawUser);

            // tao random code
            RandomCode rc = new RandomCode();
            String verifyCode = rc.activateCode();

            SendEmail se = new SendEmail(); // tao doi tuong lop SendMail

            // lay session time out cau hinh qua the context-param trong web.xml
            String RawseSsionTime = getServletContext().getInitParameter("sessiontimeout");
            int SessionTimeOut = Integer.parseInt(RawseSsionTime); // ep kieu

            se.send(email, verifyCode, SessionTimeOut); // gui mail

            //lay time hien tai de quan ly thoi gian session 
            long expiredtime = System.currentTimeMillis() / 1000; // tinh bang giay

            session.setAttribute("ExpiredTime", expiredtime); // luu time vao session
            session.setAttribute("verifyCode", verifyCode); // luu code vao session

            request.getRequestDispatcher("verify.jsp").forward(request, response);

        }
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
