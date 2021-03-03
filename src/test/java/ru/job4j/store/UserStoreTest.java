package ru.job4j.store;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UserStoreTest {

    @Test
    public void addAndFindElementsTest() {
        Store<User> userStore = new UserStore();

        User user1 = new User("USER#001");
        User user2 = new User("USER#002");
        User user3 = new User("USER#003");

        userStore.add(user1);
        userStore.add(user2);
        userStore.add(user3);

        assertThat(userStore.findById("USER#001"), is(user1));
        assertThat(userStore.findById("USER#002"), is(user2));
        assertThat(userStore.findById("USER#003"), is(user3));
    }

    @Test
    public void addDuplicatedIdElementsTest() {
        Store<User> userStore = new UserStore();

        User user1 = new User("USER#001");
        User user2 = new User("USER#001");
        User user3 = new User("USER#003");

        userStore.add(user1);
        userStore.add(user2);
        userStore.add(user3);

        assertThat(userStore.findById("USER#001"), is(user1));
        assertThat(userStore.findById("USER#001"), is(user1));
        assertThat(userStore.findById("USER#003"), is(user3));
    }

    @Test
    public void replaceTest() {
        Store<User> userStore = new UserStore();

        User user1 = new User("USER#001");
        User user2 = new User("USER#001");
        User user3 = new User("USER#003");
        User user4 = new User("USER#004");

        userStore.add(user1);
        userStore.add(user2);
        userStore.add(user3);

        assertThat(userStore.findById("USER#001"), is(user1));
        assertThat(userStore.findById("USER#001"), is(user1));
        assertThat(userStore.findById("USER#003"), is(user3));

        assertThat(userStore.replace("USER#003", user4), is(true));

        assertThat(userStore.findById("USER#004"), is(user4));

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void replaceNotExistedElementTest() {
        Store<User> userStore = new UserStore();

        User user1 = new User("USER#001");
        User user2 = new User("USER#001");
        User user3 = new User("USER#003");

        userStore.add(user1);
        userStore.add(user2);
        userStore.add(user3);

        userStore.findById("USER#004");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeTest() {
        Store<User> userStore = new UserStore();

        User user1 = new User("USER#001");
        User user2 = new User("USER#002");
        User user3 = new User("USER#003");
        User user4 = new User("USER#004");

        userStore.add(user1);
        userStore.add(user2);
        userStore.add(user3);
        userStore.add(user4);

        assertThat(userStore.findById("USER#001"), is(user1));
        assertThat(userStore.findById("USER#002"), is(user2));
        assertThat(userStore.findById("USER#003"), is(user3));
        assertThat(userStore.findById("USER#004"), is(user4));

        assertThat(userStore.delete("USER#003"), is(true));

        userStore.findById("USER#003");
    }

    @Test
    public void removeDuplicateTest() {
        Store<User> userStore = new UserStore();

        User user1 = new User("USER#001");
        User user2 = new User("USER#001");
        User user3 = new User("USER#003");
        User user4 = new User("USER#004");

        userStore.add(user1);
        userStore.add(user2);
        userStore.add(user3);
        userStore.add(user4);

        assertThat(userStore.findById("USER#001"), is(user1));
        assertThat(userStore.findById("USER#001"), is(user1));
        assertThat(userStore.findById("USER#003"), is(user3));
        assertThat(userStore.findById("USER#004"), is(user4));

        assertThat(userStore.delete("USER#001"), is(true));

        assertThat(userStore.findById("USER#001"), is(user2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeNonExistingElementTest() {
        Store<User> userStore = new UserStore();

        User user1 = new User("USER#001");
        User user2 = new User("USER#001");
        User user3 = new User("USER#003");
        User user4 = new User("USER#004");

        userStore.add(user1);
        userStore.add(user2);
        userStore.add(user3);
        userStore.add(user4);

        assertThat(userStore.findById("USER#001"), is(user1));
        assertThat(userStore.findById("USER#001"), is(user1));
        assertThat(userStore.findById("USER#003"), is(user3));
        assertThat(userStore.findById("USER#004"), is(user4));

        userStore.delete("USER#005");
    }

}
