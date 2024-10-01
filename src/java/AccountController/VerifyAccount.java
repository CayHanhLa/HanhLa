/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AccountController;

import Dao.DaoAccount;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Anh Tuan
 */
public class VerifyAccount extends HttpServlet {

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
            out.println("<title>Servlet VerifyAccount</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VerifyAccount at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("verify.jsp").forward(request, response);
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

        HttpSession session = request.getSession();
        long currentTime = System.currentTimeMillis() / 1000; // lay time hien tai (tinh bang giay)

        Long expiredTime = (Long) session.getAttribute("ExpiredTime"); // lay va ep kieu thuoc tinh ExpiredTime

        // lay session time out cau hinh qua the context-param trong web.xml
        String RawSessionTime = getServletContext().getInitParameter("sessiontimeout");
        long sessionTimeout = Long.parseLong(RawSessionTime);

        // kiem tra xem verify code da het han hay chua
        if (expiredTime == null || currentTime - expiredTime > sessionTimeout) {

            session.invalidate(); // het han xoa all session

            // thong bao
            request.setAttribute("FailtoVerify", "Verification code has expired. Please request a new code.");
            request.getRequestDispatcher("verify.jsp").forward(request, response);
        }

        // kiem tra xem code da duoc su dung hay chua
        if (session.getAttribute("verifyCode") == null || session.getAttribute("rawUser") == null) {
            // neu da su dung thi tra ve thong bao
            request.setAttribute("FailtoVerify", "Verification code has expired. Please request a new code.");
            request.getRequestDispatcher("verify.jsp").forward(request, response);
        } else {
            // neu chua
            // lay ma va tao thanh chuoi
            String rawCode = "";
            for (int i = 1; i < 7; i++) {
                rawCode += request.getParameter("number" + i);
            }

            // lay verifycode 
            String verifyCode = (String) session.getAttribute("verifyCode");

            // lay thong tin rawUser
            User customer = (User) session.getAttribute("rawUser");

            // so sanh code
            if (rawCode.equals(verifyCode)) {
                // dung
                DaoAccount d = new DaoAccount();
                String UserType = "Customer"; // tao usertype co dinh

                d.createUserID(UserType, customer.getEmail()); // them vao bang User

                int userid = d.getUserID(customer.getEmail()); // lay UserID cua User vua duoc them qua email

                // lay du lieu rawUser tu session
                User user = (User) request.getSession().getAttribute("rawUser");
                user.setUserId(userid); // set userid

                d.registerCustomer(user); // them User (Customer)
                session.invalidate(); // xoa all session de code verify chi dung duoc 1 lan

                // thong bao
                request.setAttribute("loginFailedMessage", "Account created successfully! You can now log in to access your account.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                // nhap code sai
                request.setAttribute("FailtoVerify", "Verification code is not correct. Please try again");
                request.getRequestDispatcher("verify.jsp").forward(request, response);

            }
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
