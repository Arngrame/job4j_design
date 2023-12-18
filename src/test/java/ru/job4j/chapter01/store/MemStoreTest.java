package ru.job4j.chapter01.store;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MemStoreTest {

    @Test
    public void addAndFindElementsTest() {
        MemStore<User> userStore = new MemStore<>();

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
        MemStore<Role> roleStore = new MemStore<>();

        Role role1 = new Role("ROLE#001");
        Role role2 = new Role("ROLE#001");
        Role role3 = new Role("ROLE#003");

        roleStore.add(role1);
        roleStore.add(role2);
        roleStore.add(role3);

        assertThat(roleStore.findById("ROLE#001")).isEqualTo(role1);
        assertThat(roleStore.findById("ROLE#001")).isEqualTo(role1);
        assertThat(roleStore.findById("ROLE#003")).isEqualTo(role3);
    }

    @Test
    public void replaceTest() {
        MemStore<User> userStore = new MemStore<>();

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
        MemStore<Role> roleStoreStore = new MemStore<>();

        Role role1 = new Role("ROLE#001");
        Role role2 = new Role("ROLE#001");
        Role role3 = new Role("ROLE#003");

        roleStoreStore.add(role1);
        roleStoreStore.add(role2);
        roleStoreStore.add(role3);

        assertThat(roleStoreStore.findById("ROLE#004") == null).isTrue();
    }

    @Test
    public void removeTest() {
        MemStore<User> userStore = new MemStore<>();

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
        MemStore<User> userStore = new MemStore<>();

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
        MemStore<User> userStore = new MemStore<>();

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
