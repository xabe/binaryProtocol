package com.xabe.binary.protocol.it.resource;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.nio.charset.StandardCharsets;

public class StatusResourceTest_IT {

    @Test
    public void shouldGetStatus() throws Exception {
        //Given
        final Client client = ClientBuilder.newClient();
        final WebTarget target = client.target( "http://localhost:8008"  ).path( "/v1" ).path( "/status" );

        //When
        final Response response = client.target(target.getUri())
                .request(MediaType.TEXT_PLAIN)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN + "; charset=" + StandardCharsets.UTF_8.name())
                .get();

        //Then
        final String statusPayload = response.readEntity(String.class);
        Assert.assertThat(statusPayload,CoreMatchers.is(CoreMatchers.notNullValue()));
    }

}
