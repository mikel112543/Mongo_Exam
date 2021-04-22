import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class jsonread {

    public jsonread() {
        throw new UnsupportedOperationException();
    }

    public void serialize(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> map = objectMapper.readValue(Paths.get(json).toFile(), new TypeReference<Map<String, Object>>() {
            });
            System.out.println(map.entrySet()
                    .stream()
                    .flatMap(jsonread::flatten)
                    .collect(Collectors.joining(",\n\t", "{\n\t", "\t\n}")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static Stream<StringBuilder> flatten(Map.Entry<String, Object> entrySet) {
        Object value = entrySet.getValue();
        if (value instanceof Map) {
            return ((Map<String, Object>) value).entrySet()
                    .stream()
                    .flatMap(jsonread::flatten)
                    .map(s -> s.insert(0, entrySet.getKey() + "."));

        }
        return Stream.of(new StringBuilder(entrySet.toString()));
    }
}
