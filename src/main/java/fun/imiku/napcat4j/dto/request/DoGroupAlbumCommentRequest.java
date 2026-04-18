package fun.imiku.napcat4j.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NonNull;

/**
 * 发表群相册评论请求
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoGroupAlbumCommentRequest {

    /**
     * 群号
     */
    @NonNull
    @JsonProperty("group_id")
    private Long groupId;

    /**
     * 相册 ID
     */
    @NonNull
    @JsonProperty("album_id")
    private String albumId;

    /**
     * 图片 ID
     */
    @NonNull
    @JsonProperty("lloc")
    private String lloc;

    /**
     * 评论内容
     */
    @NonNull
    @JsonProperty("content")
    private String content;
}
