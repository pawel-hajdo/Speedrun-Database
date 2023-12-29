package com.speedrundatabaseapi.run;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.speedrundatabaseapi.game.Game;
import com.speedrundatabaseapi.platform.Platform;
import com.speedrundatabaseapi.user.User;

import java.io.IOException;
import java.time.Duration;

public class RunCustomDeserializer extends JsonDeserializer<Run> {
    @Override
    public Run deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        long userId = node.get("userId").asLong();
        long gameId = node.get("gameId").asLong();
        String time = node.get("time").asText();
        String videoLink = node.get("videoLink").asText();
        String type = node.get("type").asText();
        long platformId = node.get("platformId").asLong();

        User user = new User();
        user.setUserId(userId);

        Game game = new Game();
        game.setGameId(gameId);

        Platform platform = new Platform();
        platform.setPlatformId(platformId);

        Run run = new Run();
        run.setUser(user);
        run.setGame(game);
        run.setTime(Duration.parse(time));
        run.setVideoLink(videoLink);
        run.setType(type);
        run.setPlatform(platform);

        return run;
    }
}
