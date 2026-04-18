package fun.imiku.napcat4j.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NonNull;

/**
 * 删除群相册媒体请求
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DelGroupAlbumMediaRequest {

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
     * 媒体 ID（lloc）
     */
    @NonNull
    @JsonProperty("lloc")
    private String lloc;
}
