package fun.imiku.napcat4j.dto.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.jspecify.annotations.NonNull;

/**
 * 骰子消息段
 */
@Data
public class OB11MessageDice implements OB11MessageData {

    @NonNull
    @JsonProperty("type")
    private String type;

    @NonNull
    @JsonProperty("data")
    private DiceData data;

    @Data
    public static class DiceData {
        /**
         * 骰子结果（number|string）
         */
        @NonNull
        @JsonProperty("result")
        private Object result;
    }
}
