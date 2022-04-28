package icu.websocket.domain;

import java.util.Date;

public class SocketMessage {
    private String message;
    private Date sendDate;

    public SocketMessage() {
    }

    public SocketMessage(String message, Date sendDate) {
        this.message = message;
        this.sendDate = sendDate;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSendDate() {
        return this.sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    @Override
    public String toString() {
        return "{" +
                " message='" + getMessage() + "'" +
                ", sendDate='" + getSendDate() + "'" +
                "}";
    }

}
