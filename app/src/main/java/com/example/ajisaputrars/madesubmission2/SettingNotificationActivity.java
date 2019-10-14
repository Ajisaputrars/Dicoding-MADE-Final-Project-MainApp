package com.example.ajisaputrars.madesubmission2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

public class SettingNotificationActivity extends AppCompatActivity implements View.OnClickListener {

    private AlarmReceiver alarmReceiver;
    private Switch swReleaseToday, swDailyReminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_notification);
        this.setTitle(R.string.reminder_setting);

        swReleaseToday = findViewById(R.id.switch_release_today_reminder);
        swDailyReminder = findViewById(R.id.switch_daily_reminder);

        alarmReceiver = new AlarmReceiver();

        if (alarmReceiver.isAlarmSet(this, AlarmReceiver.TYPE_DAILY_REMINDER)) {
            swDailyReminder.setChecked(true);
        } else {
            swDailyReminder.setChecked(false);
        }

        if (alarmReceiver.isAlarmSet(this, AlarmReceiver.TYPE_RELEASE_TODAY_REMINDER)) {
            swReleaseToday.setChecked(true);
        } else {
            swReleaseToday.setChecked(false);
        }

        swReleaseToday.setOnClickListener(this);
        swDailyReminder.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.switch_daily_reminder:
                if (swDailyReminder.isChecked()) {
                    String TEXT_DAILY_REMINDER = getString(R.string.daily_reminder_text);
                    Toast.makeText(this, getString(R.string.switch_daily_reminder_on_text), Toast.LENGTH_SHORT).show();

                    String TIME_DAILY_REMINDER = "07:00";
                    alarmReceiver.setDailyReminderAlarm(this, AlarmReceiver.TYPE_DAILY_REMINDER,
                            TIME_DAILY_REMINDER, TEXT_DAILY_REMINDER);
                } else {
                    Toast.makeText(this, getString(R.string.switch_daily_reminder_off_text), Toast.LENGTH_SHORT).show();
                    alarmReceiver.cancelAlarm(this, AlarmReceiver.TYPE_DAILY_REMINDER);
                }
                break;
            case R.id.switch_release_today_reminder:
                if (swReleaseToday.isChecked()) {
                    String TIME_RELEASE_TODAY_REMINDER = "08:00";
                    alarmReceiver.setDailyNewMovieReminderAlarm(this, AlarmReceiver.TYPE_RELEASE_TODAY_REMINDER,
                            TIME_RELEASE_TODAY_REMINDER);
                    Toast.makeText(this, getString(R.string.switch_release_today_reminder_on_text), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, getString(R.string.switch_release_today_reminder_off_text), Toast.LENGTH_SHORT).show();
                    alarmReceiver.cancelAlarm(this, AlarmReceiver.TYPE_RELEASE_TODAY_REMINDER);
                }
                break;
        }
    }
}
