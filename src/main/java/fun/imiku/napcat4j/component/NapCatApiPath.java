package fun.imiku.napcat4j.component;

public enum NapCatApiPath {
    GROUP_POKE("/group_poke"),
    FRIEND_POKE("/friend_poke"),
    SEND_POKE("/send_poke"),
    GET_GROUP_DETAIL_INFO("/get_group_detail_info"),
    GET_GROUP_LIST("/get_group_list"),
    GET_GROUP_INFO("/get_group_info"),
    GET_GROUP_INFO_EX("/get_group_info_ex"),
    GET_GROUP_MEMBER_LIST("/get_group_member_list"),
    GET_GROUP_MEMBER_INFO("/get_group_member_info"),
    SET_GROUP_BAN("/set_group_ban"),
    SET_GROUP_WHOLE_BAN("/set_group_whole_ban"),
    DEL_GROUP_ALBUM_MEDIA("/del_group_album_media"),
    SET_GROUP_ALBUM_MEDIA_LIKE("/set_group_album_media_like"),
    DO_GROUP_ALBUM_COMMENT("/do_group_album_comment"),
    GET_GROUP_ALBUM_MEDIA_LIST("/get_group_album_media_list"),
    GET_QUN_ALBUM_LIST("/get_qun_album_list"),
    UPLOAD_IMAGE_TO_QUN_ALBUM("/upload_image_to_qun_album"),
    SET_GROUP_SIGN("/set_group_sign"),
    SEND_GROUP_SIGN("/send_group_sign"),
    FORWARD_FRIEND_SINGLE_MSG("/forward_friend_single_msg"),
    FORWARD_GROUP_SINGLE_MSG("/forward_group_single_msg"),
    MARK_GROUP_MSG_AS_READ("/mark_group_msg_as_read"),
    MARK_PRIVATE_MSG_AS_READ("/mark_private_msg_as_read"),
    GET_MSG("/get_msg"),
    SEND_PRIVATE_MSG("/send_private_msg"),
    SEND_MSG("/send_msg"),
    DELETE_MSG("/delete_msg"),
    MARK_MSG_AS_READ("/mark_msg_as_read"),
    MARK_ALL_AS_READ("/_mark_all_as_read");

    private final String path;

    NapCatApiPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return path;
    }
}
