package hello.converter.formatter;

import hello.converter.converter.IpPortToStringConverter;
import hello.converter.converter.StringToIpPortConverter;
import hello.converter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.format.support.DefaultFormattingConversionService;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by Hunseong on 2022/04/04
 */
public class FormattingConversionServiceTest {

    @Test
    void formattingConversionService() {

        DefaultFormattingConversionService cs = new DefaultFormattingConversionService();

        // 컨버터 등록
        cs.addConverter(new StringToIpPortConverter());
        cs.addConverter(new IpPortToStringConverter());

        // 포맷터 등록
        cs.addFormatter(new MyNumberFormatter());

        // 컨버터 사용
        IpPort result1 = cs.convert("127.0.0.1:8080", IpPort.class);
        assertThat(result1).isEqualTo(new IpPort("127.0.0.1", 8080));
        String result2 = cs.convert(new IpPort("127.0.0.1", 8080), String.class);
        assertThat(result2).isEqualTo("127.0.0.1:8080");

        // 포맷터 사용
        String result3 = cs.convert(1000L, String.class);
        assertThat(result3).isEqualTo("1,000");
        Long result4 = cs.convert("1,000", Long.class);
        assertThat(result4).isEqualTo(1000L);
    }
}
