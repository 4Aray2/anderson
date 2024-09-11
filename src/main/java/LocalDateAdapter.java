import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class LocalDateAdapter implements JsonDeserializer<LocalDate> {
    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        if (json.isJsonNull() || json.getAsString().isEmpty()) {
            return null;
        }
        try {
            return LocalDate.parse(json.getAsString());
        } catch (DateTimeParseException e) {
            System.err.println("Error parsing date: " + json.getAsString());
            return null;
        }
    }
}
