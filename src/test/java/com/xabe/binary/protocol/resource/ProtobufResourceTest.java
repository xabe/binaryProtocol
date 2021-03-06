package com.xabe.binary.protocol.resource;

import com.xabe.binary.protocol.payload.PayloadProtobuf;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

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