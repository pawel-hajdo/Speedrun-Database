package com.speedrundatabaseapi.follow;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Custom JSON serializer for Follow entities.
 *
 * <p>The FollowCustomSerializer class extends JsonSerializer to provide a custom serialization
 * process for Follow entities. It specifically serializes the Follower ID, Following ID,
 * and Follow Time fields of a Follow entity.</p>
 *
 * @author Paweł Hajdo
 * @version 1.0
 */
public class FollowCustomSerializer extends JsonSerializer<Follow> {

    /**
     * Custom serialization method for Follow entities.
     *
     * @param follow            The Follow entity to be serialized.
     * @param jsonGenerator     The JsonGenerator used for writing JSON content.
     * @param serializerProvider The SerializerProvider for accessing serializers.
     * @throws IOException If an error occurs during JSON writing.
     */
    @Override
    public void serialize(Follow follow, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectField("followerId", follow.getFollower().getUserId());
        jsonGenerator.writeObjectField("followingId", follow.getFollowing().getUserId());
        jsonGenerator.writeObjectField("followTime", follow.getFollowTime());
        jsonGenerator.writeEndObject();
    }
}
