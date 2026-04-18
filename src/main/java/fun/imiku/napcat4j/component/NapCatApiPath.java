package fun.imiku.napcat4j.component;

public enum NapCatApiPath {
    GET_GROUP_MEMBER_LIST("/get_group_member_list"),
    GET_GROUP_MEMBER_INFO("/get_group_member_info"),
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
