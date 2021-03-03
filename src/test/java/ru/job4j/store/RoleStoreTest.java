package ru.job4j.store;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

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

        assertThat(roleStore.findById("ROLE#001"), is(role1));
        assertThat(roleStore.findById("ROLE#002"), is(role2));
        assertThat(roleStore.findById("ROLE#003"), is(role3));
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

        assertThat(roleStore.findById("ROLE#001"), is(role1));
        assertThat(roleStore.findById("ROLE#001"), is(role1));
        assertThat(roleStore.findById("ROLE#003"), is(role3));
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

        assertThat(roleStore.findById("ROLE#001"), is(role1));
        assertThat(roleStore.findById("ROLE#001"), is(role1));
        assertThat(roleStore.findById("ROLE#003"), is(role3));

        assertThat(roleStore.replace("ROLE#003", role4), is(true));

        assertThat(roleStore.findById("ROLE#004"), is(role4));

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void replaceNotExistedElementTest() {
        Store<Role> roleStore = new RoleStore();

        Role role1 = new Role("ROLE#001");
        Role role2 = new Role("ROLE#001");
        Role role3 = new Role("ROLE#003");

        roleStore.add(role1);
        roleStore.add(role2);
        roleStore.add(role3);

        roleStore.findById("ROLE#004");
    }

    @Test(expected = IndexOutOfBoundsException.class)
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

        assertThat(roleStore.findById("ROLE#001"), is(role1));
        assertThat(roleStore.findById("ROLE#002"), is(role2));
        assertThat(roleStore.findById("ROLE#003"), is(role3));
        assertThat(roleStore.findById("ROLE#004"), is(role4));

        assertThat(roleStore.delete("ROLE#003"), is(true));

        roleStore.findById("ROLE#003");
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

        assertThat(roleStore.findById("ROLE#001"), is(role1));
        assertThat(roleStore.findById("ROLE#001"), is(role1));
        assertThat(roleStore.findById("ROLE#003"), is(role3));
        assertThat(roleStore.findById("ROLE#004"), is(role4));

        assertThat(roleStore.delete("ROLE#001"), is(true));

        assertThat(roleStore.findById("ROLE#001"), is(role2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
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

        assertThat(roleStore.findById("ROLE#001"), is(role1));
        assertThat(roleStore.findById("ROLE#001"), is(role1));
        assertThat(roleStore.findById("ROLE#003"), is(role3));
        assertThat(roleStore.findById("ROLE#004"), is(role4));

        roleStore.delete("ROLE#005");
    }

}
