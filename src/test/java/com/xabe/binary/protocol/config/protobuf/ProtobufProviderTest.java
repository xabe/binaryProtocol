package com.xabe.binary.protocol.config.protobuf;

import com.xabe.binary.protocol.config.MediaTypeExt;
import com.xabe.binary.protocol.payload.PayloadProtobuf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.WebApplicationException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.Instant;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ProtobufProviderTest {

    private ProtobufProvider protobufProvider;

    @BeforeEach
    public void init(){
        this.protobufProvider = new ProtobufProvider();
    }

    @Test
    public void givenAObjectWhenInvokeWriteToThenReturnByteArray() throws Exception {
        //Given
        final PayloadProtobuf.Payload payload = PayloadProtobuf.Payload.newBuilder().setName("protobuf").setActive(false).setNumber(123123).setDate(PayloadProtobuf.Timestamp.newBuilder().setSeconds(Instant.now().getEpochSecond()).build()).build();
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        //When
        final long size = protobufProvider.getSize(payload, null, null, null, MediaTypeExt.APPLICATION_PROTOBUF_TYPE);
        final boolean isWritable = protobufProvider.isWriteable(PayloadProtobuf.Payload.class,null,null,MediaTypeExt.APPLICATION_PROTOBUF_TYPE);
        protobufProvider.writeTo(payload,null,null,null,null,null,outputStream);

        //Then
        assertThat(size, is(greaterThanOrEqualTo(1l)));
        assertThat(isWritable, is(true));
        assertThat(outputStream.toByteArray(), is(notNullValue()));
        assertThat(outputStream.size(), is(greaterThan(1)));
    }

    @Test
    public void givenAByteArrayWhenInvokeReadFromThenReturnPayload() throws Exception {
        //Given
        final Instant time = Instant.now();
        final Class payloadClass = PayloadProtobuf.Payload.class;
        final PayloadProtobuf.Payload payload = PayloadProtobuf.Payload.newBuilder().setName("protobuf").setActive(false).setNumber(123123).setDate(PayloadProtobuf.Timestamp.newBuilder().setSeconds(time.getEpochSecond()).build()).build();
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        protobufProvider.writeTo(payload,null,null,null,null,null,outputStream);

        final ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

        //When
        final Object result = (Object) protobufProvider.readFrom(payloadClass, null, null, MediaTypeExt.APPLICATION_PROTOBUF_TYPE, null, inputStream);
        final boolean readable = protobufProvider.isReadable(payloadClass, null, null, MediaTypeExt.APPLICATION_PROTOBUF_TYPE);

        //Then
        assertThat(result, is(notNullValue()));
        assertThat(readable, is(true));
        final PayloadProtobuf.Payload payload1 = PayloadProtobuf.Payload.class.cast(result);
        assertThat(payload1.getActive(),is(false));
        assertThat(payload1.getNumber(),is(123123));
        assertThat(payload1.getName(),is("protobuf"));
        assertThat(payload1.getDate().getSeconds(),is(time.getEpochSecond()));
    }

    @Test
    public void givenAByteArrayWhenInvokeReadFromThenReturnWebApplicationException() throws Exception {
        //Given
        final Class payloadClass = String.class;
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(new byte[]{});

        //When

        assertThrows(WebApplicationException.class, () -> {
            protobufProvider.readFrom(payloadClass, null, null, null, null, inputStream);

        });
    }

}