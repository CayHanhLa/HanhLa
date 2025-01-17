/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author
 */
public class Feedback {

    private int feedbackID;
    private int productID;
    private int userID;
    private String fullName;
    private String email;
    private String phoneNumber;
    private int rating;
    private String feedbackText;
    private String imageURL;
    private Date feedbackDate;
    
    private String userName;
    private String avatar;

    public Feedback() {
    }

    public Feedback(int feedbackID,  int userID, String fullName, String email, String phoneNumber, int rating, String feedbackText, String imageURL, Date feedbackDate) {
        this.feedbackID = feedbackID;
        this.productID = productID;
        this.userID = userID;

        this.feedbackDate = feedbackDate;
    }

    public Feedback(int productID, int userID, int rating, String feedbackText, String imageURL) {
        this.productID = productID;
        this.userID = userID;
        this.rating = rating;
        this.feedbackText = feedbackText;
        this.imageURL = imageURL;
    }

    public Feedback(int feedbackID, int productID, int userID, String neNumber, int rating, String feedbackText, String imageURL, Date feedbackDate, String userName, String avatar) {
        this.feedbackID = feedbackID;
        this.productID = productID;
        this.userID = userID;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        
    }
    
    public int getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }

    public int getProductID() {
        return productID;
    }

        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Date getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
