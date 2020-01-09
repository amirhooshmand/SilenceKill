package ir.amirhooshmand.silencekill.repository;

import android.app.NotificationManager;

public class RequestPermissionActivityRepository {
    NotificationManager notificationManager;

    public RequestPermissionActivityRepository() {

    }

    public RequestPermissionActivityRepository(NotificationManager notificationManager) {
        this.notificationManager = notificationManager;
    }

    public NotificationManager getNotificationManager() {
        return notificationManager;
    }

    public void setNotificationManager(NotificationManager notificationManager) {
        this.notificationManager = notificationManager;
    }
}
