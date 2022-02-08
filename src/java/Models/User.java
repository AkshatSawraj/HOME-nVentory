/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author 835489
 */
public class User {

    private String email;
    private boolean isActive;
    private String firstName;
    private String lastName;
    private String password;
    private String role;
    private int companyID;

    public User() {
    }

    public User(String email, boolean isActive, String firstName, String lastName, String password, String role, int companyID) {
        this.email = email;
        this.isActive = isActive;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
        this.companyID = companyID;
    }

    public User(String email, boolean isActive, String firstName, String lastName, String password, String role) {
        this.email = email;
        this.isActive = isActive;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return isActive;
    }

    public void isActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + "email=" + email + ", isActive=" + isActive + ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password + ", role=" + role + '}';
    }

}
