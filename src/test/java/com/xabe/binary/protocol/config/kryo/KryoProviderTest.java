package com.xabe.binary.protocol.config.kryo;

import com.xabe.binary.protocol.config.MediaTypeExt;
import com.xabe.binary.protocol.payload.Payload;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.WebApplicationException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class KryoProviderTest {

    private KryoProvider kryoProvider;

    @BeforeEach
    public void init(){
        this.kryoProvider = new KryoProvider();
    }

    @Test
    public void givenAObjectWhenInvokeWriteToThenReturnByteArray() throws Exception {
        //Given
        final Payload payload = new Payload(LocalDateTime.now(),"fake",1,true);
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        //When
        final long size = kryoProvider.getSize(payload, null, null, null, MediaTypeExt.APPLICATION_KRYO_TYPE);
        final boolean isWritable =kryoProvider.isWriteable(null,null,null,MediaTypeExt.APPLICATION_KRYO_TYPE);
        kryoProvider.writeTo(payload,null,null,null,null,null,outputStream);

        //Then
        assertThat(size, is(-1l));
        assertThat(isWritable, is(true));
        assertThat(outputStream.toByteArray(), is(notNullValue()));
        assertThat(outputStream.size(), is(greaterThan(1)));
    }

    @Test
    public void givenAByteArrayWhenInvokeReadFromThenReturnPayload() throws Exception {
        //Given
        final LocalDateTime time = LocalDateTime.now();
        final Class payloadClass = Payload.class;
        final Payload payload = new Payload(time,"fake",1,true);
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        kryoProvider.writeTo(payload,null,null,null,null,null,outputStream);

        final ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

        //When
        final Object result = (Object) kryoProvider.readFrom(payloadClass, null, null, null, null, inputStream);
        final boolean readable = kryoProvider.isReadable(null, null, null, null);

        //Then
        assertThat(result, is(notNullValue()));
        assertThat(readable, is(true));
        final Payload payload1 = Payload.class.cast(result);
        assertThat(payload1.getActive(),is(true));
        assertThat(payload1.getNumber(),is(1));
        assertThat(payload1.getName(),is("fake"));
        assertThat(payload1.getDate(),is(time));
    }

    @Test
    public void givenAByteArrayWhenInvokeReadFromThenReturnWebApplicationException() throws Exception {
        //Given
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(new byte[]{});

        //When
        assertThrows(WebApplicationException.class, () -> {
            kryoProvider.readFrom(null, null, null, null, null, inputStream);
        });
    }

}