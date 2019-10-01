package com.example.ajisaputrars.madesubmission2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

    }

    private void showAlarmNotification(Context context, String title, String message, int notifId) {

    }

    public void setDailyReminderAlarm(Context context, String type, String time, String message) {

    }

    public void setDailyNewMovieReminderAlarm(Context context, String type, String time, String message) {

    }

    public void cancelAlarm(Context context, String type) {

    }

    public boolean isAlarmSet(Context context, String type) {
        return true;
    }

    public boolean isDateInvalid(String date, String format) {
        return true;
    }
}
