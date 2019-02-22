package com.example.peter.project2;

public class MessageFormat {
//    owner: {
//        type: user,
//                required: true
//    },
//    content: {
//        type: String
//    },
//    urlImage: {
//        type: String
//    },
//    urlAudio: {
//        type: String
//    },
//    createdDate: { type: Date, default: Date.now },

    private String Username;
    private String Message;
    private String UniqueId;

    public MessageFormat(String uniqueId, String username, String message) {
        Username = username;
        Message = message;
        UniqueId = uniqueId;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getUniqueId() {
        return UniqueId;
    }

    public void setUniqueId(String uniqueId) {
        UniqueId = uniqueId;
    }
}
