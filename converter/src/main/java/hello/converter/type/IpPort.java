package hello.converter.type;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Created by Hunseong on 2022/04/04
 */
@Getter
@EqualsAndHashCode
public class IpPort {

    private String ip;
    private int port;

    public IpPort(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
}
