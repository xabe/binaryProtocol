package com.xabe.binary.protocol.it.resource;

import com.xabe.binary.protocol.config.MediaTypeExt;
import com.xabe.binary.protocol.config.kryo.KryoProvider;
import com.xabe.binary.protocol.payload.Payload;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class KryoResourceTest_IT {

    @Test
    public void shouldKryo() throws Exception {
        //Given
        final Client client = ClientBuilder.newClient().register(KryoProvider.class);
        final WebTarget target = client.target( "http://localhost:8008"  ).path( "/v1" ).path( "/kryo" );

        //When
        final Response response = client.target(target.getUri())
                .request(MediaTypeExt.APPLICATION_KRYO)
                .header(HttpHeaders.ACCEPT,MediaTypeExt.APPLICATION_KRYO)
                .get();

        //Then
        final Payload payload = response.readEntity(Payload.class);
        assertThat(payload, is(notNullValue()));
        assertThat(payload.getName(), is("kryo"));
        assertThat(payload.getDate(), is(notNullValue()));
        assertThat(payload.getNumber(), is(123456));
        assertThat(payload.getActive(), is(true));
    }

}
