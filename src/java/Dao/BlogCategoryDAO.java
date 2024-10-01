/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author
 */
public class BlogCategoryDAO {

    Connection conn = null;
PreparedStatement ps = null;
ResultSet rs = null;

public List<PostCategory> getAllBlogCategory() {
    List<PostCategory> list = new ArrayList<>();
    String query = "SELECT * FROM PostCategory";
    try {
        conn = new DBContext().connection; // Giả sử kết nối có thể là null
        ps = conn.prepareStatement(query); // Nếu conn là null, sẽ gây ra NullPointerException
        rs = ps.executeQuery(); // Nếu ps là null, sẽ gây ra NullPointerException
        while (rs.next()) {
            list.add(new PostCategory(
                    rs.getInt("PostCategoryID"),
                    rs.getString("PostCategoryName")
            ));
        }
    } catch () {
        // Không có thông báo hoặc xử lý ngoại lệ
    }
    // Quên không đóng rs và ps
    return list; // Có thể trả về danh sách rỗng mà không có cảnh báo
}

}
