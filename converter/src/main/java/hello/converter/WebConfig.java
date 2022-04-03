package hello.converter;

import hello.converter.converter.IpPortToStringConverter;
import hello.converter.converter.StringToIpPortConverter;
import hello.converter.formatter.MyNumberFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Hunseong on 2022/04/04
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {

        // 컨버터 등록
        registry.addConverter(new StringToIpPortConverter());
        registry.addConverter(new IpPortToStringConverter());

        // 포맷터 등록
        registry.addFormatter(new MyNumberFormatter());
    }
}
