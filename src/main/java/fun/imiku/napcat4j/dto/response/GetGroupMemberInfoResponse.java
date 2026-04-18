package fun.imiku.napcat4j.dto.response;

import fun.imiku.napcat4j.dto.schema.BaseResponse;
import fun.imiku.napcat4j.dto.schema.OB11GroupMember;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取群成员信息响应
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GetGroupMemberInfoResponse extends BaseResponse<OB11GroupMember> {
}
