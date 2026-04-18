package fun.imiku.napcat4j.dto.schema;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * OneBot 11 消息段标记接口
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = OB11MessageText.class, name = "text"),
        @JsonSubTypes.Type(value = OB11MessageFace.class, name = "face"),
        @JsonSubTypes.Type(value = OB11MessageMFace.class, name = "mface"),
        @JsonSubTypes.Type(value = OB11MessageAt.class, name = "at"),
        @JsonSubTypes.Type(value = OB11MessageReply.class, name = "reply"),
        @JsonSubTypes.Type(value = OB11MessageImage.class, name = "image"),
        @JsonSubTypes.Type(value = OB11MessageRecord.class, name = "record"),
        @JsonSubTypes.Type(value = OB11MessageVideo.class, name = "video"),
        @JsonSubTypes.Type(value = OB11MessageFile.class, name = "file"),
        @JsonSubTypes.Type(value = OB11MessageCustomMusic.class, name = "music"),
        @JsonSubTypes.Type(value = OB11MessagePoke.class, name = "poke"),
        @JsonSubTypes.Type(value = OB11MessageDice.class, name = "dice"),
        @JsonSubTypes.Type(value = OB11MessageRPS.class, name = "rps"),
        @JsonSubTypes.Type(value = OB11MessageContact.class, name = "contact"),
        @JsonSubTypes.Type(value = OB11MessageLocation.class, name = "location"),
        @JsonSubTypes.Type(value = OB11MessageJson.class, name = "json"),
        @JsonSubTypes.Type(value = OB11MessageXml.class, name = "xml"),
        @JsonSubTypes.Type(value = OB11MessageMarkdown.class, name = "markdown"),
        @JsonSubTypes.Type(value = OB11MessageMiniApp.class, name = "miniapp"),
        @JsonSubTypes.Type(value = OB11MessageNode.class, name = "node"),
        @JsonSubTypes.Type(value = OB11MessageForward.class, name = "forward"),
        @JsonSubTypes.Type(value = OB11MessageOnlineFile.class, name = "onlinefile"),
        @JsonSubTypes.Type(value = OB11MessageFlashTransfer.class, name = "flashtransfer")
})
public interface OB11MessageData extends OB11MessageMixType {
}
