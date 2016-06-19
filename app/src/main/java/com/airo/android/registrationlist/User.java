package com.airo.android.registrationlist;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Airo on 19.06.2016.
 */
public class User {
    private UUID mId;
    private String mFullname;
    private Date mBirth;
    private String mUsername;
    private String mPassword;
    private String mEmail;

    public User() {
// Генерирование уникального идентификатора
        this(UUID.randomUUID());

    }

    public User(UUID id) {
        mId = id;
        mBirth = new Date();
    }

    public UUID getId() {
        return mId;
    }


    public String getFullname() {
        return mFullname;
    }

    public void setFullname(String fullname) {
        mFullname = fullname;
    }

    public Date getBirth() {
        return mBirth;
    }

    public void setBirth(Date birth) {
        mBirth = birth;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }
}
