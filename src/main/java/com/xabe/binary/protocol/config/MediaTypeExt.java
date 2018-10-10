package com.xabe.binary.protocol.config;

import javax.ws.rs.core.MediaType;

public class MediaTypeExt extends MediaType {
    /**
     * A {@code String} constant representing "{@value #APPLICATION_PROTOBUF}" media type.
     */
    public final static String APPLICATION_PROTOBUF = "application/x-protobuf";
    /**
     * A {@link javax.ws.rs.core.MediaType} constant representing "{@value #APPLICATION_PROTOBUF}" media type.
     */
    public final static MediaType APPLICATION_PROTOBUF_TYPE = new MediaType("application", "x-protobuf");

    /**
     * A {@code String} constant representing {@value #APPLICATION_KRYO} media type.
     */
    public final static String APPLICATION_KRYO = "application/x-kryo";

    /**
     * A {@link MediaType} constant representing {@value #APPLICATION_KRYO} media type.
     */
    public final static MediaType APPLICATION_KRYO_TYPE = new MediaType("application", "x-kryo");
}
