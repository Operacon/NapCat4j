package fun.imiku.napcat4j.dto.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.jspecify.annotations.NonNull;

/**
 * 小程序消息段
 */
@Data
public class OB11MessageMiniApp implements OB11MessageData {

    @NonNull
    @JsonProperty("type")
    private String type;

    @NonNull
    @JsonProperty("data")
    private MiniAppData data;

    @Data
    public static class MiniAppData {
        /**
         * 小程序数据
         */
        @NonNull
        @JsonProperty("data")
        private String data;
    }
}
