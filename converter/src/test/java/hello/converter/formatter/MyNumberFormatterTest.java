package hello.converter.formatter;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Hunseong on 2022/04/04
 */
class MyNumberFormatterTest {

    MyNumberFormatter formatter = new MyNumberFormatter();

    @Test
    void parse() throws ParseException {
        Number result = formatter.parse("1,000", Locale.KOREA);
        assertThat(result).isEqualTo(1000L);
    }

    @Test
    void print() {
        String result = formatter.print(1000L, Locale.KOREA);
        assertThat(result).isEqualTo("1,000");
    }
}