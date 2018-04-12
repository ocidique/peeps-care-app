package com.care.peeps;

/**
 * Created by HP on 4/12/2018.
 */

public class Message
{
    private String content;
    private String sender;
    private long timeStamp;


    public Message()
    {
        // default constructor
    }

    public Message(String messageContain, String messageSender, long timeStamp)
    {
        this.content = messageContain;
        this.sender = messageSender;
        this.timeStamp = timeStamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
