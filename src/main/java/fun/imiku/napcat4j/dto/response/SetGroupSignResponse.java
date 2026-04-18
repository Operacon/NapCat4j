package fun.imiku.napcat4j.dto.response;

import fun.imiku.napcat4j.dto.schema.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 群打卡响应
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SetGroupSignResponse extends BaseResponse<Void> {
}
