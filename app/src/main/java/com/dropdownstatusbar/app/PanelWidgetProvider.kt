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
            val pendingIntent = actionPendingIntent(context, appWidgetId, panelAction)
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

class CombinedPanelWidgetProvider : AppWidgetProvider() {
    override fun onUpdate(context: Context, manager: AppWidgetManager, appWidgetIds: IntArray) {
        appWidgetIds.forEach { appWidgetId ->
            val views = RemoteViews(context.packageName, R.layout.widget_combined_panel)
            views.setContentDescription(
                R.id.widget_quick_settings_action,
                context.getString(R.string.open_quick_settings)
            )
            views.setContentDescription(
                R.id.widget_notifications_action,
                context.getString(R.string.open_notifications)
            )
            views.setOnClickPendingIntent(
                R.id.widget_quick_settings_action,
                actionPendingIntent(context, appWidgetId * 2, PanelAction.QUICK_SETTINGS)
            )
            views.setOnClickPendingIntent(
                R.id.widget_notifications_action,
                actionPendingIntent(context, appWidgetId * 2 + 1, PanelAction.NOTIFICATIONS)
            )
            manager.updateAppWidget(appWidgetId, views)
        }
    }
}

private fun actionPendingIntent(
    context: Context,
    requestCode: Int,
    panelAction: PanelAction
): PendingIntent {
    val intent = ActionActivity.intent(context, panelAction).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
    }
    return PendingIntent.getActivity(
        context,
        requestCode,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )
}
