package com.speedrundatabaseapi.user;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class UserCustomSerializer extends JsonSerializer<User> {
    @Override
    public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectField("userId", user.getUserId());
        jsonGenerator.writeObjectField("login", user.getLogin());
        jsonGenerator.writeObjectField("email", user.getEmail());
        jsonGenerator.writeObjectField("role", user.getRole());
        jsonGenerator.writeEndObject();
    }
}
