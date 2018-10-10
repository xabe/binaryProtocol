package com.xabe.binary.protocol.it.resource;

import com.xabe.binary.protocol.config.MediaTypeExt;
import com.xabe.binary.protocol.config.jackson.ObjectMapperContextResolver;
import com.xabe.binary.protocol.payload.Payload;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class JsonResourceTest_IT {

    @Test
    public void shouldJson() throws Exception {
        //Given
        final Client client = ClientBuilder.newClient().register(JacksonFeature.class).register(new ObjectMapperContextResolver());
        final WebTarget target = client.target( "http://localhost:8008"  ).path( "/v1" ).path( "/json" );

        //When
        final Response response = client.target(target.getUri())
                .request(MediaTypeExt.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT,MediaTypeExt.APPLICATION_JSON)
                .get();

        //Then
        final Payload payload = response.readEntity(Payload.class);
        assertThat(payload, is(CoreMatchers.notNullValue()));
        assertThat(payload.getName(), is("json"));
        assertThat(payload.getDate(), is(notNullValue()));
        assertThat(payload.getNumber(), is(123456));
        assertThat(payload.getActive(), is(true));
    }

}
