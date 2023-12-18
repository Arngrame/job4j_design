package ru.job4j.chapter01.store;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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

        assertThat(userStore.findById("USER#001")).isEqualTo(user1);
        assertThat(userStore.findById("USER#002")).isEqualTo(user2);
        assertThat(userStore.findById("USER#003")).isEqualTo(user3);
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

        assertThat(userStore.findById("USER#001")).isEqualTo(user1);
        assertThat(userStore.findById("USER#001")).isEqualTo(user1);
        assertThat(userStore.findById("USER#003")).isEqualTo(user3);
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

        assertThat(userStore.findById("USER#001")).isEqualTo(user1);
        assertThat(userStore.findById("USER#001")).isEqualTo(user1);
        assertThat(userStore.findById("USER#003")).isEqualTo(user3);

        assertThat(userStore.replace("USER#003", user4)).isTrue();

        assertThat(userStore.findById("USER#004")).isEqualTo(user4);
    }

    @Test
    public void replaceNotExistedElementTest() {
        Store<User> userStore = new UserStore();

        User user1 = new User("USER#001");
        User user2 = new User("USER#001");
        User user3 = new User("USER#003");

        userStore.add(user1);
        userStore.add(user2);
        userStore.add(user3);

        assertThat(userStore.findById("USER#004") == null).isTrue();
    }

    @Test
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

        assertThat(userStore.findById("USER#001")).isEqualTo(user1);
        assertThat(userStore.findById("USER#002")).isEqualTo(user2);
        assertThat(userStore.findById("USER#003")).isEqualTo(user3);
        assertThat(userStore.findById("USER#004")).isEqualTo(user4);

        assertThat(userStore.delete("USER#003")).isTrue();

        assertThat(userStore.findById("USER#003") == null).isTrue();
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

        assertThat(userStore.findById("USER#001")).isEqualTo(user1);
        assertThat(userStore.findById("USER#001")).isEqualTo(user1);
        assertThat(userStore.findById("USER#003")).isEqualTo(user3);
        assertThat(userStore.findById("USER#004")).isEqualTo(user4);

        assertThat(userStore.delete("USER#001")).isTrue();

        assertThat(userStore.findById("USER#001")).isEqualTo(user2);
    }

    @Test
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

        assertThat(userStore.findById("USER#001")).isEqualTo(user1);
        assertThat(userStore.findById("USER#001")).isEqualTo(user1);
        assertThat(userStore.findById("USER#003")).isEqualTo(user3);
        assertThat(userStore.findById("USER#004")).isEqualTo(user4);

        assertThat(userStore.delete("USER#005")).isFalse();
    }

}
