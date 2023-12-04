package ru.job4j.chapter01;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.chapter01.Trigger;

public class TriggerTest {

    @Test
    public void test() {
        Assert.assertEquals(1, new Trigger().someLogic());
    }
}
