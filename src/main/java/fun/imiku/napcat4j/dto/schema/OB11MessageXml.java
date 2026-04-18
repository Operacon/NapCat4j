package fun.imiku.napcat4j.dto.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.jspecify.annotations.NonNull;

/**
 * XML消息段
 */
@Data
public class OB11MessageXml implements OB11MessageData {

    @NonNull
    @JsonProperty("type")
    private String type;

    @NonNull
    @JsonProperty("data")
    private XmlData data;

    @Data
    public static class XmlData {
        /**
         * XML数据
         */
        @NonNull
        @JsonProperty("data")
        private String data;
    }
}
