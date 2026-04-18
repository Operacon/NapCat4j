package fun.imiku.napcat4j.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import fun.imiku.napcat4j.dto.schema.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * 获取群相册列表响应
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GetQunAlbumListResponse extends BaseResponse<GetQunAlbumListResponse.GetQunAlbumListData> {

    /**
     * 获取群相册列表业务数据
     */
    @Data
    public static class GetQunAlbumListData {

        /**
         * 群相册列表
         */
        @NonNull
        @JsonProperty("album_list")
        private List<AlbumItem> albumList;

        /**
         * 分页附加信息
         */
        @NonNull
        @JsonProperty("attach_info")
        private String attachInfo;

        /**
         * 是否有更多数据
         */
        @NonNull
        @JsonProperty("has_more")
        private Boolean hasMore;
    }

    /**
     * 群相册信息
     */
    @Data
    public static class AlbumItem {

        /**
         * 相册 ID
         */
        @Nullable
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("album_id")
        private String albumId;

        /**
         * 相册名称
         */
        @Nullable
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("album_name")
        private String albumName;

        /**
         * 封面 URL
         */
        @Nullable
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("cover_url")
        private String coverUrl;

        /**
         * 创建时间
         */
        @Nullable
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("create_time")
        private Long createTime;
    }
}
