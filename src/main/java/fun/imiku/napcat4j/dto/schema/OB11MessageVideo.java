package fun.imiku.napcat4j.dto.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.jspecify.annotations.NonNull;

/**
 * 视频消息段
 */
@Data
public class OB11MessageVideo implements OB11MessageData {

    @NonNull
    @JsonProperty("type")
    private String type;

    @NonNull
    @JsonProperty("data")
    private FileBaseData data;
}
