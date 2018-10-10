package com.xabe.binary.protocol.config.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.xabe.binary.protocol.payload.Payload;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class KryoPayloadSerializer extends Serializer<Payload> {

    private static final boolean DOES_NOT_ACCEPT_NULL = true;
    private static final boolean IMMUTABLE = true;

    public KryoPayloadSerializer() {
        super(DOES_NOT_ACCEPT_NULL, IMMUTABLE);
    }

    @Override
    public void write(Kryo kryo, Output output, Payload payload) {
        output.writeString(payload.getDate().format(DateTimeFormatter.ISO_DATE_TIME));
        output.writeString(payload.getName());
        output.writeInt(payload.getNumber(), true);
        output.writeBoolean(payload.getActive());
    }

    @Override
    public Payload read(Kryo kryo, Input input, Class<Payload> aClass) {
        final LocalDateTime date = LocalDateTime.parse(input.readString(),DateTimeFormatter.ISO_DATE_TIME);
        final String name = input.readString();
        final Integer number = input.readInt(true);
        final Boolean active = input.readBoolean();
        return new Payload(date,name,number,active);
    }
}
