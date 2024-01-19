package com.speedrundatabaseapi.run;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Custom serializer for the Run class, used during JSON serialization.
 *
 * <p>This class defines the serialization process for converting a Run object into JSON format.</p>
 *
 * @author Paweł Hajdo
 * @version 1.0
 */
public class RunCustomSerializer extends JsonSerializer<Run> {

    /**
     * Serializes a Run object into JSON format.
     *
     * @param run                The Run object to be serialized.
     * @param jsonGenerator      The JSON generator.
     * @param serializerProvider The serializer provider.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public void serialize(Run run, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        // Writing basic fields
        jsonGenerator.writeObjectField("runId", run.getRunId());

        // Writing user details
        jsonGenerator.writeObjectFieldStart("user");
        jsonGenerator.writeObjectField("userId", run.getUser().getUserId());
        jsonGenerator.writeObjectField("login", run.getUser().getLogin());
        jsonGenerator.writeEndObject();

        // Writing game details
        jsonGenerator.writeObjectFieldStart("game");
        jsonGenerator.writeObjectField("gameId", run.getGame().getGameId());
        jsonGenerator.writeObjectField("name", run.getGame().getName());
        jsonGenerator.writeObjectField("image", run.getGame().getImage());
        jsonGenerator.writeEndObject();

        // Writing other fields
        jsonGenerator.writeObjectField("time", run.getTime());
        jsonGenerator.writeObjectField("type", run.getType());
        jsonGenerator.writeObjectField("videoLink", run.getVideoLink());
        jsonGenerator.writeObjectField("date", run.getDate());

        // Writing platform details
        jsonGenerator.writeObjectFieldStart("platform");
        jsonGenerator.writeObjectField("platformId", run.getPlatform().getPlatformId());
        jsonGenerator.writeObjectField("type", run.getPlatform().getType());
        jsonGenerator.writeObjectField("name", run.getPlatform().getName());
        jsonGenerator.writeEndObject();

        jsonGenerator.writeObjectField("confirmedBy", run.getConfirmedBy());
        jsonGenerator.writeEndObject();
    }
}
