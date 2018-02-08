package seo.com.dovizvealtinkurlari;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by jubat on 6.8.2017.
 */

public class JSONRead {

    public ArrayList<Data> SerbestDataList =new ArrayList<Data>();
    public  ArrayList<Data> AltinDataList =new ArrayList<Data>();
    Images images = new Images();

    public void SerbestDataRead(String urlText) {

        try {
            HttpHandler sh = new HttpHandler();
            String jsonStr = sh.makeServiceCall(urlText);

            if(jsonStr != null && !jsonStr.isEmpty()){
                jsonStr = jsonStr.replace("[","").replace("]","");
                jsonStr = jsonStr.replace("},","}~~");
                String[] stringList = jsonStr.split("~~");

                for(int i = 0; i < stringList.length; i++ ){
                    JSONObject jsonObj = new JSONObject(stringList[i]);
                    Data data = new Data();
                    data.Bayrak=images.serbest[i];
                    data.Para=jsonObj.getString("full_name");
                    data.Satis=jsonObj.getString("selling");
                    data.Alis=jsonObj.getString("buying");
                    double x=jsonObj.getDouble("change_rate");
                    if (x < 0){
                        data.Oran=R.drawable.down;
                    }
                    else if(x>0){
                        data.Oran=R.drawable.up;
                    }
                    else{
                        data.Oran=R.drawable.xxx;
                    }
                    SerbestDataList.add(data);
                }
            }
        }
        catch (final JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Data> SerbestGetData(){

        return SerbestDataList;
    }

    public void AltinDataRead(String urlText) {
        try {
            HttpHandler sh = new HttpHandler();
            String jsonStr = sh.makeServiceCall(urlText);
            if(jsonStr != null && !jsonStr.isEmpty()){
                jsonStr = jsonStr.replace("[","").replace("]","");
                jsonStr = jsonStr.replace("},","}~~");
                String[] stringList = jsonStr.split("~~");

                for(int i = 0; i < stringList.length; i++ ){
                    JSONObject jsonObj = new JSONObject(stringList[i]);
                    Data data = new Data();
                    data.Bayrak=R.drawable.altin;
                    data.Para=jsonObj.getString("full_name");
                    data.Satis=jsonObj.getString("selling");
                    data.Alis=jsonObj.getString("buying");
                    double x=jsonObj.getDouble("change_rate");
                    if (x < 0){
                        data.Oran=R.drawable.down;
                    }
                    else if(x>0){
                        data.Oran=R.drawable.up;
                    }
                    else{
                        data.Oran=R.drawable.xxx;
                    }
                    AltinDataList.add(data);
                }
            }
        }
        catch (final JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Data> AltinGetData(){

        return AltinDataList;
    }

}

