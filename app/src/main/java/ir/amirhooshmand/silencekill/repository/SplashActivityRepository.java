package ir.amirhooshmand.silencekill.repository;

import android.app.NotificationManager;

public class SplashActivityRepository {
    NotificationManager notificationManager;

    public SplashActivityRepository() {

    }

    public SplashActivityRepository(NotificationManager notificationManager) {
        this.notificationManager = notificationManager;
    }

    public NotificationManager getNotificationManager() {
        return notificationManager;
    }

    public void setNotificationManager(NotificationManager notificationManager) {
        this.notificationManager = notificationManager;
    }
}
