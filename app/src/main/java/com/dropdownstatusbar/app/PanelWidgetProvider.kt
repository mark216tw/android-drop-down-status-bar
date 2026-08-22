package com.dropdownstatusbar.app

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

abstract class PanelWidgetProvider : AppWidgetProvider() {
    abstract val panelAction: PanelAction
    abstract val layoutResource: Int
    abstract val descriptionResource: Int

    override fun onUpdate(context: Context, manager: AppWidgetManager, appWidgetIds: IntArray) {
        appWidgetIds.forEach { appWidgetId ->
            val views = RemoteViews(context.packageName, layoutResource)
            views.setContentDescription(R.id.widget_icon, context.getString(descriptionResource))
            val intent = ActionActivity.intent(context, panelAction).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            val pendingIntent = PendingIntent.getActivity(
                context,
                appWidgetId,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            views.setOnClickPendingIntent(R.id.widget_root, pendingIntent)
            manager.updateAppWidget(appWidgetId, views)
        }
    }
}

class NotificationWidgetProvider : PanelWidgetProvider() {
    override val panelAction = PanelAction.NOTIFICATIONS
    override val layoutResource = R.layout.widget_panel
    override val descriptionResource = R.string.open_notifications
}

class QuickSettingsWidgetProvider : PanelWidgetProvider() {
    override val panelAction = PanelAction.QUICK_SETTINGS
    override val layoutResource = R.layout.widget_quick_settings
    override val descriptionResource = R.string.open_quick_settings
}
