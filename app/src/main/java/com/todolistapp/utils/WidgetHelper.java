package com.todolistapp.utils;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;

import com.todolistapp.widget.TodoWidget;

public class WidgetHelper {

    public static void updateWidget(Context context) {
        Intent intent = new Intent(context, TodoWidget.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        context.sendBroadcast(intent);
    }
}
