package com.xabe.binary.protocol.resource;

import com.xabe.binary.protocol.payload.PayloadProtobuf;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class ProtobufResourceTest {

    @Test
    public void shouldReturnJsonPayloadWhenInvokeGetPayload() throws Exception {
        //Given
        final ProtobufResource protobufResource = new ProtobufResource();

        //When
        final PayloadProtobuf.Payload payload = protobufResource.getPayload();

        //Then
        assertThat(payload, is(notNullValue()));

    }

}