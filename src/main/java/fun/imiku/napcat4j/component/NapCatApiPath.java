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
    DEL_GROUP_ALBUM_MEDIA("/del_group_album_media");

    private final String path;

    NapCatApiPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return path;
    }
}
