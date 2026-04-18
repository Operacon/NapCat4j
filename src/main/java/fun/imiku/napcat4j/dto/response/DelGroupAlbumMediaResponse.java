package fun.imiku.napcat4j.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import fun.imiku.napcat4j.dto.schema.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jspecify.annotations.Nullable;

import java.util.Map;

/**
 * 删除群相册媒体响应
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DelGroupAlbumMediaResponse extends BaseResponse<DelGroupAlbumMediaResponse.DelGroupAlbumMediaData> {

    /**
     * 删除群相册媒体业务数据
     */
    @Data
    public static class DelGroupAlbumMediaData {

        /**
         * 扩展结果（文档示例中为对象）
         */
        @Nullable
        @JsonProperty("result")
        private Map<String, Object> result;
    }
}
