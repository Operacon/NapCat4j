package fun.imiku.napcat4j.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NonNull;

/**
 * 群组禁言请求
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetGroupBanRequest {

    /**
     * 群号
     */
    @NonNull
    @JsonProperty("group_id")
    private Long groupId;

    /**
     * 用户 QQ
     */
    @NonNull
    @JsonProperty("user_id")
    private Long userId;

    /**
     * 禁言时长（秒）
     */
    @NonNull
    @JsonProperty("duration")
    private Long duration;
}
