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

/**
 * Custom deserializer for the Run class, used during JSON deserialization.
 *
 * <p>This class defines the deserialization process for converting JSON data into a Run object.</p>
 *
 * @author Paweł Hajdo
 * @version 1.0
 */
public class RunCustomDeserializer extends JsonDeserializer<Run> {

    /**
     * Deserializes JSON data into a Run object.
     *
     * @param jsonParser             The JSON parser.
     * @param deserializationContext The deserialization context.
     * @return The deserialized Run object.
     * @throws IOException      If an I/O error occurs.
     * @throws JacksonException If a Jackson exception occurs.
     */
    @Override
    public Run deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        // Extracting values from the JSON node
        long userId = node.get("userId").asLong();
        long gameId = node.get("gameId").asLong();
        String time = node.get("time").asText();
        String videoLink = node.get("videoLink").asText();
        String type = node.get("type").asText();
        long platformId = node.get("platformId").asLong();

        // Creating User object
        User user = new User();
        user.setUserId(userId);

        // Creating Game object
        Game game = new Game();
        game.setGameId(gameId);

        // Creating Platform object
        Platform platform = new Platform();
        platform.setPlatformId(platformId);

        // Creating Run object and setting its properties
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
