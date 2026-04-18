package fun.imiku.napcat4j.dto.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.jspecify.annotations.NonNull;

/**
 * 戳一戳消息段
 */
@Data
public class OB11MessagePoke implements OB11MessageData {

    @NonNull
    @JsonProperty("type")
    private String type;

    @NonNull
    @JsonProperty("data")
    private PokeData data;

    @Data
    public static class PokeData {
        /**
         * 戳一戳类型
         */
        @NonNull
        @JsonProperty("type")
        private String type;

        /**
         * 戳一戳 ID
         */
        @NonNull
        @JsonProperty("id")
        private String id;
    }
}
