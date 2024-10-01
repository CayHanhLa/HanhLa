/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Anh Tuan
 */
public class Employee extends User {

    private int roleId;

    public Employee() {
    }

    public Employee(int roleId) {
        this.roleId = roleId;
    }

    public Employee(int userId, String fullName, String email, String password, String gender, String phoneNumber, String address, String avata, int roleId, String status) {
        super(userId, fullName, email, password, gender, phoneNumber, address, avata, status);
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

}
