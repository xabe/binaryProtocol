package com.xabe.binary.protocol.resource;

import com.xabe.binary.protocol.payload.Payload;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

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