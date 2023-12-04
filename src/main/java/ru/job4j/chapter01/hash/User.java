package ru.job4j.chapter01.hash;

import java.util.Calendar;

public class User {

    private String name;
    private int children;
    private Calendar birthday;

    public User() {
    }

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "name='" + name + ", children=" + children + ", birthday=" + birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && name.equals(user.name) && birthday.equals(user.birthday);
    }

    @Override
    public int hashCode() {
        int defaultHash = 31;
        defaultHash = defaultHash * 17 + (name == null ? 0 : name.hashCode());
        defaultHash = defaultHash * 17 + children;
        defaultHash = defaultHash * 17 + (birthday == null ? 0 : birthday.hashCode());
        return defaultHash;
    }
}
