package fun.imiku.napcat4j.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import fun.imiku.napcat4j.dto.schema.BaseResponse;
import fun.imiku.napcat4j.dto.schema.OB11MessageData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * 获取消息响应
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GetMsgResponse extends BaseResponse<GetMsgResponse.GetMsgData> {

    /**
     * 获取消息业务数据
     */
    @Data
    public static class GetMsgData {

        /**
         * 发送时间
         */
        @NonNull
        @JsonProperty("time")
        private Long time;

        /**
         * 消息类型
         */
        @NonNull
        @JsonProperty("message_type")
        private String messageType;

        /**
         * 消息 ID
         */
        @NonNull
        @JsonProperty("message_id")
        private Long messageId;

        /**
         * 真实 ID
         */
        @NonNull
        @JsonProperty("real_id")
        private Long realId;

        /**
         * 消息序号
         */
        @NonNull
        @JsonProperty("message_seq")
        private Long messageSeq;

        /**
         * 发送者
         */
        @NonNull
        @JsonProperty("sender")
        private Long sender;

        /**
         * 消息内容
         */
        @NonNull
        @JsonProperty("message")
        private OB11MessageData message;

        /**
         * 原始消息内容
         */
        @NonNull
        @JsonProperty("raw_message")
        private String rawMessage;

        /**
         * 字体
         */
        @NonNull
        @JsonProperty("font")
        private Long font;

        /**
         * 群号
         */
        @Nullable
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("group_id")
        private Long groupId;

        /**
         * 发送者 QQ 号
         */
        @NonNull
        @JsonProperty("user_id")
        private Long userId;

        /**
         * 表情回应列表
         */
        @Nullable
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("emoji_likes_list")
        private List<String> emojiLikesList;
    }
}
