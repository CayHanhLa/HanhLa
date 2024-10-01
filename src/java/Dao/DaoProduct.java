/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Products;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sontu
 */
public class DaoProduct {

    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Connection connection = null;

    public List<Products> getAllProducts() {
        List<Products> list = new ArrayList<>();
        String query = "SELECT \n"
                + "    p.ProductID,\n"
                + "    p.Title,\n"
                + "    p.BriefInfo,\n"
                + "    b.BrandName,\n"
                + "    p.Description,\n"
                + "    p.Thumbnail,\n"
                + "    p.OriginalPrice,\n"
                + "    p.Status,\n"
                + "    s.SizeName,\n"
                + "    pd.StockQuantity\n"
                + "FROM \n"
                + "    Product p\n"
                + "JOIN \n"
                + "    Brand b ON p.BrandID = b.BrandID\n"
                + "JOIN \n"
                + "    ProductDetails pd ON p.ProductID = pd.ProductID\n"
                + "JOIN \n"
                + "    Size s ON pd.SizeID = s.SizeID;";
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String title = rs.getString(2);
                String briefInfor = rs.getString(3);
                String brand = rs.getString(4);
                String description = rs.getString(5);
                String thumbnail = rs.getString(6);
                double price = rs.getDouble(7);
                String status = rs.getString(8);
                String size = rs.getString(9);
                int stock = rs.getInt(10);
                Products p = new Products(id, title, briefInfor, brand, description, thumbnail, price, status, size, stock);
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Products> get3Products() {
        List<Products> list = new ArrayList<>();
        String query = "SELECT TOP 3\n"
                + "    p.ProductID,\n"
                + "    p.Title,\n"
                + "    p.BriefInfo,\n"
                + "    b.BrandName,\n"
                + "    p.Description,\n"
                + "    p.Thumbnail,\n"
                + "    p.OriginalPrice,\n"
                + "    p.Status,\n"
                + "    s.SizeName,\n"
                + "    pd.StockQuantity\n"
                + "FROM [dbo].[Product] p\n"
                + "JOIN [dbo].[Brand] b ON p.BrandID = b.BrandID\n"
                + "JOIN [dbo].[ProductDetails] pd ON p.ProductID = pd.ProductID\n"
                + "JOIN [dbo].[Size] s ON pd.SizeID = s.SizeID\n"
                + "ORDER BY p.OriginalPrice DESC;";
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String title = rs.getString(2);
                String briefInfor = rs.getString(3);
                String brand = rs.getString(4);
                String description = rs.getString(5);
                String thumbnail = rs.getString(6);
                double price = rs.getDouble(7);
                String status = rs.getString(8);
                String size = rs.getString(9);
                int stock = rs.getInt(10);
                Products p = new Products(id, title, briefInfor, brand, description, thumbnail, price, status, size, stock);
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Products> get8ProductsTrending() {
        List<Products> list = new ArrayList<>();
        String query = "SELECT TOP 8\n"
                + "    p.ProductID,\n"
                + "    p.Title,\n"
                + "    p.BriefInfo,\n"
                + "    b.BrandName,\n"
                + "    p.Description,\n"
                + "    p.Thumbnail,\n"
                + "    p.OriginalPrice,\n"
                + "    p.Status,\n"
                + "    s.SizeName,\n"
                + "    pd.StockQuantity\n"
                + "FROM [dbo].[Product] p\n"
                + "JOIN [dbo].[Brand] b ON p.BrandID = b.BrandID\n"
                + "JOIN [dbo].[ProductDetails] pd ON p.ProductID = pd.ProductID\n"
                + "JOIN [dbo].[Size] s ON pd.SizeID = s.SizeID\n"
                + "WHERE p.Status = 'Trending'\n"
                + "ORDER BY p.UpdatedDate DESC;";
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String title = rs.getString(2);
                String briefInfor = rs.getString(3);
                String brand = rs.getString(4);
                String description = rs.getString(5);
                String thumbnail = rs.getString(6);
                double price = rs.getDouble(7);
                String status = rs.getString(8);
                String size = rs.getString(9);
                int stock = rs.getInt(10);
                Products p = new Products(id, title, briefInfor, brand, description, thumbnail, price, status, size, stock);
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Products> get4ProductsNewest() {
        List<Products> list = new ArrayList<>();
        String query = "SELECT TOP 4\n"
                + "    p.ProductID,\n"
                + "    p.Title,\n"
                + "    p.BriefInfo,\n"
                + "    b.BrandName,\n"
                + "    p.Description,\n"
                + "    p.Thumbnail,\n"
                + "    p.OriginalPrice,\n"
                + "    p.Status,\n"
                + "    s.SizeName,\n"
                + "    pd.StockQuantity\n"
                + "FROM [dbo].[Product] p\n"
                + "JOIN [dbo].[Brand] b ON p.BrandID = b.BrandID\n"
                + "JOIN [dbo].[ProductDetails] pd ON p.ProductID = pd.ProductID\n"
                + "JOIN [dbo].[Size] s ON pd.SizeID = s.SizeID\n"
                + "ORDER BY p.UpdatedDate DESC;";
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String title = rs.getString(2);
                String briefInfor = rs.getString(3);
                String brand = rs.getString(4);
                String description = rs.getString(5);
                String thumbnail = rs.getString(6);
                double price = rs.getDouble(7);
                String status = rs.getString(8);
                String size = rs.getString(9);
                int stock = rs.getInt(10);
                Products p = new Products(id, title, briefInfor, brand, description, thumbnail, price, status, size, stock);
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Products getProductByID(int pid) {
        Products p = null; // Khởi tạo đối tượng sản phẩm là null
        String query = "SELECT\n"
                + "    p.ProductID AS id,\n"
                + "    p.Title AS title,\n"
                + "    p.BriefInfo AS briefInfo,\n"
                + "    b.BrandName AS brandName,\n"
                + "    p.Description AS description,\n"
                + "    p.Thumbnail AS thumbnail,\n"
                + "    p.OriginalPrice AS price,\n"
                + "    p.Status AS status,\n"
                + "    s.SizeName AS size,\n"
                + "    COALESCE(pd.StockQuantity, 0) AS stockQuantity\n"
                + "FROM [dbo].[Product] p\n"
                + "JOIN [dbo].[Brand] b ON p.BrandID = b.BrandID\n"
                + "JOIN [dbo].[ProductDetails] pd ON p.ProductID = pd.ProductID\n"
                + "JOIN [dbo].[Size] s ON pd.SizeID = s.SizeID\n"
                + "WHERE p.ProductID = ?"; // Sử dụng tham số pid

        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(query);
            ps.setInt(1, pid); // Truyền giá trị pid vào câu truy vấn

            rs = ps.executeQuery();

            if (rs.next()) {
                String id = rs.getString(1);
                String title = rs.getString(2);
                String briefInfor = rs.getString(3);
                String brand = rs.getString(4);
                String description = rs.getString(5);
                String thumbnail = rs.getString(6);
                double price = rs.getDouble(7);
                String status = rs.getString(8);
                String size = rs.getString(9);
                int stock = rs.getInt(10);
                // Gán các giá trị vào đối tượng sản phẩm
                p = new Products(id, title, briefInfor, brand, description, thumbnail, price, status, size, stock);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p; // Trả về đối tượng sản phẩm (null nếu không tìm thấy)
    }

    public List<Products> get3ProductsTopSeller() {
        List<Products> list = new ArrayList<>();
        String query = "SELECT TOP 3\n"
                + "    p.ProductID AS id,\n"
                + "    p.Title AS title,\n"
                + "    p.BriefInfo AS briefInfo,\n"
                + "    b.BrandName AS brandName,\n"
                + "    p.Description AS description,\n"
                + "    p.Thumbnail AS thumbnail,\n"
                + "    p.OriginalPrice AS price,\n"
                + "    p.Status AS status,\n"
                + "    s.SizeName AS size,\n"
                + "    COALESCE(pd.StockQuantity, 0) AS stockQuantity\n"
                + "FROM [dbo].[Product] p\n"
                + "JOIN [dbo].[Brand] b ON p.BrandID = b.BrandID\n"
                + "JOIN [dbo].[ProductDetails] pd ON p.ProductID = pd.ProductID\n"
                + "JOIN [dbo].[Size] s ON pd.SizeID = s.SizeID\n"
                + "ORDER BY COALESCE(pd.SoldQuantity, 0) DESC;";
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String title = rs.getString(2);
                String briefInfor = rs.getString(3);
                String brand = rs.getString(4);
                String description = rs.getString(5);
                String thumbnail = rs.getString(6);
                double price = rs.getDouble(7);
                String status = rs.getString(8);
                String size = rs.getString(9);
                int stock = rs.getInt(10);
                Products p = new Products(id, title, briefInfor, brand, description, thumbnail, price, status, size, stock);
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Products> get3ProductsTopPrice() {
        List<Products> list = new ArrayList<>();
        String query = "SELECT TOP 3\n"
                + "    p.ProductID AS id,\n"
                + "    p.Title AS title,\n"
                + "    p.BriefInfo AS briefInfo,\n"
                + "    b.BrandName AS brandName,\n"
                + "    p.Description AS description,\n"
                + "    p.Thumbnail AS thumbnail,\n"
                + "    p.OriginalPrice AS price,\n"
                + "    p.Status AS status,\n"
                + "    s.SizeName AS size,\n"
                + "    COALESCE(pd.StockQuantity, 0) AS stockQuantity\n"
                + "FROM [dbo].[Product] p\n"
                + "JOIN [dbo].[Brand] b ON p.BrandID = b.BrandID\n"
                + "JOIN [dbo].[ProductDetails] pd ON p.ProductID = pd.ProductID\n"
                + "JOIN [dbo].[Size] s ON pd.SizeID = s.SizeID\n"
                + "ORDER BY COALESCE(pd.OriginalPrice, 0) DESC;";
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String title = rs.getString(2);
                String briefInfor = rs.getString(3);
                String brand = rs.getString(4);
                String description = rs.getString(5);
                String thumbnail = rs.getString(6);
                double price = rs.getDouble(7);
                String status = rs.getString(8);
                String size = rs.getString(9);
                int stock = rs.getInt(10);
                Products p = new Products(id, title, briefInfor, brand, description, thumbnail, price, status, size, stock);
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Products> get3ProductsTopTrending() {
        List<Products> list = new ArrayList<>();
        String query = "SELECT TOP 3\n"
                + "    p.ProductID AS id,\n"
                + "    p.Title AS title,\n"
                + "    p.BriefInfo AS briefInfo,\n"
                + "    b.BrandName AS brandName,\n"
                + "    p.Description AS description,\n"
                + "    p.Thumbnail AS thumbnail,\n"
                + "    p.OriginalPrice AS price,\n"
                + "    p.Status AS status,\n"
                + "    s.SizeName AS size,\n"
                + "    COALESCE(pd.StockQuantity, 0) AS stockQuantity\n"
                + "FROM [dbo].[Product] p\n"
                + "JOIN [dbo].[Brand] b ON p.BrandID = b.BrandID\n"
                + "JOIN [dbo].[ProductDetails] pd ON p.ProductID = pd.ProductID\n"
                + "JOIN [dbo].[Size] s ON pd.SizeID = s.SizeID\n"
                + "WHERE p.Status = 'Trending'\n"
                + "ORDER BY p.OriginalPrice DESC;";
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String title = rs.getString(2);
                String briefInfor = rs.getString(3);
                String brand = rs.getString(4);
                String description = rs.getString(5);
                String thumbnail = rs.getString(6);
                double price = rs.getDouble(7);
                String status = rs.getString(8);
                String size = rs.getString(9);
                int stock = rs.getInt(10);
                Products p = new Products(id, title, briefInfor, brand, description, thumbnail, price, status, size, stock);
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Products> get3ProductsTopSale() {
        List<Products> list = new ArrayList<>();
        String query = "SELECT TOP 3\n"
                + "    p.ProductID AS id,\n"
                + "    p.Title AS title,\n"
                + "    p.BriefInfo AS briefInfo,\n"
                + "    b.BrandName AS brandName,\n"
                + "    p.Description AS description,\n"
                + "    p.Thumbnail AS thumbnail,\n"
                + "    p.OriginalPrice AS price,\n"
                + "    p.Status AS status,\n"
                + "    s.SizeName AS size,\n"
                + "    COALESCE(pd.StockQuantity, 0) AS stockQuantity\n"
                + "FROM [dbo].[Product] p\n"
                + "JOIN [dbo].[Brand] b ON p.BrandID = b.BrandID\n"
                + "JOIN [dbo].[ProductDetails] pd ON p.ProductID = pd.ProductID\n"
                + "JOIN [dbo].[Size] s ON pd.SizeID = s.SizeID\n"
                + "WHERE p.Status = 'Sale'\n"
                + "ORDER BY p.OriginalPrice DESC;";
        try {
            connection = new DBContext().connection;
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String title = rs.getString(2);
                String briefInfor = rs.getString(3);
                String brand = rs.getString(4);
                String description = rs.getString(5);
                String thumbnail = rs.getString(6);
                double price = rs.getDouble(7);
                String status = rs.getString(8);
                String size = rs.getString(9);
                int stock = rs.getInt(10);
                Products p = new Products(id, title, briefInfor, brand, description, thumbnail, price, status, size, stock);
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        DaoProduct dao = new DaoProduct();
        List<Products> list = dao.get3ProductsTopTrending();
        for (Products p : list) {
            System.out.println(p.getStockQuanlity());
        }
    }
}
