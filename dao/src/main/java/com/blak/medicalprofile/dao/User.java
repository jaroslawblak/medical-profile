package com.blak.medicalprofile.dao;

import java.time.LocalDate;
import java.util.Objects;

public abstract class User {
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String pesel;
    private String userKey;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public User firstName(final String firstName) {
        this.firstName = firstName;
        return this;
    }

    public User lastName(final String lastName) {
        this.lastName = lastName;
        return this;
    }

    public User birthday(final LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    public User id(final int id) {
        this.id = id;
        return this;
    }


    public User pesel(final String pesel) {
        this.pesel = pesel;
        return this;
    }

    public User userKey(final String userKey) {
        this.userKey = userKey;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(birthday, user.birthday) &&
                Objects.equals(pesel, user.pesel) &&
                Objects.equals(userKey, user.userKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, birthday, pesel);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", pesel='" + pesel + '\'' +
                ", userKey='" + userKey + '\'' +
                '}';
    }
}
