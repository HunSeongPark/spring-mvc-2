package hello.converter.controller;

import hello.converter.type.IpPort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Hunseong on 2022/04/04
 */
@RestController
public class HelloController {

    @GetMapping("/ip-port")
    public IpPort ipPort(@RequestParam IpPort ipPort) {
        return ipPort;
    }
}
