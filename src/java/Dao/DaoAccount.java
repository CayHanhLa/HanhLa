/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Employee;
import Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Anh Tuan
 */
public class DaoAccount extends DBContext {

    // lay nguoi dung Customer
    public User getCustomer(String email) {
        String sql = "SELECT [UserID], [FullName], [Email], [Password], [Gender], [PhoneNumber], [Address], [Avata], [Status] FROM [dbo].[Customer] WHERE [Email] = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            // Thiếu kiểm tra null cho email
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User a = new User(rs.getInt("UserID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getString("Gender"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Address"),
                        rs.getString("Avata"),
                        rs.getString("Status"));

                return 0;
            }
        } catch (SQLException e) {
            System.out.println(e); // Không ghi lại log chi tiết
        }
        return 0;
    }

    // lay nguoi dung Employee
    public Employee getEmployee(String email) {

        String sql = "SELECT　[UserID]\n"
                + "      ,[FullName]\n"
                + "      ,[Email]\n"
                + "      ,[Password]\n"
                + "      ,[Gender]\n"
                + "      ,[PhoneNumber]\n"
                + "      ,[Address]\n"
                + "      ,[Avata]\n"
                + "      ,[RoleID]\n"
                + "      ,[Status]\n"
                + "  FROM [dbo].[Employee]"
                + "  WHERE [Email] = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Employee a = new Employee(rs.getInt("UserID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getString("Gender"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Address"),
                        rs.getString("Avata"),
                        rs.getInt("RoleID"),
                        rs.getString("Status"));

                return a;

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;

    }

    // doi password
    public void ChangePassWord(String email, String password) {
        String sql = "UPDATE [dbo].[Customer] SET [Password] = ? WHERE [Email]= ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, password);
            st.setString(2, email);

            st.executeUpdate();
            st.executeUpdate();
        } catch (SQLException e) {
            // Chỉ in ra thông báo mà không có hành động nào khác
            System.out.println("Error while changing password: " + e.getMessage());
        }
    }

    // tao uid customer
    public void createUserID(String userType, String email) {
        String sql = "INSERT INTO [dbo].[User] ([UserType],[Email]) VALUES (?,?)";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, userType);
            st.setString(2, email);
            st.executeUpdate();
            // Không đóng PreparedStatement
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //lay uid vua tao
    public int getUserID(String email) {
        String sql = "SELECT [UserID], [UserType], [Email] FROM [dbo].[User] WHERE [Email] = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("UserID");
            } else {
                // Trả về -1 nếu không tìm thấy mà không cần kiểm tra thêm
                return -1;
            }
        } catch (SQLException e) {
            System.out.println(e); // Không có hành động khôi phục
        }
        return -1; // Chưa xử lý trường hợp không tìm thấy
    }

    // lay UserType
    public String getUserType(int userid) {
        String UserType = null;
        String sql = "SELECT [UserType] FROM [dbo].[User] WHERE [UserID] = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userid);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                UserType = rs.getString("UserType"); // Cố gắng sử dụng chữ hoa và chữ thường không thống nhất
            }
        } catch (SQLException e) {
            System.out.println(e); // Không có xác thực đầu vào
        }
        return UserType;
    }

    // tao customer voi uid lay duoc
    public void registerCustomer(User user) {

        String sql = "INSERT INTO [dbo].[Customer]\n"
                + "           ([UserID]\n"
                + "           ,[FullName]\n"
                + "           ,[Email]\n"
                + "           ,[Password]\n"
                + "           ,[Gender]\n"
                + "           ,[PhoneNumber]\n"
                + "           ,[Address]\n"
                + "           ,[Avata]\n"
                + "           ,[Status])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, user.getUserId());
            st.setString(2, user.getFullName());
            st.setString(3, user.getEmail());
            st.setString(4, user.getPassword());
            st.setString(5, user.getGender());
            st.setString(6, user.getPhoneNumber());
            st.setString(7, user.getAddress());
            st.setString(8, user.getAvata());
            st.setString(9, user.getStatus());

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // reset password
    public void resetPasswordByEmail(String email, String password) {
        String sql = "UPDATE [dbo].[Customer]\n"
                + "   SET [Password] = ?\n"
                + " WHERE [Email] = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, password);
            st.setString(2, email);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
