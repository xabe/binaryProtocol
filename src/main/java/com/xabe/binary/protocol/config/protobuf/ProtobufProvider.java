package com.xabe.binary.protocol.config.protobuf;

import com.google.protobuf.Message;
import com.google.protobuf.TextFormat;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Provider
public class ProtobufProvider implements MessageBodyReader<Message>, MessageBodyWriter<Message> {

    public static final String NEW_BUILDER = "newBuilder";
    public static final String TEXT_FORMAT = "text-format";
    private final Map<Class<Message>, Method> methodCache = new ConcurrentHashMap<>();

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return isAssignableFrom(type);
    }

    @Override
    public Message readFrom(Class<Message> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) {

        try {
            final Method newBuilder = methodCache.computeIfAbsent(
                            type,
                            t -> {
                                try {
                                    return t.getMethod(NEW_BUILDER);
                                } catch (Exception e) {
                                    return null;
                                }
                            });
            final Message.Builder builder = (Message.Builder) newBuilder.invoke(type);
            if (mediaType.getSubtype().contains(TEXT_FORMAT)) {
                TextFormat.merge(new InputStreamReader(entityStream, StandardCharsets.UTF_8), builder);
                return builder.build();
            } else {
                return builder.mergeFrom(entityStream).build();
            }
        } catch (Exception e) {
            throw new WebApplicationException(e);
        }
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return isAssignableFrom(type);
    }

    @Override
    public long getSize(Message message, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return message.getSerializedSize();
    }

    @Override
    public void writeTo(Message message, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) {
        try {
            entityStream.write(message.toByteArray());
        } catch (Exception e) {
            throw new WebApplicationException(e);
        }
    }

    private boolean isAssignableFrom(Class<?> type) {
        return Message.class.isAssignableFrom(type);
    }
}
