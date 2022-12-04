package notification;

import account_creation.Account;

import java.time.LocalDateTime;

public class ChatNotification extends Notification{
    /**
     * Child class of Notification - a Match Notification.
     */
    private String id;

    /**
     * Construct a Chat Notification Object with attributes of content, sender, time, and chatID
     * @param content Content of the notification
     * @param sender Sender of the notification
     * @param time Time the notification was sent
     * @param chatID The chatid of current user
     */
    public ChatNotification(String content, Account sender, LocalDateTime time, String chatID) {
        super(content, sender, time);
        this.id = chatID;
    }
}
