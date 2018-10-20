package com.xabe.binary.protocol.payload;

import com.xabe.binary.protocol.AbstractPojoTest;
import org.junit.Before;

import java.time.LocalDateTime;

public class PayloadTest extends AbstractPojoTest<Payload> {

    @Override
    @Before
    public void beforeTests() {
        final LocalDateTime localDateTime = LocalDateTime.now();
        this.pojo = new Payload(localDateTime,"",1,true);
        this.equalsPojo = new Payload(localDateTime,"",1,true);
        this.otherPojo = new Payload(LocalDateTime.now(),"",1,false);
    }
}