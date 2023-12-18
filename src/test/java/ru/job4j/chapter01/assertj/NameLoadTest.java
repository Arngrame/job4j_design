package ru.job4j.chapter01.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {

    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkParseOnEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("array is empty");
    }

    @Test
    void checkParseAndGetWorkCorrectly() {
        NameLoad nameLoad = new NameLoad();

        String[] colors = {"color1=red", "color2=green", "color3=blue"};
        nameLoad.parse(colors);
        nameLoad.getMap();
    }

    @Test
    void checkParseSecondItemHasNoSeparator() {
        NameLoad nameLoad = new NameLoad();
        String[] colors = {"color1=red", "color2green", "color3=blue"};

        assertThatThrownBy(() -> nameLoad.parse(colors))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("this name: %s does not contain the symbol '='", "color2green"));
    }

    @Test
    void checkParseFirstItemHasNoKey() {
        NameLoad nameLoad = new NameLoad();
        String[] colors = {"=red", "color2=green", "color3=blue"};

        assertThatThrownBy(() -> nameLoad.parse(colors))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("this name: %s does not contain a key", "=red"));
    }

    @Test
    void checkParseThirdItemHasNoValue() {
        NameLoad nameLoad = new NameLoad();

        String[] colors = {"color1=red", "color2=green", "color3="};

        assertThatThrownBy(() -> nameLoad.parse(colors))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: %s does not contain a value".formatted("color3="));
    }
}