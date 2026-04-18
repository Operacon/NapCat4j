package fun.imiku.napcat4j.dto.schema;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * 合并转发消息节点
 */
@Data
public class OB11MessageNode implements OB11MessageData {

    @NonNull
    @JsonProperty("type")
    private String type;

    @NonNull
    @JsonProperty("data")
    private NodeData data;

    @Data
    public static class NodeData {
        /**
         * 转发消息ID
         */
        @Nullable
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("id")
        private String id;

        /**
         * 发送者QQ号（number|string）
         */
        @Nullable
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("user_id")
        private Object userId;

        /**
         * 发送者QQ号（兼容go-cqhttp，number|string）
         */
        @Nullable
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("uin")
        private Object uin;

        /**
         * 发送者昵称
         */
        @NonNull
        @JsonProperty("nickname")
        private String nickname;

        /**
         * 发送者昵称（兼容go-cqhttp）
         */
        @Nullable
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("name")
        private String name;

        /**
         * 消息内容（OB11MessageMixType）
         */
        @NonNull
        @JsonProperty("content")
        private OB11MessageData content;

        /**
         * 消息来源
         */
        @Nullable
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("source")
        private String source;

        /**
         * 新闻列表
         */
        @Nullable
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("news")
        private List<NewsItem> news;

        /**
         * 摘要
         */
        @Nullable
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("summary")
        private String summary;

        /**
         * 提示
         */
        @Nullable
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("prompt")
        private String prompt;

        /**
         * 时间
         */
        @Nullable
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("time")
        private String time;
    }

    @Data
    public static class NewsItem {
        /**
         * 新闻文本
         */
        @NonNull
        @JsonProperty("text")
        private String text;
    }
}
