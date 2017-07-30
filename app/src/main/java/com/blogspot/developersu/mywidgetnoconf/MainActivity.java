package com.blogspot.developersu.mywidgetnoconf;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private void handleIntent(Intent i){
        String recievedString;
        Bundle bndle = i.getExtras();
        if (bndle != null) {
            int awID = bndle.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
            recievedString = bndle.getString("signature");
            Toast.makeText(getApplicationContext(), "Got intent from: " + Integer.toString(awID) + " " + recievedString, Toast.LENGTH_SHORT).show();
            if (awID != 0 && recievedString != null)
                sendBackToWidget(awID, recievedString);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handleIntent(getIntent());
        /* Почему мы вызываем handleIntent прямо тут? Потому, что если приложение закрыто а не работает в фоне, то при запуске его из виджета будет вызван
         * onCreate. Очевидно, что если это приложение уже будет находиться в стеке, то вызываться будет что-то другое :) */
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        //setIntent(intent);                    // Удобно использовать, если бы наш Intent был объявлен глобальной переменной. Тогда бы мы просто переопределили его через setintent().
        handleIntent(intent);
    }

    private void sendBackToWidget(int widgetID, String str){
        AppWidgetManager awManager = AppWidgetManager.getInstance(getApplicationContext());
        RemoteViews rv = new RemoteViews(getPackageName(), R.layout.my_widget_layout);
        rv.setTextViewText(R.id.widgetText, str);
        awManager.updateAppWidget(widgetID, rv);
    }
}
