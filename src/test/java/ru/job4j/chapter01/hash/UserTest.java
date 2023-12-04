package ru.job4j.chapter01.hash;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserTest {

    @Test
    public void test() {
        Calendar birthday = new GregorianCalendar(1990, Calendar.NOVEMBER, 3);
        User employee1 = new User("Mike", 3, birthday);
        User employee2 = new User("Mike", 3, birthday);

        Map<User, Object> employees = new HashMap<>();
        employees.put(employee1, new Object());
        employees.put(employee2, new Object());

        System.out.println("First employee hashCode() = " + employee1.hashCode());
        System.out.println("Second employee hashCode() = " + employee2.hashCode());

        int firstEmpHash = ((employee1.hashCode()) ^ (employee1.hashCode() >>> 16));
        int secondEmpHash = ((employee2.hashCode()) ^ (employee2.hashCode() >>> 16));
        System.out.println("First employee Map#hash() = " + firstEmpHash);
        System.out.println("Second employee Map#hash() = " + secondEmpHash);

        System.out.println("First employee index of bucket = " + firstEmpHash % 16);
        System.out.println("Second employee index of bucket = " + secondEmpHash % 16);

        System.out.println(employee1.equals(employee2));

        for (Map.Entry<User, Object> entry : employees.entrySet()) {
            System.out.println("Key:" + entry.getKey());
            System.out.println("Value:" + entry.getValue());
        }
    }

}
