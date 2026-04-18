package fun.imiku.napcat4j.dto.schema;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/**
 * 自定义音乐消息段
 */
@Data
public class OB11MessageCustomMusic implements OB11MessageData {

    @NonNull
    @JsonProperty("type")
    private String type;

    @NonNull
    @JsonProperty("data")
    private CustomMusicData data;

    @Data
    public static class CustomMusicData {
        /**
         * 音乐平台类型
         */
        @NonNull
        @JsonProperty("type")
        private String type;

        /**
         * 音乐ID（文档为null类型）
         */
        @NonNull
        @JsonProperty("id")
        private Object id;

        /**
         * 点击后跳转URL
         */
        @NonNull
        @JsonProperty("url")
        private String url;

        /**
         * 音频 URL
         */
        @Nullable
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("audio")
        private String audio;

        /**
         * 音乐标题
         */
        @Nullable
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("title")
        private String title;

        /**
         * 封面图片 URL
         */
        @NonNull
        @JsonProperty("image")
        private String image;

        /**
         * 音乐简介
         */
        @Nullable
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("content")
        private String content;
    }
}
