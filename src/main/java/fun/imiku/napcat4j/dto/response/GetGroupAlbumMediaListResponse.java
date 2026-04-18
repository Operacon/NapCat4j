package fun.imiku.napcat4j.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import fun.imiku.napcat4j.dto.schema.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * 获取群相册媒体列表响应
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GetGroupAlbumMediaListResponse extends BaseResponse<GetGroupAlbumMediaListResponse.GetGroupAlbumMediaListData> {

    /**
     * 获取群相册媒体列表业务数据
     */
    @Data
    public static class GetGroupAlbumMediaListData {

        /**
         * 媒体列表
         */
        @Nullable
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("media_list")
        private List<MediaItem> mediaList;
    }

    /**
     * 群相册媒体信息
     */
    @Data
    public static class MediaItem {

        /**
         * 媒体 ID
         */
        @Nullable
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("media_id")
        private String mediaId;

        /**
         * 媒体 URL
         */
        @Nullable
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("url")
        private String url;
    }
}
