package fun.imiku.napcat4j.dto.schema;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/**
 * 文件消息段基础数据
 */
@Data
@NoArgsConstructor
public class FileBaseData {

    /**
     * 文件路径/URL/file:///
     */
    @NonNull
    @JsonProperty("file")
    private String file;

    /**
     * 文件路径
     */
    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("path")
    private String path;

    /**
     * 文件 URL
     */
    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("url")
    private String url;

    /**
     * 文件名
     */
    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("name")
    private String name;

    /**
     * 缩略图
     */
    @Nullable
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("thumb")
    private String thumb;
}
