/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Constant.Constant;
import Model.Feedback;
import Model.Post;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author
 */
public class FeedbackDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Feedback> getAllFeedbackByProductId(int productId) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Feedback> list = new ArrayList<>();
        String query = "select F.*,\n"
                + "CASE\n"
                + "      WHEN C.UserID = U.UserID THEN C.FullName\n"
                + "      ELSE E.FullName \n"
                + "END AS UserName,\n"
                + "CASE\n"
                + "      WHEN C.UserID = U.UserID THEN C.Avata\n"
                + "      ELSE E.Avata \n"
                + "END AS Avata\n"
                + "from Feedback F\n"
                + "left join [User] U on F.UserID = U.UserID\n"
                + "left join Customer C on C.UserID = U.UserID\n"
                + "left join Employee E on E.UserID = U.UserID "
                + "where F.ProductID = ? "
                + "order by F.FeedbackID desc";
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            ps.setInt(1, productId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Feedback f = new Feedback(rs.getInt("FeedbackID"),
                        rs.getInt("ProductID"),
                        rs.getInt("UserID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("PhoneNumber"),
                        rs.getInt("Rating"),
                        rs.getString("FeedbackText"),
                        rs.getString("ImageURL"),
                        rs.getTimestamp("FeedbackDate"),
                        rs.getString("UserName"),
                        rs.getString("Avata")
                );
                list.add(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void addFeedback(Feedback feedback) {
        String query = "INSERT INTO [Feedback]([ProductID]\n"
                + "      ,[UserID]\n"
                + "      ,[Rating]\n"
                + "      ,[FeedbackText]\n"
                + "      ,[ImageURL])\n"
                + "VALUES (?, ?, ?, ?, ?)";
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            ps.setInt(1, feedback.getProductID());
            ps.setInt(2, feedback.getUserID());
            ps.setInt(3, feedback.getRating());
            ps.setString(4, feedback.getFeedbackText());
            ps.setString(5, feedback.getImageURL());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public double getAvgRating(int productId) {
        double rating = 0;
        String query = "SELECT \n"
                + "    ProductID, \n"
                + "    ROUND(AVG(CAST(Rating AS FLOAT)), 1) AS AverageRating\n"
                + "FROM \n"
                + "    [SWP_Group4_1].[dbo].[Feedback]\n"
                + "where ProductID = ?\n"
                + "GROUP BY \n"
                + "    ProductID;";
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            ps.setInt(1, productId);

            rs = ps.executeQuery();
            while (rs.next()) {
                rating = rs.getDouble("AverageRating");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rating;
    }

    public List<Integer> getNumberOfFBByRating(int productId) {
        List<Integer> list = new ArrayList<>();
        String query = "WITH Ratings AS (\n"
                + "    SELECT 1 AS Rating\n"
                + "    UNION ALL\n"
                + "    SELECT 2\n"
                + "    UNION ALL\n"
                + "    SELECT 3\n"
                + "    UNION ALL\n"
                + "    SELECT 4\n"
                + "    UNION ALL\n"
                + "    SELECT 5\n"
                + ")\n"
                + "SELECT \n"
                + "    r.Rating, \n"
                + "    COUNT(f.Rating) AS FeedbackCount\n"
                + "FROM \n"
                + "    Ratings r\n"
                + "LEFT JOIN \n"
                + "    Feedback f\n"
                + "ON \n"
                + "    r.Rating = f.Rating and f.ProductID = ?\n"
                + "GROUP BY \n"
                + "    r.Rating\n"
                + "ORDER BY \n"
                + "    r.Rating DESC;";
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            ps.setInt(1, productId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return list;
    }

    public boolean isBought(int userId, int productId) {
        String query = "  select * from OrderDetails OD\n"
                + "  left join Orders O on O.OrderID = OD.OrderID\n"
                + "  where O.UserID = ? and OD.ProductID = ?";
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setInt(2, productId);

            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
