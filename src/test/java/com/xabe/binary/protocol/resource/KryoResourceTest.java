package com.xabe.binary.protocol.resource;

import com.xabe.binary.protocol.payload.Payload;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class KryoResourceTest {

    @Test
    public void shouldReturnJsonPayloadWhenInvokeGetPayload() throws Exception {
        //Given
        final KryoResource kryoResource = new KryoResource();

        //When
        final Payload payload = kryoResource.getPayload();

        //Then
        assertThat(payload, is(notNullValue()));

    }

}