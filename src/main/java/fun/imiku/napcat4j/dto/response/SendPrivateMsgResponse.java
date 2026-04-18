package fun.imiku.napcat4j.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import fun.imiku.napcat4j.dto.schema.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/**
 * 发送私聊消息响应
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SendPrivateMsgResponse extends BaseResponse<SendPrivateMsgResponse.SendPrivateMsgData> {

    /**
     * 发送私聊消息业务数据
     */
    @Data
    public static class SendPrivateMsgData {

        /**
         * 消息 ID
         */
        @NonNull
        @JsonProperty("message_id")
        private Long messageId;

        /**
         * 转发消息的 res_id
         */
        @Nullable
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("res_id")
        private String resId;

        /**
         * 转发消息的 forward_id
         */
        @Nullable
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("forward_id")
        private String forwardId;
    }
}
