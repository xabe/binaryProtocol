package com.xabe.binary.protocol.resource;

import com.xabe.binary.protocol.payload.Payload;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class JsonResourceTest {

    @Test
    public void shouldReturnJsonPayloadWhenInvokeGetPayload() throws Exception {
        //Given
        final JsonResource jsonResource = new JsonResource();

        //When
        final Payload payload = jsonResource.getPayload();

        //Then
        assertThat(payload, is(notNullValue()));

    }
}
