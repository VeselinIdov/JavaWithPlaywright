package core;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class JSONUtils {

    @SneakyThrows
    public static <P> P deserializeResponse(String jsonResponse, Class<P> cls) {
        return new ObjectMapper().readValue(jsonResponse, cls);
    }
}
