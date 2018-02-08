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

public class MerkezBankası extends Fragment {


    public static GridView simpleGrid;
    public static View rootView;
    private ArrayList<Data> MerkezList =new ArrayList<Data>();

    //Merkez Bankası Kurlar XML Servisi
    public String url = "http://www.tcmb.gov.tr/kurlar/today.xml";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.merkezbankasi, container, false);
        simpleGrid = (GridView) rootView.findViewById(R.id.merkezGridView);

        XMLRead xmlRead = new XMLRead();
        xmlRead.DataRead("http://www.tcmb.gov.tr/kurlar/today.xml");
        MerkezList = xmlRead.GetData();

        if(MerkezList.isEmpty()){
            Toast.makeText(rootView.getContext(),"Lütfen internet bağlantınızı kontrol ediniz.",Toast.LENGTH_LONG);
        }

        CustomGrid Cgrid = new CustomGrid(rootView.getContext(),MerkezList);
        simpleGrid.setAdapter(Cgrid);

        return rootView;
    }

}