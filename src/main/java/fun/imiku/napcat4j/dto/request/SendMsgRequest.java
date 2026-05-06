package fun.imiku.napcat4j.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * 发送消息请求
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendMsgRequest {

    /**
     * 消息类型（private/group）
     */
    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("message_type")
    private String messageType;

    /**
     * 用户 QQ
     */
    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("user_id")
    private Long userId;

    /**
     * 群号
     */
    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("group_id")
    private Long groupId;

    /**
     * 消息内容（OB11MessageMixType）
     * <p>
     * List of OB11MessageData, OB11MessageData, String, List of ArrayMsg, ArrayMsg
     * <p>
     * 推荐使用 Shiro 的 ArrayMsg 及其 builder 构建
     */
    @NonNull
    @JsonProperty("message")
    private Object message;

    /**
     * 是否作为纯文本发送
     */
    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("auto_escape")
    private Boolean autoEscape;

    /**
     * 合并转发来源
     */
    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("source")
    private String source;

    /**
     * 合并转发新闻
     */
    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("news")
    private List<NewsItem> news;

    /**
     * 合并转发摘要
     */
    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("summary")
    private String summary;

    /**
     * 合并转发提示
     */
    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("prompt")
    private String prompt;

    /**
     * 自定义发送超时（毫秒）
     */
    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("timeout")
    private Double timeout;

    /**
     * 合并转发新闻项
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NewsItem {

        /**
         * 新闻文本
         */
        @NonNull
        @JsonProperty("text")
        private String text;
    }
}
