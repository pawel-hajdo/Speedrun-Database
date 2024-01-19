package com.speedrundatabaseapi.user;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Custom JSON serializer for the User class. This serializer defines how a User object
 * should be converted to its JSON representation during serialization.
 *
 * <p>It customizes the serialization process to include only specific fields of the User class.</p>
 *
 * @author Paweł Hajdo
 * @version 1.0
 * @see User
 */
public class UserCustomSerializer extends JsonSerializer<User> {

    /**
     * Serializes a User object to its JSON representation.
     *
     * @param user              The User object to be serialized.
     * @param jsonGenerator     The JsonGenerator to write JSON content.
     * @param serializerProvider The SerializerProvider to use for accessing serializers.
     * @throws IOException If an error occurs during JSON generation.
     */
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
