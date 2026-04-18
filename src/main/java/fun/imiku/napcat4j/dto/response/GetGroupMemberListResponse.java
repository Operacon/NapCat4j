package fun.imiku.napcat4j.dto.response;

import fun.imiku.napcat4j.dto.schema.BaseResponse;
import fun.imiku.napcat4j.dto.schema.OB11GroupMember;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 获取群成员列表响应
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GetGroupMemberListResponse extends BaseResponse<List<OB11GroupMember>> {
}
