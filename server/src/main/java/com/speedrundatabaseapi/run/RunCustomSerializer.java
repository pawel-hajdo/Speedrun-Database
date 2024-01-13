package com.speedrundatabaseapi.run;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class RunCustomSerializer extends JsonSerializer<Run> {
    @Override
    public void serialize(Run run, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectField("runId", run.getRunId());

        jsonGenerator.writeObjectFieldStart("user");
        jsonGenerator.writeObjectField("userId", run.getUser().getUserId());
        jsonGenerator.writeObjectField("login", run.getUser().getUsername());
        jsonGenerator.writeEndObject();

        jsonGenerator.writeObjectFieldStart("game");
        jsonGenerator.writeObjectField("gameId", run.getGame().getGameId());
        jsonGenerator.writeObjectField("name", run.getGame().getName());
        jsonGenerator.writeObjectField("image", run.getGame().getImage());
        jsonGenerator.writeEndObject();

        jsonGenerator.writeObjectField("time", run.getTime());
        jsonGenerator.writeObjectField("type", run.getType());
        jsonGenerator.writeObjectField("videoLink", run.getVideoLink());
        jsonGenerator.writeObjectField("date", run.getDate());

        jsonGenerator.writeObjectFieldStart("platform");
        jsonGenerator.writeObjectField("platformId", run.getPlatform().getPlatformId());
        jsonGenerator.writeObjectField("type", run.getPlatform().getType());
        jsonGenerator.writeObjectField("name", run.getPlatform().getName());
        jsonGenerator.writeEndObject();

        jsonGenerator.writeObjectField("confirmedBy", run.getConfirmedBy());
        jsonGenerator.writeEndObject();
    }
}
