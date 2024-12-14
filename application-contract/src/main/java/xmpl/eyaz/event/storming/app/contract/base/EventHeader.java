package xmpl.eyaz.event.storming.app.contract.base;

import org.apache.kafka.common.header.Header;

public class EventHeader implements Header {

    private final String key;
    private final String value;

    public EventHeader(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String key() {
        return key;
    }

    @Override
    public byte[] value() {
        return value.getBytes();
    }
}
