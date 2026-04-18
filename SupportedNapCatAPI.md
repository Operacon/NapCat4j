对 [NapCat API](https://napcat.apifox.cn) 的实现。

## 已实现的 NapCat API

- `` 删除群相册媒体

#### 删除群相册媒体

- API 路径：`/del_group_album_media`
- API 文档链接：[删除群相册媒体](https://napcat.apifox.cn/395455119e0.md)
- 所在 Service：`GroupService`
- 方法：`delGroupAlbumMedia()` / `delGroupAlbumMediaAsync()`

#### 获取群成员列表

- API 路径：`/get_group_member_list`
- API 文档链接：[获取群成员列表](https://napcat.apifox.cn/226657034e0.md)
- 所在 Service：`GroupService`
- 方法：`getGroupMemberList()` / `getGroupMemberListAsync()`

#### 获取群成员信息

- API 路径：`/get_group_member_info`
- API 文档链接：[获取群成员信息](https://napcat.apifox.cn/226657019e0.md)
- 所在 Service：`GroupService`
- 方法：`getGroupMemberInfo()` / `getGroupMemberInfoAsync()`

#### 发送戳一戳
- API 路径：`/group_poke`
- API 文档链接：[发送戳一戳](https://napcat.apifox.cn/226659265e0.md)
- 所在 Service：`GroupService`
- 方法：`groupPoke()`

#### 获取群详细信息
- API 路径：`/get_group_detail_info`
- API 文档链接：[获取群详细信息](https://napcat.apifox.cn/307180859e0.md)
- 所在 Service：`GroupService`
- 方法：`getGroupDetailInfo()` / `getGroupDetailInfoAsync()`

#### 获取群列表
- API 路径：`/get_group_list`
- API 文档链接：[获取群列表](https://napcat.apifox.cn/226656992e0.md)
- 所在 Service：`GroupService`
- 方法：`getGroupList()` / `getGroupListAsync()`
