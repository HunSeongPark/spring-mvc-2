package hello.converter.converter;

import hello.converter.converter.IpPortToStringConverter;
import hello.converter.converter.StringToIpPortConverter;
import hello.converter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by Hunseong on 2022/04/04
 */
public class ConversionServiceTest {

    @Test
    void conversionService() {

        // 등록
        DefaultConversionService cs = new DefaultConversionService();
        cs.addConverter(new StringToIpPortConverter());
        cs.addConverter(new IpPortToStringConverter());

        // 사용
        IpPort result1 = cs.convert("127.0.0.1:8080", IpPort.class);
        assertThat(result1).isEqualTo(new IpPort("127.0.0.1",8080));

        String result2 = cs.convert(new IpPort("127.0.0.1", 8080), String.class);
        assertThat(result2).isEqualTo("127.0.0.1:8080");
    }
}
