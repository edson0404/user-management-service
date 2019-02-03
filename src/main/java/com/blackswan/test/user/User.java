package com.blackswan.test.user;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;

    protected User() {
    }

    public User(String userName, String firstName, String lastName) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public UserId getId() {
        return new UserId(id);
    }

//    public long getId() {
//        return id;
//    }


    @Override
    public String toString() {
        return String.format(
                "User[id=%d, userName='%s', firstName='%s', lastName='%s']",
                id, userName, firstName, lastName);
    }

    //static to be instantiated outside of the enclosing class.
    //Embeddable defined to state the inner class UserId is dependent on User
    @Embeddable
    public static class UserId implements Serializable {

        private final Long userId;

        UserId() {
            this.userId = null;
        }

        public UserId(Long userId) {
            this.userId = userId;
        }

        public Long getUserId() {
            return userId;
        }
    }


}
