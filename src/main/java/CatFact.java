import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class CatFact {
    private String id;
    private String text;
    private String type;
    private String userNameFirst;
    private String userNameLast;
    private int upvotes;
    private String userUpvoted;

    public String getUserNameFirst() {
        return userNameFirst;
    }

    public void setUserNameFirst(String userNameFirst) {
        this.userNameFirst = userNameFirst;
    }

    public String getUserNameLast() {
        return userNameLast;
    }

    public void setUserNameLast(String userNameLast) {
        this.userNameLast = userNameLast;
    }

    @SuppressWarnings("unchecked")
    @JsonProperty("user")
    private void unpackUser(Map<String,Object> user) {
        Map<String,String> name = (Map<String,String>)user.get("name");
        this.userNameFirst = name.get("first");
        this.userNameLast = name.get("last");
    }

    public CatFact(@JsonProperty("_id") String id
            , @JsonProperty("text")  String text
            , @JsonProperty("type") String type
            , @JsonProperty("upvotes") int upvotes
            , @JsonProperty("userUpvoted") String userUpvoted) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.upvotes = upvotes;
        this.userUpvoted = userUpvoted;
    }

    @Override
    public String toString() {
        return "{" +
                "Text: '" + text + '\'' +
                ", User Name: '" + userNameFirst + ' ' + userNameLast + '\'' +
                ", Upvotes=" + upvotes +
                ", UserUpvoted='" + userUpvoted + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserName() {
        return userNameFirst;
    }

    public void setUserName(String name) {
        this.userNameFirst = name;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public String getUserUpvoted() {
        return userUpvoted;
    }

    public void setUserUpvoted(String userUpvoted) {
        this.userUpvoted = userUpvoted;
    }
}
