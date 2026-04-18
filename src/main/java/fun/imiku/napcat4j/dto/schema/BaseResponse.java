package fun.imiku.napcat4j.dto.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/**
 * NapCat 通用响应结构
 *
 * @param <T> 业务数据类型
 */
@Data
@NoArgsConstructor
public class BaseResponse<T> {

    /**
     * 状态（ok/failed）
     */
    @NonNull
    @JsonProperty("status")
    private String status;

    /**
     * 返回码
     */
    @NonNull
    @JsonProperty("retcode")
    private Integer retcode;

    /**
     * 业务数据
     */
    @Nullable
    @JsonProperty("data")
    private T data;

    /**
     * 消息
     */
    @Nullable
    @JsonProperty("message")
    private String message;

    /**
     * 提示
     */
    @Nullable
    @JsonProperty("wording")
    private String wording;

    /**
     * 响应流类型
     */
    @Nullable
    @JsonProperty("stream")
    private String stream;
}
