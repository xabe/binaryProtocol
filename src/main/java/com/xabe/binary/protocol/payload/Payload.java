package com.xabe.binary.protocol.payload;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.LocalDateTime;

public class Payload {

    private final LocalDateTime date;
    private final String name;
    private final Integer number;
    private final Boolean active;

    @JsonCreator
    public Payload(
            @JsonProperty("date") LocalDateTime date,
            @JsonProperty("name") String name,
            @JsonProperty("number") Integer number,
            @JsonProperty("active") Boolean active) {
        this.date = date;
        this.name = name;
        this.number = number;
        this.active = active;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public Integer getNumber() {
        return number;
    }

    public Boolean getActive() {
        return active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Payload that = (Payload) o;

        return new EqualsBuilder()
                .append(date, that.date)
                .append(name, that.name)
                .append(number, that.number)
                .append(active, that.active)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(date)
                .append(name)
                .append(number)
                .append(active)
                .toHashCode();
    }
}
