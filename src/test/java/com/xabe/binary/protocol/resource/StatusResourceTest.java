package com.xabe.binary.protocol.resource;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class StatusResourceTest {

    private StatusResource statusResource = new StatusResource();

    @Test
    public void shouldReturnStatus() throws Exception {
        //Given

        //When
        final String statusPayload = statusResource.getStatus();

        //Then
        assertThat(statusPayload, is(notNullValue()));
    }

}
