package layout;

import android.app.IntentService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Message;
import android.widget.RemoteViews;

import java.util.ArrayList;

import seo.com.dovizvealtinkurlari.Data;
import seo.com.dovizvealtinkurlari.JSONRead;
import seo.com.dovizvealtinkurlari.R;

import static android.appwidget.AppWidgetManager.INVALID_APPWIDGET_ID;

/**
 * Created by jubat on 6.8.2017.
 */

public class UpdateWidgetService extends IntentService {
    public UpdateWidgetService() {
        super("UpdateWidgetService");
    }

    public static String ACTION_WIDGET_TITLE_CLICK = "Action_widget_title_click";
    public static String ACTION_WIDGET_CONTENT_CLICK = "Action_widget_conent_click";
    public static String ACTION_WIDGET_UPDATE_CLICK = "linearLayout";

    @Override
    protected void onHandleIntent(Intent intent) {
        AppWidgetManager appWidgetManager = AppWidgetManager
                .getInstance(this);
        int incomingAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,
                INVALID_APPWIDGET_ID);
        if (incomingAppWidgetId != INVALID_APPWIDGET_ID) {
            updateOneAppWidget(appWidgetManager, incomingAppWidgetId,
                    intent.getAction());
        }
    }

    private void updateOneAppWidget(AppWidgetManager appWidgetManager,
                                    int appWidgetId, String whichButton) {
        RemoteViews views = new RemoteViews(this.getPackageName(),
                R.layout.doviz_widget);

        onWidgetHeaderClick(views, appWidgetId);
        onWidgetContentClick(views, appWidgetId);
        updateWidgetData(views, appWidgetId);
        bindDataToRemoteView(views,null);

        if (whichButton.equals("ACTION_WIDGET_VIEW_CLICK")) {
        }
        else if (whichButton.equals("ACTION_WIDGET_TITLE_VIEW_CLICK")) {
        }
        else if (whichButton.equals("linearLayout")) {
        }
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
    public void bindDataToRemoteView(RemoteViews views, Message message) {

    }

    private void updateWidgetData(RemoteViews remoteViews, int appWidgetId){

        ArrayList<Data> DataList  = new ArrayList<Data>();
        ArrayList<Data> AltinList = new ArrayList<Data>();

        AltinList.clear();
        DataList.clear();

        JSONRead jsonRead =new JSONRead();
        jsonRead.AltinDataRead("https://www.doviz.com/api/v1/golds/all/latest");
        AltinList = jsonRead.AltinGetData();
        jsonRead.SerbestDataRead("https://www.doviz.com/api/v1/currencies/all/latest");
        DataList = jsonRead.SerbestGetData();

        if(!DataList.isEmpty()){
            remoteViews.setTextViewText(R.id.textDolar,DataList.get(0).Para);
            remoteViews.setTextViewText(R.id.textDolarAlis,DataList.get(0).Alis);
            remoteViews.setTextViewText(R.id.textDolarSatis,DataList.get(0).Satis);
            remoteViews.setImageViewResource(R.id.dolar,R.drawable.usa);

            remoteViews.setTextViewText(R.id.textEuro,DataList.get(1).Para);
            remoteViews.setTextViewText(R.id.textEuroAlis,DataList.get(1).Alis);
            remoteViews.setTextViewText(R.id.textEuroSatis,DataList.get(1).Satis);
            remoteViews.setImageViewResource(R.id.euro,R.drawable.europeanunion);

            remoteViews.setTextViewText(R.id.textGram,AltinList.get(5).Para);
            remoteViews.setTextViewText(R.id.textGramAlis,AltinList.get(5).Alis);
            remoteViews.setTextViewText(R.id.textGramSatis,AltinList.get(5).Satis);
            remoteViews.setImageViewResource(R.id.gram,R.drawable.altin);

            remoteViews.setTextViewText(R.id.textCeyrek,AltinList.get(0).Para);
            remoteViews.setTextViewText(R.id.textCeyrekAlis,AltinList.get(0).Alis);
            remoteViews.setTextViewText(R.id.textCeyrekSatis,AltinList.get(0).Satis);
        }

        remoteViews.setImageViewResource(R.id.ceyrek,R.drawable.altin);

        Intent btnNextIntent = new Intent(this, this.getClass());
        btnNextIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetId);
        btnNextIntent.setAction(ACTION_WIDGET_UPDATE_CLICK);
        PendingIntent btnNextPendingIntent = PendingIntent.getService(this,
                0, btnNextIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.linearLayout,
                btnNextPendingIntent);
    }
    private void onWidgetHeaderClick(RemoteViews views, int appWidgetId) {
    }
    private void onWidgetContentClick(RemoteViews views, int appWidgetId) {
    }
}