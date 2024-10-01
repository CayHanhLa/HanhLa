/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author sontu
 */
public class Products {
    private String id;
    private String title;
    private String briefInfor;
    private String brandName;
    private String description;
    private String thumbnail;
    private double price;
    private String status;
    private String size;
    private int stockQuanlity;

    public Products(String id, String title, String briefInfor, String brandName, String description, String thumbnail, double price, String status, String size, int stockQuanlity) {
        this.id = id;
        this.title = title;
        this.briefInfor = briefInfor;
        this.brandName = brandName;
        this.description = description;
        this.thumbnail = thumbnail;
        this.price = price;
        this.status = status;
        this.size = size;
        this.stockQuanlity = stockQuanlity;
    }

    public Products() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBriefInfor() {
        return briefInfor;
    }

    public void setBriefInfor(String briefInfor) {
        this.briefInfor = briefInfor;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getStockQuanlity() {
        return stockQuanlity;
    }

    public void setStockQuanlity(int stockQuanlity) {
        this.stockQuanlity = stockQuanlity;
    }
    
}
