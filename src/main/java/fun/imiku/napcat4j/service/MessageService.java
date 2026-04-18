package fun.imiku.napcat4j.service;

import fun.imiku.napcat4j.component.ApiPostService;
import fun.imiku.napcat4j.component.NapCatApiPath;
import fun.imiku.napcat4j.dto.request.SendMsgRequest;
import fun.imiku.napcat4j.dto.request.SendPrivateMsgRequest;
import fun.imiku.napcat4j.dto.response.DeleteMsgResponse;
import fun.imiku.napcat4j.dto.response.ForwardFriendSingleMsgResponse;
import fun.imiku.napcat4j.dto.response.ForwardGroupSingleMsgResponse;
import fun.imiku.napcat4j.dto.response.GetMsgResponse;
import fun.imiku.napcat4j.dto.response.MarkAllAsReadResponse;
import fun.imiku.napcat4j.dto.response.MarkGroupMsgAsReadResponse;
import fun.imiku.napcat4j.dto.response.MarkMsgAsReadResponse;
import fun.imiku.napcat4j.dto.response.MarkPrivateMsgAsReadResponse;
import fun.imiku.napcat4j.dto.response.SendMsgResponse;
import fun.imiku.napcat4j.dto.response.SendPrivateMsgResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * 消息服务
 */
@Service
@SuppressWarnings("unused")
public class MessageService {

    private final ApiPostService apiPostService;

    public MessageService(ApiPostService apiPostService) {
        this.apiPostService = apiPostService;
    }

    /**
     * 发送消息
     */
    public SendMsgResponse sendMsg(SendMsgRequest request) throws IOException, InterruptedException {
        HttpResponse<String> resp = apiPostService.postJson(NapCatApiPath.SEND_MSG, request);
        return ApiPostService.parseResponse(resp, SendMsgResponse.class);
    }

    /**
     * 异步发送消息
     */
    public CompletableFuture<SendMsgResponse> sendMsgAsync(SendMsgRequest request) {
        return ApiPostService.supplyAsyncWithCatch(() -> sendMsg(request));
    }

    /**
     * 发送私聊消息
     */
    public SendPrivateMsgResponse sendPrivateMsg(SendPrivateMsgRequest request) throws IOException, InterruptedException {
        HttpResponse<String> resp = apiPostService.postJson(NapCatApiPath.SEND_PRIVATE_MSG, request);
        return ApiPostService.parseResponse(resp, SendPrivateMsgResponse.class);
    }

    /**
     * 异步发送私聊消息
     */
    public CompletableFuture<SendPrivateMsgResponse> sendPrivateMsgAsync(SendPrivateMsgRequest request) {
        return ApiPostService.supplyAsyncWithCatch(() -> sendPrivateMsg(request));
    }

    /**
     * 获取消息
     */
    public GetMsgResponse getMsg(Object messageId) throws IOException, InterruptedException {
        Map<String, Object> body = new HashMap<>();
        body.put("message_id", messageId);

        HttpResponse<String> resp = apiPostService.postJson(NapCatApiPath.GET_MSG, body);
        return ApiPostService.parseResponse(resp, GetMsgResponse.class);
    }

    /**
     * 异步获取消息
     */
    public CompletableFuture<GetMsgResponse> getMsgAsync(Object messageId) {
        return ApiPostService.supplyAsyncWithCatch(() -> getMsg(messageId));
    }

    /**
     * 撤回消息
     */
    public DeleteMsgResponse deleteMsg(Object messageId) throws IOException, InterruptedException {
        Map<String, Object> body = new HashMap<>();
        body.put("message_id", messageId);

        HttpResponse<String> resp = apiPostService.postJson(NapCatApiPath.DELETE_MSG, body);
        return ApiPostService.parseResponse(resp, DeleteMsgResponse.class);
    }

    /**
     * 转发好友单条消息
     */
    public ForwardFriendSingleMsgResponse forwardFriendSingleMsg(Object messageId, Long groupId, Long userId) throws IOException, InterruptedException {
        Map<String, Object> body = new HashMap<>();
        body.put("message_id", messageId);
        body.put("group_id", groupId);
        body.put("user_id", userId);

        HttpResponse<String> resp = apiPostService.postJson(NapCatApiPath.FORWARD_FRIEND_SINGLE_MSG, body);
        return ApiPostService.parseResponse(resp, ForwardFriendSingleMsgResponse.class);
    }

    /**
     * 异步转发好友单条消息
     */
    public CompletableFuture<ForwardFriendSingleMsgResponse> forwardFriendSingleMsgAsync(Object messageId, Long groupId, Long userId) {
        return ApiPostService.supplyAsyncWithCatch(() -> forwardFriendSingleMsg(messageId, groupId, userId));
    }

    /**
     * 转发群单条消息
     */
    public ForwardGroupSingleMsgResponse forwardGroupSingleMsg(Object messageId, Long groupId, Long userId) throws IOException, InterruptedException {
        Map<String, Object> body = new HashMap<>();
        body.put("message_id", messageId);
        body.put("group_id", groupId);
        body.put("user_id", userId);

        HttpResponse<String> resp = apiPostService.postJson(NapCatApiPath.FORWARD_GROUP_SINGLE_MSG, body);
        return ApiPostService.parseResponse(resp, ForwardGroupSingleMsgResponse.class);
    }

    /**
     * 异步转发群单条消息
     */
    public CompletableFuture<ForwardGroupSingleMsgResponse> forwardGroupSingleMsgAsync(Object messageId, Long groupId, Long userId) {
        return ApiPostService.supplyAsyncWithCatch(() -> forwardGroupSingleMsg(messageId, groupId, userId));
    }

    /**
     * 标记群聊已读
     */
    public MarkGroupMsgAsReadResponse markGroupMsgAsRead(Long userId, Long groupId, String messageId) throws IOException, InterruptedException {
        Map<String, Object> body = new HashMap<>();
        body.put("user_id", userId);
        body.put("group_id", groupId);
        body.put("message_id", messageId);

        HttpResponse<String> resp = apiPostService.postJson(NapCatApiPath.MARK_GROUP_MSG_AS_READ, body);
        return ApiPostService.parseResponse(resp, MarkGroupMsgAsReadResponse.class);
    }

    /**
     * 标记私聊已读
     */
    public MarkPrivateMsgAsReadResponse markPrivateMsgAsRead(Long userId, Long groupId, String messageId) throws IOException, InterruptedException {
        Map<String, Object> body = new HashMap<>();
        body.put("user_id", userId);
        body.put("group_id", groupId);
        body.put("message_id", messageId);

        HttpResponse<String> resp = apiPostService.postJson(NapCatApiPath.MARK_PRIVATE_MSG_AS_READ, body);
        return ApiPostService.parseResponse(resp, MarkPrivateMsgAsReadResponse.class);
    }

    /**
     * 标记消息已读
     */
    public MarkMsgAsReadResponse markMsgAsRead(Long userId, Long groupId, String messageId) throws IOException, InterruptedException {
        Map<String, Object> body = new HashMap<>();
        body.put("user_id", userId);
        body.put("group_id", groupId);
        body.put("message_id", messageId);

        HttpResponse<String> resp = apiPostService.postJson(NapCatApiPath.MARK_MSG_AS_READ, body);
        return ApiPostService.parseResponse(resp, MarkMsgAsReadResponse.class);
    }

    /**
     * 标记所有消息已读
     */
    public MarkAllAsReadResponse markAllAsRead() throws IOException, InterruptedException {
        HttpResponse<String> resp = apiPostService.postJson(NapCatApiPath.MARK_ALL_AS_READ, null);
        return ApiPostService.parseResponse(resp, MarkAllAsReadResponse.class);
    }
}
