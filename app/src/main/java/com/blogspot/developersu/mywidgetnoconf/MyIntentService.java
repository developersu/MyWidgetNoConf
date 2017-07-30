package com.blogspot.developersu.mywidgetnoconf;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;


public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            Bundle bndle = intent.getExtras();
            int wID = bndle.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
            String btnPressed = bndle.getString("signature");

            RemoteViews rv = new RemoteViews(getPackageName(), R.layout.my_widget_layout);
            AppWidgetManager awm = AppWidgetManager.getInstance(getApplicationContext());
            rv.setTextViewText(R.id.widgetText, btnPressed);
            awm.updateAppWidget(wID, rv);
        }
    }
}
