package com.xabe.binary.protocol.it.resource;

import com.xabe.binary.protocol.config.MediaTypeExt;
import com.xabe.binary.protocol.config.jackson.ObjectMapperContextResolver;
import com.xabe.binary.protocol.payload.Payload;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class DeleteTest_IT {

    @Test
    public void shouldInvokeDeleteWithBody() throws Exception {
        //Given
        ClientConfig config = new ClientConfig();
        config.property(ClientProperties.SUPPRESS_HTTP_COMPLIANCE_VALIDATION, true);
        final Client client = ClientBuilder.newClient(config).register(JacksonFeature.class).register(new ObjectMapperContextResolver());
        final WebTarget target = client.target( "http://localhost:8008"  ).path( "/v1" ).path( "/json" );

        //When
        final Response response = client.target(target.getUri())
                .request(MediaTypeExt.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT,MediaTypeExt.APPLICATION_JSON)
                .build("DELETE", Entity.entity("{}", MediaType.APPLICATION_JSON))
                .invoke();

        //Then
        final Payload payload = response.readEntity(Payload.class);
        assertThat(payload, is(CoreMatchers.notNullValue()));
        assertThat(payload.getName(), is("json"));
        assertThat(payload.getDate(), is(notNullValue()));
        assertThat(payload.getNumber(), is(123456));
        assertThat(payload.getActive(), is(true));
    }

    @Test
    public void shouldInvokeDeleteWithOutBody() throws Exception {
        //Given
        ClientConfig config = new ClientConfig();
        config.property(ClientProperties.SUPPRESS_HTTP_COMPLIANCE_VALIDATION, true);
        final Client client = ClientBuilder.newClient(config).register(JacksonFeature.class).register(new ObjectMapperContextResolver());
        final WebTarget target = client.target( "http://localhost:8008"  ).path( "/v1" ).path( "/json" );

        //When
        final Response response = client.target(target.getUri())
                .request(MediaTypeExt.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT,MediaTypeExt.APPLICATION_JSON)
                .delete();

        //Then
        final Payload payload = response.readEntity(Payload.class);
        assertThat(payload, is(CoreMatchers.notNullValue()));
        assertThat(payload.getName(), is("json"));
        assertThat(payload.getDate(), is(notNullValue()));
        assertThat(payload.getNumber(), is(123456));
        assertThat(payload.getActive(), is(true));
    }
}
