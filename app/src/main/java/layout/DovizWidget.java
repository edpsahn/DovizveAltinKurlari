package layout;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import seo.com.dovizvealtinkurlari.R;

import static android.appwidget.AppWidgetManager.INVALID_APPWIDGET_ID;

/**
 * Implementation of App Widget functionality.
 */
public class DovizWidget extends AppWidgetProvider {

    public void onUpdate(Context context,
                         android.appwidget.AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        int appWidgetId = INVALID_APPWIDGET_ID;
        if (appWidgetIds != null) {
            int N = appWidgetIds.length;
            if (N == 1) {
                appWidgetId = appWidgetIds[0];
            }
        }
        Intent intent = new Intent(context, UpdateWidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetId);
        intent.setAction("DO NOTHING ACTION");
        context.startService(intent);
    }
}

