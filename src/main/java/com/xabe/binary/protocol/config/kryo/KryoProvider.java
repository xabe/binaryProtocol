package com.xabe.binary.protocol.config.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.pool.KryoPool;
import com.xabe.binary.protocol.config.MediaTypeExt;
import com.xabe.binary.protocol.payload.Payload;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
public class KryoProvider implements MessageBodyWriter<Object>, MessageBodyReader<Object> {

    private final KryoPool pool;

    public KryoProvider() {
        pool = new KryoPool.Builder(() -> {
            Kryo kryo = new Kryo();
            //Registering Serializer for Class
            kryo.addDefaultSerializer( Payload.class, KryoPayloadSerializer.class);
            return kryo;
        }).build();

    }

    //
    // MessageBodyWriter
    //

    @Override
    public long getSize(Object object, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return MediaTypeExt.APPLICATION_KRYO_TYPE.equals(mediaType);
    }

    @Override
    public void writeTo(Object object, Class<?> type, Type genericType,
                        Annotation[] annotations, MediaType mediaType,
                        MultivaluedMap<String, Object> httpHeaders,
                        OutputStream entityStream) throws IOException {
        final Kryo kryo = pool.borrow();
        try (final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                final Output output = new Output( baos );){
            kryo.writeObject( output, object ); //writeClassAndObject
            output.flush();
            entityStream.write( baos.toByteArray() );
        } finally {
            pool.release( kryo );
        }
    }

    //
    // MessageBodyReader
    //

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public Object readFrom(Class<Object> type, Type genericType,
                           Annotation[] annotations, MediaType mediaType,
                           MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
            throws IOException {
        try {
            final Kryo kryo = pool.borrow();
            try (Input input = new Input( entityStream );) {
                return kryo.readObject( input, type ); //readClassAndObject
            } finally {
                pool.release( kryo );
            }
        } catch (Exception e) {
            throw new WebApplicationException( e );
        }
    }
}
