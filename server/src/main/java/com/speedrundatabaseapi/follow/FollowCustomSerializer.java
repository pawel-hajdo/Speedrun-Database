package com.speedrundatabaseapi.follow;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class FollowCustomSerializer extends JsonSerializer<Follow> {
    @Override
    public void serialize(Follow follow, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectField("followerId", follow.getFollower().getUserId());
        jsonGenerator.writeObjectField("followingId", follow.getFollowing().getUserId());
        jsonGenerator.writeObjectField("followTime", follow.getFollowTime());
        jsonGenerator.writeEndObject();
    }
}
