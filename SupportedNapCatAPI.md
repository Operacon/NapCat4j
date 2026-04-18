对 [NapCat API](https://napcat.apifox.cn) 的实现。

部分 API 功能是重复的，且有部分 API 文档字段定义有一些问题（按 [NatCat 的 OpenAPI](https://napcat.apifox.cn/5430207m0)
整理）。请以 JavaDoc 为准。

绝大部分可能耗时的 API 调用都提供了基于虚拟线程的异步版本。然而基于 `@PrivateMessageListener` 等注解被 NapCat4j
自动调用的方法，本身执行时已经在虚拟线程上了；除非有特殊需求，大多数时候用同步版本的 API 方法即可。

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

#### 发送戳一戳

- API 路径：`/friend_poke`
- API 文档链接：[发送戳一戳](https://napcat.apifox.cn/226659255e0.md)
- 所在 Service：`GroupService`
- 方法：`friendPoke()`

#### 发送戳一戳

- API 路径：`/send_poke`
- API 文档链接：[发送戳一戳](https://napcat.apifox.cn/250286923e0.md)
- 所在 Service：`GroupService`
- 方法：`sendPoke()`

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

#### 获取群信息

- API 路径：`/get_group_info`
- API 文档链接：[获取群信息](https://napcat.apifox.cn/226656979e0.md)
- 所在 Service：`GroupService`
- 方法：`getGroupInfo()` / `getGroupInfoAsync()`

#### 获取群详细信息 (扩展)

- API 路径：`/get_group_info_ex`
- API 文档链接：[获取群详细信息 (扩展)](https://napcat.apifox.cn/226659229e0.md)
- 所在 Service：`GroupService`
- 方法：`getGroupInfoEx()` / `getGroupInfoExAsync()`

#### 群组禁言

- API 路径：`/set_group_ban`
- API 文档链接：[群组禁言](https://napcat.apifox.cn/226656791e0.md)
- 所在 Service：`GroupService`
- 方法：`setGroupBan()`

#### 全员禁言

- API 路径：`/set_group_whole_ban`
- API 文档链接：[全员禁言](https://napcat.apifox.cn/226656802e0.md)
- 所在 Service：`GroupService`
- 方法：`setGroupWholeBan()`

#### 点赞群相册媒体

- API 路径：`/set_group_album_media_like`
- API 文档链接：[点赞群相册媒体](https://napcat.apifox.cn/395457331e0.md)
- 所在 Service：`GroupService`
- 方法：`setGroupAlbumMediaLike()` / `setGroupAlbumMediaLikeAsync()`

#### 发表群相册评论

- API 路径：`/do_group_album_comment`
- API 文档链接：[发表群相册评论](https://napcat.apifox.cn/395458911e0.md)
- 所在 Service：`GroupService`
- 方法：`doGroupAlbumComment()` / `doGroupAlbumCommentAsync()`

#### 获取群相册媒体列表

- API 路径：`/get_group_album_media_list`
- API 文档链接：[获取群相册媒体列表](https://napcat.apifox.cn/395459066e0.md)
- 所在 Service：`GroupService`
- 方法：`getGroupAlbumMediaList()` / `getGroupAlbumMediaListAsync()`

#### 获取群相册列表

- API 路径：`/get_qun_album_list`
- API 文档链接：[获取群相册列表](https://napcat.apifox.cn/395460287e0.md)
- 所在 Service：`GroupService`
- 方法：`getQunAlbumList()` / `getQunAlbumListAsync()`

#### 上传图片到群相册

- API 路径：`/upload_image_to_qun_album`
- API 文档链接：[上传图片到群相册](https://napcat.apifox.cn/395459739e0.md)
- 所在 Service：`GroupService`
- 方法：`uploadImageToQunAlbum()` / `uploadImageToQunAlbumAsync()`

#### 群打卡

- API 路径：`/set_group_sign`
- API 文档链接：[群打卡](https://napcat.apifox.cn/226659329e0.md)
- 所在 Service：`GroupService`
- 方法：`setGroupSign()` / `setGroupSignAsync()`

#### 群打卡

- API 路径：`/send_group_sign`
- API 文档链接：[群打卡](https://napcat.apifox.cn/230897177e0.md)
- 所在 Service：`GroupService`
- 方法：`sendGroupSign()` / `sendGroupSignAsync()`

#### 发送消息

- API 路径：`/send_msg`
- API 文档链接：[发送消息](https://napcat.apifox.cn/226656652e0.md)
- 所在 Service：`MessageService`
- 方法：`sendMsg()` / `sendMsgAsync()`

#### 撤回消息

- API 路径：`/delete_msg`
- API 文档链接：[撤回消息](https://napcat.apifox.cn/226919954e0.md)
- 所在 Service：`MessageService`
- 方法：`deleteMsg()`

#### 获取消息

- API 路径：`/get_msg`
- API 文档链接：[获取消息](https://napcat.apifox.cn/226656707e0.md)
- 所在 Service：`MessageService`
- 方法：`getMsg()` / `getMsgAsync()`

#### 发送私聊消息

- API 路径：`/send_private_msg`
- API 文档链接：[发送私聊消息](https://napcat.apifox.cn/226656553e0.md)
- 所在 Service：`MessageService`
- 方法：`sendPrivateMsg()` / `sendPrivateMsgAsync()`

#### 转发单条消息（好友）

- API 路径：`/forward_friend_single_msg`
- API 文档链接：[转发单条消息](https://napcat.apifox.cn/226659051e0.md)
- 所在 Service：`MessageService`
- 方法：`forwardFriendSingleMsg()` / `forwardFriendSingleMsgAsync()`

#### 转发单条消息（群）

- API 路径：`/forward_group_single_msg`
- API 文档链接：[转发单条消息](https://napcat.apifox.cn/226659074e0.md)
- 所在 Service：`MessageService`
- 方法：`forwardGroupSingleMsg()` / `forwardGroupSingleMsgAsync()`

#### 标记群聊已读

- API 路径：`/mark_group_msg_as_read`
- API 文档链接：[标记群聊已读](https://napcat.apifox.cn/226659167e0.md)
- 所在 Service：`MessageService`
- 方法：`markGroupMsgAsRead()`

#### 标记私聊已读

- API 路径：`/mark_private_msg_as_read`
- API 文档链接：[标记私聊已读](https://napcat.apifox.cn/226659165e0.md)
- 所在 Service：`MessageService`
- 方法：`markPrivateMsgAsRead()`

#### 标记消息已读（Go-CQHTTP）

- API 路径：`/mark_msg_as_read`
- API 文档链接：[标记消息已读 (Go-CQHTTP)](https://napcat.apifox.cn/226657389e0.md)
- 所在 Service：`MessageService`
- 方法：`markMsgAsRead()`

#### 标记所有消息已读

- API 路径：`/_mark_all_as_read`
- API 文档链接：[标记所有消息已读](https://napcat.apifox.cn/226659194e0.md)
- 所在 Service：`MessageService`
- 方法：`markAllAsRead()`
