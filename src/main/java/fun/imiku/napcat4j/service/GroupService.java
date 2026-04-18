package fun.imiku.napcat4j.service;

import com.mikuac.shiro.core.Bot;
import fun.imiku.napcat4j.component.ApiPostService;
import fun.imiku.napcat4j.component.NapCatApiPath;
import fun.imiku.napcat4j.dto.request.DelGroupAlbumMediaRequest;
import fun.imiku.napcat4j.dto.request.GetGroupMemberInfoRequest;
import fun.imiku.napcat4j.dto.response.DelGroupAlbumMediaResponse;
import fun.imiku.napcat4j.dto.response.GetGroupMemberInfoResponse;
import fun.imiku.napcat4j.dto.response.GetGroupMemberListResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

/**
 * 群组服务
 */
@Service
public class GroupService {
    private final ApiPostService apiPostService;

    public GroupService(ApiPostService apiPostService) {
        this.apiPostService = apiPostService;
    }

    /**
     * 获取群成员列表
     */
    public GetGroupMemberListResponse getGroupMemberList(Bot bot, Long groupId, Boolean noCache) throws IOException, InterruptedException {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("group_id", groupId);
        body.put("no_cache", noCache);

        HttpResponse<String> resp = apiPostService.postJson(NapCatApiPath.GET_GROUP_MEMBER_LIST, body);
        return ApiPostService.parseResponse(resp, GetGroupMemberListResponse.class);
    }

    /**
     * 异步获取群成员列表
     */
    public CompletableFuture<GetGroupMemberListResponse> getGroupMemberListAsync(Bot bot, Long groupId, Boolean noCache) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return getGroupMemberList(bot, groupId, noCache);
            } catch (IOException | InterruptedException e) {
                throw new CompletionException(e);
            }
        }, ApiPostService.VIRTUAL_THREAD_EXECUTOR);
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
        return CompletableFuture.supplyAsync(() -> {
            try {
                return getGroupMemberInfo(request);
            } catch (IOException | InterruptedException e) {
                throw new CompletionException(e);
            }
        }, ApiPostService.VIRTUAL_THREAD_EXECUTOR);
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
        return CompletableFuture.supplyAsync(() -> {
            try {
                return delGroupAlbumMedia(request);
            } catch (IOException | InterruptedException e) {
                throw new CompletionException(e);
            }
        }, ApiPostService.VIRTUAL_THREAD_EXECUTOR);
    }
}
