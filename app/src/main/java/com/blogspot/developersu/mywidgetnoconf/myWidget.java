package com.blogspot.developersu.mywidgetnoconf;


import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class myWidget extends AppWidgetProvider {

    private void setWidgetIntents(Context context, AppWidgetManager appWidgetManager, int appWidgetIds[]){

        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.my_widget_layout);

        for (int appWidgetId : appWidgetIds) {
            Intent intentBtn = new Intent(context, MainActivity.class);
            intentBtn.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            intentBtn.putExtra("signature", "Button 1");
            intentBtn.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP );
            PendingIntent pi = PendingIntent.getActivity(context, appWidgetId+0, intentBtn, PendingIntent.FLAG_UPDATE_CURRENT); // appWidgetId+0 для того, чтобы можно было в разных виджетах иметь разные данные
            rv.setOnClickPendingIntent(R.id.widgetBtn, pi);
            /*===================================================================================================*/
            // Set the same for button 2
            Intent intentBtn2 = new Intent(context, MainActivity.class);
            intentBtn2.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            intentBtn2.putExtra("signature", "Button 2");
            intentBtn2.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP );
            // Where PendingIntent.getActivity(Context context, int requestCode, Intent intent, int flags) has 4 parameters.
            // You have to send different "requestCode" for different PendingIntents.
            PendingIntent pi2 = PendingIntent.getActivity(context, appWidgetId+1, intentBtn2, PendingIntent.FLAG_UPDATE_CURRENT);
            rv.setOnClickPendingIntent(R.id.widgetBtn2, pi2);
            /*===================================================================================================*/
            Intent intentBtn3 = new Intent(context, MyIntentService.class);
            intentBtn3.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            intentBtn3.putExtra("signature", "Button 3");
            PendingIntent pi3 = PendingIntent.getService(context, appWidgetId+2, intentBtn3, PendingIntent.FLAG_UPDATE_CURRENT);
            rv.setOnClickPendingIntent(R.id.widgetBtn3, pi3);
            /*===================================================================================================*/
            Intent intentBtn4 = new Intent(context, MyIntentService.class);
            intentBtn4.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            intentBtn4.putExtra("signature", "Button 4");
            PendingIntent pi4 = PendingIntent.getService(context, appWidgetId+3, intentBtn4, PendingIntent.FLAG_UPDATE_CURRENT);
            rv.setOnClickPendingIntent(R.id.widgetBtn4, pi4);

            appWidgetManager.updateAppWidget(appWidgetId, rv);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        setWidgetIntents(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        AppWidgetManager awm = AppWidgetManager.getInstance(context);
        ComponentName compName = new ComponentName(context, myWidget.class);
        int[] widgetIds = awm.getAppWidgetIds(compName);
        setWidgetIntents(context, awm, widgetIds);
    }
}
