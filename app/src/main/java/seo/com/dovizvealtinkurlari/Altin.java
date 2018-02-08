package seo.com.dovizvealtinkurlari;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by jubat on 6.8.2017.
 */

public class Altin extends Fragment {

    public static GridView simpleGrid;
    public static View rootView;
    private ArrayList<Data> AltınList =new ArrayList<Data>();

    //Altın Veri Servisi
    //public String url = "http://www.doviz.com/api/v1/golds/all/latest";
    public String url = "https://www.doviz.com/api/v1/golds/all/latest";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.altin, container, false);
        simpleGrid = (GridView) rootView.findViewById(R.id.altinGridView);

        JSONRead jsonGoldRead =new JSONRead();
        jsonGoldRead.AltinDataRead("https://www.doviz.com/api/v1/golds/all/latest");
        AltınList = jsonGoldRead.AltinGetData();

        if(AltınList.isEmpty()){
            Toast.makeText(rootView.getContext(),"Lütfen internet bağlantınızı kontrol ediniz.",Toast.LENGTH_LONG);
        }

        CustomGrid Cgrid = new CustomGrid(rootView.getContext(),AltınList);
        simpleGrid.setAdapter(Cgrid);
        return rootView;

    }

}