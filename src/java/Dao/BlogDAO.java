/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Constant.Constant;
import Model.Post;


/**
 *
 * @author
 */
public class BlogDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Post> getAllBlogs(String txtSearch, int category, Integer page) {
        List<Post> list = new ArrayList<>();
        String query = "SELECT P.*, \n"
                + "       PC.PostCategoryName,\n"
                + "       CASE \n"
                + "           WHEN C.FullName IS NOT NULL THEN C.FullName \n"
                + "           ELSE E.FullName \n"
                + "       END AS FullName\n"
                + "FROM Post P\n"
                + "LEFT JOIN PostCategory PC ON P.PostCategoryID = PC.PostCategoryID\n"
                + "LEFT JOIN [User] U ON U.UserID = P.UserID\n"
                + "LEFT JOIN Customer C ON U.UserID = C.UserID\n"
                + "LEFT JOIN Employee E ON E.UserID = U.UserID\n"
                + "WHERE (? = '' OR P.Title LIKE '%' + ? + '%')\n"
                + "  AND (? = 0 OR PC.PostCategoryID = ?)\n"
                + "ORDER BY P.PostID desc\n";
        if (page != null) {
            query += "OFFSET ? ROWS\n"
                    + "FETCH NEXT ? ROWS ONLY;";
        }

                        rs.getString("FullName"),
                        rs.getString("PostCategoryName")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<Post> getTop3LastestBlogs() {
        List<Post> list = new ArrayList<>();
        String query = "SELECT top 3 P.*, \n"
                + "       PC.PostCategoryName,\n"
                + "       CASE \n"
                + "           WHEN C.FullName IS NOT NULL THEN C.FullName \n"
                + "           ELSE E.FullName \n"
                + "       END AS FullName\n"
                + "FROM Post P\n"
                + "LEFT JOIN PostCategory PC ON P.PostCategoryID = PC.PostCategoryID\n"ID"),
                        rs.getInt("UserID"),
                        rs.getString("Title"),
                        rs.getString("PostImg"),
                        rs.getString("PostContent"),
                        rs.getDate("PostDate"),
                        rs.getString("PostBrief"),
                        rs.getBoolean("PostFlag"),
                        rs.getInt("PostCategoryID"),
                        rs.getString("FullName"),
                        rs.getString("PostCategoryName")
                ));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Post getBlogById(int id) {
        String query = "SELECT P.*, \n"
                + "       PC.PostCategoryName,\n"
                + "       CASE \n"
                + "           WHEN C.FullName IS NOT NULL THEN C.FullName \n"
                + "           ELSE E.FullName \n"
                + "       END AS FullName\n"
                + "FROM Post P\n"
                + "LEFT JOIN PostCategory PC ON P.PostCategoryID = PC.PostCategoryID\n"
                + "LEFT JOIN [User] U ON U.UserID = P.UserID\n"
                + "LEFT JOIN Customer C ON U.UserID = C.UserID\n"
                + "LEFT JOIN Employee E ON E.UserID = U.UserID where P.PostID = ?";
        try {
            conn = new DBContext().connection;
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Post(rs.getInt("PostID"),
                        rs.getInt("UserID"),
                        rs.getString("Title"),
                        rs.getString("PostImg"),
                        rs.getString("PostContent"),
                        rs.getDate("PostDate"),
                        rs.getString("PostBrief"),
                        rs.getBoolean("PostFlag"),
                        rs.getInt("PostCategoryID"),
                        rs.getString("FullName"),
                        rs.getString("PostCategoryName")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
