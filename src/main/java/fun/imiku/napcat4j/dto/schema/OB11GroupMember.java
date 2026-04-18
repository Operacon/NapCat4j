package fun.imiku.napcat4j.dto.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/**
 * OneBot 11 群成员信息
 */
@Data
public class OB11GroupMember {

    /**
     * 群号
     */
    @NonNull
    @JsonProperty("group_id")
    private Long groupId;

    /**
     * QQ 号
     */
    @NonNull
    @JsonProperty("user_id")
    private Long userId;

    /**
     * 昵称
     */
    @NonNull
    @JsonProperty("nickname")
    private String nickname;

    /**
     * 名片
     */
    @Nullable
    @JsonProperty("card")
    private String card;

    /**
     * 性别
     */
    @Nullable
    @JsonProperty("sex")
    private String sex;

    /**
     * 年龄
     */
    @Nullable
    @JsonProperty("age")
    private Integer age;

    /**
     * 入群时间戳
     */
    @Nullable
    @JsonProperty("join_time")
    private Long joinTime;

    /**
     * 最后发言时间戳
     */
    @Nullable
    @JsonProperty("last_sent_time")
    private Long lastSentTime;

    /**
     * 等级
     */
    @Nullable
    @JsonProperty("level")
    private String level;

    /**
     * QQ 等级
     */
    @Nullable
    @JsonProperty("qq_level")
    private Integer qqLevel;

    /**
     * 角色（owner/admin/member）
     */
    @Nullable
    @JsonProperty("role")
    private String role;

    /**
     * 头衔
     */
    @Nullable
    @JsonProperty("title")
    private String title;

    /**
     * 地区
     */
    @Nullable
    @JsonProperty("area")
    private String area;

    /**
     * 是否不良记录
     */
    @Nullable
    @JsonProperty("unfriendly")
    private Boolean unfriendly;

    /**
     * 头衔过期时间
     */
    @Nullable
    @JsonProperty("title_expire_time")
    private Long titleExpireTime;

    /**
     * 是否允许修改名片
     */
    @Nullable
    @JsonProperty("card_changeable")
    private Boolean cardChangeable;

    /**
     * 禁言截止时间戳
     */
    @Nullable
    @JsonProperty("shut_up_timestamp")
    private Long shutUpTimestamp;

    /**
     * 是否为机器人
     */
    @Nullable
    @JsonProperty("is_robot")
    private Boolean isRobot;

    /**
     * Q 龄
     */
    @Nullable
    @JsonProperty("qage")
    private Integer qage;
}
