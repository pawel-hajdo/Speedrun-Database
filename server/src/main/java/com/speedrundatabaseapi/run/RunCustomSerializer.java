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
        jsonGenerator.writeObjectField("userId", run.getUser().getUserId());
        jsonGenerator.writeObjectField("gameId", run.getGame().getGameId());
        jsonGenerator.writeObjectField("time", run.getTime());
        jsonGenerator.writeObjectField("type", run.getType());
        jsonGenerator.writeObjectField("videoLink", run.getVideoLink());
        jsonGenerator.writeObjectField("date", run.getDate());
        jsonGenerator.writeObjectField("platform", run.getPlatform().getPlatformId());
        jsonGenerator.writeObjectField("confirmedBy", run.getConfirmedBy());
        jsonGenerator.writeEndObject();
    }
}
