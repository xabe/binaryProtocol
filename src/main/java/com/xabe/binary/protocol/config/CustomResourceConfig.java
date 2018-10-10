package com.xabe.binary.protocol.config;

import com.xabe.binary.protocol.config.jackson.ObjectMapperContextResolver;
import com.xabe.binary.protocol.config.kryo.KryoProvider;
import com.xabe.binary.protocol.config.protobuf.ProtobufProvider;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class CustomResourceConfig extends ResourceConfig {

    public CustomResourceConfig() {
        packages("com.xabe.binary.protocol.resource");
        register(JacksonFeature.class);
        register(new ObjectMapperContextResolver());
        register(new KryoProvider());
        register(new ProtobufProvider());
        property( ServerProperties.BV_FEATURE_DISABLE, true );
        property( ServerProperties.RESOURCE_VALIDATION_IGNORE_ERRORS, true );
        property( ServerProperties.TRACING, "ALL" );
        property( ServerProperties.TRACING_THRESHOLD, "VERBOSE" );
    }
}
