package fun.imiku.napcat4j.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NonNull;

/**
 * 上传图片到群相册请求
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadImageToQunAlbumRequest {

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
     * 相册名称
     */
    @NonNull
    @JsonProperty("album_name")
    private String albumName;

    /**
     * 图片路径、URL 或 Base64
     */
    @NonNull
    @JsonProperty("file")
    private String file;
}
