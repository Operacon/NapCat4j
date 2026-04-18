package fun.imiku.napcat4j.service;

import fun.imiku.napcat4j.component.ApiPostService;
import fun.imiku.napcat4j.component.NapCatApiPath;
import fun.imiku.napcat4j.dto.request.DelGroupAlbumMediaRequest;
import fun.imiku.napcat4j.dto.request.DoGroupAlbumCommentRequest;
import fun.imiku.napcat4j.dto.request.FriendPokeRequest;
import fun.imiku.napcat4j.dto.request.GetGroupMemberInfoRequest;
import fun.imiku.napcat4j.dto.request.GroupPokeRequest;
import fun.imiku.napcat4j.dto.request.SendPokeRequest;
import fun.imiku.napcat4j.dto.request.SetGroupAlbumMediaLikeRequest;
import fun.imiku.napcat4j.dto.request.SetGroupBanRequest;
import fun.imiku.napcat4j.dto.request.UploadImageToQunAlbumRequest;
import fun.imiku.napcat4j.dto.response.DelGroupAlbumMediaResponse;
import fun.imiku.napcat4j.dto.response.DoGroupAlbumCommentResponse;
import fun.imiku.napcat4j.dto.response.FriendPokeResponse;
import fun.imiku.napcat4j.dto.response.GetGroupAlbumMediaListResponse;
import fun.imiku.napcat4j.dto.response.GetGroupDetailInfoResponse;
import fun.imiku.napcat4j.dto.response.GetGroupInfoExResponse;
import fun.imiku.napcat4j.dto.response.GetGroupInfoResponse;
import fun.imiku.napcat4j.dto.response.GetGroupListResponse;
import fun.imiku.napcat4j.dto.response.GetGroupMemberInfoResponse;
import fun.imiku.napcat4j.dto.response.GetGroupMemberListResponse;
import fun.imiku.napcat4j.dto.response.GetQunAlbumListResponse;
import fun.imiku.napcat4j.dto.response.GroupPokeResponse;
import fun.imiku.napcat4j.dto.response.SendGroupSignResponse;
import fun.imiku.napcat4j.dto.response.SendPokeResponse;
import fun.imiku.napcat4j.dto.response.SetGroupAlbumMediaLikeResponse;
import fun.imiku.napcat4j.dto.response.SetGroupBanResponse;
import fun.imiku.napcat4j.dto.response.SetGroupSignResponse;
import fun.imiku.napcat4j.dto.response.SetGroupWholeBanResponse;
import fun.imiku.napcat4j.dto.response.UploadImageToQunAlbumResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * 群组服务
 */
@Service
@SuppressWarnings("unused")
public class GroupService {
    private final ApiPostService apiPostService;

    public GroupService(ApiPostService apiPostService) {
        this.apiPostService = apiPostService;
    }

    /**
     * 发送戳一戳
     */
    public GroupPokeResponse groupPoke(GroupPokeRequest request) throws IOException, InterruptedException {
        HttpResponse<String> resp = apiPostService.postJson(NapCatApiPath.GROUP_POKE, request);
        return ApiPostService.parseResponse(resp, GroupPokeResponse.class);
    }

    /**
     * 发送戳一戳
     */
    public FriendPokeResponse friendPoke(FriendPokeRequest request) throws IOException, InterruptedException {
        HttpResponse<String> resp = apiPostService.postJson(NapCatApiPath.FRIEND_POKE, request);
        return ApiPostService.parseResponse(resp, FriendPokeResponse.class);
    }

    /**
     * 发送戳一戳
     */
    public SendPokeResponse sendPoke(SendPokeRequest request) throws IOException, InterruptedException {
        HttpResponse<String> resp = apiPostService.postJson(NapCatApiPath.SEND_POKE, request);
        return ApiPostService.parseResponse(resp, SendPokeResponse.class);
    }

    /**
     * 获取群详细信息
     */
    public GetGroupDetailInfoResponse getGroupDetailInfo(Long groupId) throws IOException, InterruptedException {
        Map<String, Object> body = new HashMap<>();
        body.put("group_id", groupId);

        HttpResponse<String> resp = apiPostService.postJson(NapCatApiPath.GET_GROUP_DETAIL_INFO, body);
        return ApiPostService.parseResponse(resp, GetGroupDetailInfoResponse.class);
    }

    /**
     * 异步获取群详细信息
     */
    public CompletableFuture<GetGroupDetailInfoResponse> getGroupDetailInfoAsync(Long groupId) {
        return ApiPostService.supplyAsyncWithCatch(() -> getGroupDetailInfo(groupId));
    }

    /**
     * 获取群列表
     */
    public GetGroupListResponse getGroupList(Boolean noCache) throws IOException, InterruptedException {
        Map<String, Object> body = new HashMap<>();
        body.put("no_cache", noCache);

        HttpResponse<String> resp = apiPostService.postJson(NapCatApiPath.GET_GROUP_LIST, body);
        return ApiPostService.parseResponse(resp, GetGroupListResponse.class);
    }

    /**
     * 异步获取群列表
     */
    public CompletableFuture<GetGroupListResponse> getGroupListAsync(Boolean noCache) {
        return ApiPostService.supplyAsyncWithCatch(() -> getGroupList(noCache));
    }

    /**
     * 获取群信息
     */
    public GetGroupInfoResponse getGroupInfo(Long groupId) throws IOException, InterruptedException {
        Map<String, Object> body = new HashMap<>();
        body.put("group_id", groupId);

        HttpResponse<String> resp = apiPostService.postJson(NapCatApiPath.GET_GROUP_INFO, body);
        return ApiPostService.parseResponse(resp, GetGroupInfoResponse.class);
    }

    /**
     * 异步获取群信息
     */
    public CompletableFuture<GetGroupInfoResponse> getGroupInfoAsync(Long groupId) {
        return ApiPostService.supplyAsyncWithCatch(() -> getGroupInfo(groupId));
    }

    /**
     * 获取群详细信息（扩展）
     */
    public GetGroupInfoExResponse getGroupInfoEx(Long groupId) throws IOException, InterruptedException {
        Map<String, Object> body = new HashMap<>();
        body.put("group_id", groupId);

        HttpResponse<String> resp = apiPostService.postJson(NapCatApiPath.GET_GROUP_INFO_EX, body);
        return ApiPostService.parseResponse(resp, GetGroupInfoExResponse.class);
    }

    /**
     * 异步获取群详细信息（扩展）
     */
    public CompletableFuture<GetGroupInfoExResponse> getGroupInfoExAsync(Long groupId) {
        return ApiPostService.supplyAsyncWithCatch(() -> getGroupInfoEx(groupId));
    }

    /**
     * 群组禁言
     */
    public SetGroupBanResponse setGroupBan(SetGroupBanRequest request) throws IOException, InterruptedException {
        HttpResponse<String> resp = apiPostService.postJson(NapCatApiPath.SET_GROUP_BAN, request);
        return ApiPostService.parseResponse(resp, SetGroupBanResponse.class);
    }

    /**
     * 全员禁言
     */
    public SetGroupWholeBanResponse setGroupWholeBan(Long groupId, Object enable) throws IOException, InterruptedException {
        Map<String, Object> body = new HashMap<>();
        body.put("group_id", groupId);
        body.put("enable", enable);

        HttpResponse<String> resp = apiPostService.postJson(NapCatApiPath.SET_GROUP_WHOLE_BAN, body);
        return ApiPostService.parseResponse(resp, SetGroupWholeBanResponse.class);
    }

    /**
     * 获取群成员列表
     */
    public GetGroupMemberListResponse getGroupMemberList(Long groupId, Boolean noCache) throws IOException, InterruptedException {
        Map<String, Object> body = new HashMap<>();
        body.put("group_id", groupId);
        body.put("no_cache", noCache);

        HttpResponse<String> resp = apiPostService.postJson(NapCatApiPath.GET_GROUP_MEMBER_LIST, body);
        return ApiPostService.parseResponse(resp, GetGroupMemberListResponse.class);
    }

    /**
     * 异步获取群成员列表
     */
    public CompletableFuture<GetGroupMemberListResponse> getGroupMemberListAsync(Long groupId, Boolean noCache) {
        return ApiPostService.supplyAsyncWithCatch(() -> getGroupMemberList(groupId, noCache));
    }

    /**
     * 获取群成员信息
     */
    public GetGroupMemberInfoResponse getGroupMemberInfo(GetGroupMemberInfoRequest request) throws IOException, InterruptedException {
        HttpResponse<String> resp = apiPostService.postJson(NapCatApiPath.GET_GROUP_MEMBER_INFO, request);
        return ApiPostService.parseResponse(resp, GetGroupMemberInfoResponse.class);
    }

    /**
     * 异步获取群成员信息
     */
    public CompletableFuture<GetGroupMemberInfoResponse> getGroupMemberInfoAsync(GetGroupMemberInfoRequest request) {
        return ApiPostService.supplyAsyncWithCatch(() -> getGroupMemberInfo(request));
    }

    /**
     * 删除群相册媒体
     */
    public DelGroupAlbumMediaResponse delGroupAlbumMedia(DelGroupAlbumMediaRequest request) throws IOException, InterruptedException {
        HttpResponse<String> resp = apiPostService.postJson(NapCatApiPath.DEL_GROUP_ALBUM_MEDIA, request);
        return ApiPostService.parseResponse(resp, DelGroupAlbumMediaResponse.class);
    }

    /**
     * 异步删除群相册媒体
     */
    public CompletableFuture<DelGroupAlbumMediaResponse> delGroupAlbumMediaAsync(DelGroupAlbumMediaRequest request) {
        return ApiPostService.supplyAsyncWithCatch(() -> delGroupAlbumMedia(request));
    }

    /**
     * 点赞群相册媒体
     */
    public SetGroupAlbumMediaLikeResponse setGroupAlbumMediaLike(SetGroupAlbumMediaLikeRequest request) throws IOException, InterruptedException {
        HttpResponse<String> resp = apiPostService.postJson(NapCatApiPath.SET_GROUP_ALBUM_MEDIA_LIKE, request);
        return ApiPostService.parseResponse(resp, SetGroupAlbumMediaLikeResponse.class);
    }

    /**
     * 异步点赞群相册媒体
     */
    public CompletableFuture<SetGroupAlbumMediaLikeResponse> setGroupAlbumMediaLikeAsync(SetGroupAlbumMediaLikeRequest request) {
        return ApiPostService.supplyAsyncWithCatch(() -> setGroupAlbumMediaLike(request));
    }

    /**
     * 发表群相册评论
     */
    public DoGroupAlbumCommentResponse doGroupAlbumComment(DoGroupAlbumCommentRequest request) throws IOException, InterruptedException {
        HttpResponse<String> resp = apiPostService.postJson(NapCatApiPath.DO_GROUP_ALBUM_COMMENT, request);
        return ApiPostService.parseResponse(resp, DoGroupAlbumCommentResponse.class);
    }

    /**
     * 异步发表群相册评论
     */
    public CompletableFuture<DoGroupAlbumCommentResponse> doGroupAlbumCommentAsync(DoGroupAlbumCommentRequest request) {
        return ApiPostService.supplyAsyncWithCatch(() -> doGroupAlbumComment(request));
    }

    /**
     * 获取群相册媒体列表
     */
    public GetGroupAlbumMediaListResponse getGroupAlbumMediaList(Long groupId, String albumId, String attachInfo) throws IOException, InterruptedException {
        Map<String, Object> body = new HashMap<>();
        body.put("group_id", groupId);
        body.put("album_id", albumId);
        body.put("attach_info", attachInfo);

        HttpResponse<String> resp = apiPostService.postJson(NapCatApiPath.GET_GROUP_ALBUM_MEDIA_LIST, body);
        return ApiPostService.parseResponse(resp, GetGroupAlbumMediaListResponse.class);
    }

    /**
     * 异步获取群相册媒体列表
     */
    public CompletableFuture<GetGroupAlbumMediaListResponse> getGroupAlbumMediaListAsync(Long groupId, String albumId, String attachInfo) {
        return ApiPostService.supplyAsyncWithCatch(() -> getGroupAlbumMediaList(groupId, albumId, attachInfo));
    }

    /**
     * 获取群相册列表
     */
    public GetQunAlbumListResponse getQunAlbumList(Long groupId, String attachInfo) throws IOException, InterruptedException {
        Map<String, Object> body = new HashMap<>();
        body.put("group_id", groupId);
        body.put("attach_info", attachInfo);

        HttpResponse<String> resp = apiPostService.postJson(NapCatApiPath.GET_QUN_ALBUM_LIST, body);
        return ApiPostService.parseResponse(resp, GetQunAlbumListResponse.class);
    }

    /**
     * 异步获取群相册列表
     */
    public CompletableFuture<GetQunAlbumListResponse> getQunAlbumListAsync(Long groupId, String attachInfo) {
        return ApiPostService.supplyAsyncWithCatch(() -> getQunAlbumList(groupId, attachInfo));
    }

    /**
     * 上传图片到群相册
     */
    public UploadImageToQunAlbumResponse uploadImageToQunAlbum(UploadImageToQunAlbumRequest request) throws IOException, InterruptedException {
        HttpResponse<String> resp = apiPostService.postJson(NapCatApiPath.UPLOAD_IMAGE_TO_QUN_ALBUM, request);
        return ApiPostService.parseResponse(resp, UploadImageToQunAlbumResponse.class);
    }

    /**
     * 异步上传图片到群相册
     */
    public CompletableFuture<UploadImageToQunAlbumResponse> uploadImageToQunAlbumAsync(UploadImageToQunAlbumRequest request) {
        return ApiPostService.supplyAsyncWithCatch(() -> uploadImageToQunAlbum(request));
    }

    /**
     * 群打卡
     */
    public SetGroupSignResponse setGroupSign(Long groupId) throws IOException, InterruptedException {
        Map<String, Object> body = new HashMap<>();
        body.put("group_id", groupId);

        HttpResponse<String> resp = apiPostService.postJson(NapCatApiPath.SET_GROUP_SIGN, body);
        return ApiPostService.parseResponse(resp, SetGroupSignResponse.class);
    }

    /**
     * 异步群打卡
     */
    public CompletableFuture<SetGroupSignResponse> setGroupSignAsync(Long groupId) {
        return ApiPostService.supplyAsyncWithCatch(() -> setGroupSign(groupId));
    }

    /**
     * 发送群打卡
     */
    public SendGroupSignResponse sendGroupSign(Long groupId) throws IOException, InterruptedException {
        Map<String, Object> body = new HashMap<>();
        body.put("group_id", groupId);

        HttpResponse<String> resp = apiPostService.postJson(NapCatApiPath.SEND_GROUP_SIGN, body);
        return ApiPostService.parseResponse(resp, SendGroupSignResponse.class);
    }

    /**
     * 异步发送群打卡
     */
    public CompletableFuture<SendGroupSignResponse> sendGroupSignAsync(Long groupId) {
        return ApiPostService.supplyAsyncWithCatch(() -> sendGroupSign(groupId));
    }
}
