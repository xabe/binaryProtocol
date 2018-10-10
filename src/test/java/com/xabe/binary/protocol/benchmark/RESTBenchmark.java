package com.xabe.binary.protocol.benchmark;

import com.xabe.binary.protocol.config.MediaTypeExt;
import com.xabe.binary.protocol.config.kryo.KryoProvider;
import com.xabe.binary.protocol.config.protobuf.ProtobufProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.junit.Before;
import org.junit.Test;
import org.openjdk.jmh.annotations.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.SECONDS)
public class RESTBenchmark {

    private Client client = ClientBuilder.newClient(new ClientConfig().register(KryoProvider.class).register(ProtobufProvider.class).property(ClientProperties.CONNECT_TIMEOUT,1000).property(ClientProperties.READ_TIMEOUT,1000));


    @Benchmark
    public Integer getKryo() throws Exception {
        //Given
        final WebTarget target = client.target( "http://localhost:8008"  ).path( "/v1" ).path( "/kryo" );

        //When
        final Response response = client.target(target.getUri())
                .request(MediaTypeExt.APPLICATION_KRYO)
                .header(HttpHeaders.ACCEPT,MediaTypeExt.APPLICATION_KRYO)
                .get();

        //Then
        return response.getLength();
    }

    @Benchmark
    public Integer getProtobuf() throws Exception {
        //Given
        final WebTarget target = client.target( "http://localhost:8008"  ).path( "/v1" ).path( "/protobuf" );

        //When
        final Response response = client.target(target.getUri())
                .request(MediaTypeExt.APPLICATION_PROTOBUF)
                .header(HttpHeaders.ACCEPT,MediaTypeExt.APPLICATION_PROTOBUF)
                .get();

        //Then
        return response.getLength();
    }

    @Benchmark
    public Integer getJson() throws Exception {
        //Given
        final WebTarget target = client.target( "http://localhost:8008"  ).path( "/v1" ).path( "/status" );

        //When
        final Response response = client.target(target.getUri())
                .request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON + "; charset=" + StandardCharsets.UTF_8.name())
                .get();

        //Then
        return response.getLength();
    }
}
