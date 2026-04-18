package fun.imiku.napcat4j.dto.response;

import fun.imiku.napcat4j.dto.schema.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 全员禁言响应
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SetGroupWholeBanResponse extends BaseResponse<Void> {
}
