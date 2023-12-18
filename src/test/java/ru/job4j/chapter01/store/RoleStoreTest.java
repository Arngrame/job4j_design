package ru.job4j.chapter01.store;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RoleStoreTest {

    @Test
    public void addAndFindElementsTest() {
        Store<Role> roleStore = new RoleStore();

        Role role1 = new Role("ROLE#001");
        Role role2 = new Role("ROLE#002");
        Role role3 = new Role("ROLE#003");

        roleStore.add(role1);
        roleStore.add(role2);
        roleStore.add(role3);

        assertThat(roleStore.findById("ROLE#001")).isEqualTo(role1);
        assertThat(roleStore.findById("ROLE#002")).isEqualTo(role2);
        assertThat(roleStore.findById("ROLE#003")).isEqualTo(role3);
    }

    @Test
    public void addDuplicatedIdElementsTest() {
        Store<Role> roleStore = new RoleStore();

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
        Store<Role> roleStore = new RoleStore();

        Role role1 = new Role("ROLE#001");
        Role role2 = new Role("ROLE#001");
        Role role3 = new Role("ROLE#003");
        Role role4 = new Role("ROLE#004");

        roleStore.add(role1);
        roleStore.add(role2);
        roleStore.add(role3);

        assertThat(roleStore.findById("ROLE#001")).isEqualTo(role1);
        assertThat(roleStore.findById("ROLE#001")).isEqualTo(role1);
        assertThat(roleStore.findById("ROLE#003")).isEqualTo(role3);

        assertThat(roleStore.replace("ROLE#003", role4)).isTrue();

        assertThat(roleStore.findById("ROLE#004")).isEqualTo(role4);

    }

    @Test
    public void replaceNotExistedElementTest() {
        Store<Role> roleStore = new RoleStore();

        Role role1 = new Role("ROLE#001");
        Role role2 = new Role("ROLE#002");
        Role role3 = new Role("ROLE#003");

        roleStore.add(role1);
        roleStore.add(role2);
        roleStore.add(role3);

        assertThat(roleStore.findById("ROLE#004") == null).isTrue();
    }

    @Test
    public void removeTest() {
        Store<Role> roleStore = new RoleStore();

        Role role1 = new Role("ROLE#001");
        Role role2 = new Role("ROLE#002");
        Role role3 = new Role("ROLE#003");
        Role role4 = new Role("ROLE#004");

        roleStore.add(role1);
        roleStore.add(role2);
        roleStore.add(role3);
        roleStore.add(role4);

        assertThat(roleStore.findById("ROLE#001")).isEqualTo(role1);
        assertThat(roleStore.findById("ROLE#002")).isEqualTo(role2);
        assertThat(roleStore.findById("ROLE#003")).isEqualTo(role3);
        assertThat(roleStore.findById("ROLE#004")).isEqualTo(role4);

        assertThat(roleStore.delete("ROLE#003")).isTrue();

        assertThat(roleStore.findById("ROLE#003") == null).isTrue();
    }

    @Test
    public void removeDuplicateTest() {
        Store<Role> roleStore = new RoleStore();

        Role role1 = new Role("ROLE#001");
        Role role2 = new Role("ROLE#001");
        Role role3 = new Role("ROLE#003");
        Role role4 = new Role("ROLE#004");

        roleStore.add(role1);
        roleStore.add(role2);
        roleStore.add(role3);
        roleStore.add(role4);

        assertThat(roleStore.findById("ROLE#001")).isEqualTo(role1);
        assertThat(roleStore.findById("ROLE#001")).isEqualTo(role1);
        assertThat(roleStore.findById("ROLE#003")).isEqualTo(role3);
        assertThat(roleStore.findById("ROLE#004")).isEqualTo(role4);

        assertThat(roleStore.delete("ROLE#001")).isTrue();

        assertThat(roleStore.findById("ROLE#001")).isEqualTo(role2);
    }

    @Test
    public void removeNonExistingElementTest() {
        Store<Role> roleStore = new RoleStore();

        Role role1 = new Role("ROLE#001");
        Role role2 = new Role("ROLE#001");
        Role role3 = new Role("ROLE#003");
        Role role4 = new Role("ROLE#004");

        roleStore.add(role1);
        roleStore.add(role2);
        roleStore.add(role3);
        roleStore.add(role4);

        assertThat(roleStore.findById("ROLE#001")).isEqualTo(role1);
        assertThat(roleStore.findById("ROLE#001")).isEqualTo(role1);
        assertThat(roleStore.findById("ROLE#003")).isEqualTo(role3);
        assertThat(roleStore.findById("ROLE#004")).isEqualTo(role4);

        assertThat(roleStore.delete("ROLE#005")).isFalse();
    }

}
