package fun.imiku.napcat4j.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import fun.imiku.napcat4j.dto.schema.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jspecify.annotations.Nullable;

/**
 * 上传图片到群相册响应
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UploadImageToQunAlbumResponse extends BaseResponse<UploadImageToQunAlbumResponse.UploadImageToQunAlbumData> {

    /**
     * 上传图片到群相册业务数据
     */
    @Data
    public static class UploadImageToQunAlbumData {

        /**
         * 上传结果
         */
        @Nullable
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("result")
        private Object result;
    }
}
