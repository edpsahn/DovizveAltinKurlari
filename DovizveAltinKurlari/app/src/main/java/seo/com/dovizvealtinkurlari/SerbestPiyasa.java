package seo.com.dovizvealtinkurlari;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by jubat on 6.8.2017.
 */

public class SerbestPiyasa extends Fragment {

    public static GridView simpleGrid;
    public static View rootView;
    private ArrayList<Data> SerbestList =new ArrayList<Data>();
    private ArrayList<Data> popUpList =new ArrayList<Data>();

    public static ArrayList<String> paraList =new ArrayList<String>();

    public static int selectedPosition = 0;

    EditText cevrilecekTutar;
    TextView cevrilenTutar;
    Spinner firstSpinner;
    Spinner secondSpinner;
    Button btnCalculate;

    // Serbes Piyasa Kur Veri Servisi
    public String url = "http://www.doviz.com/api/v1/currencies/all/latest";

    //Serbest Piyasa sayfası yükleniyor...
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.serbestpiyasa, container, false);
        simpleGrid = (GridView) rootView.findViewById(R.id.serbestGridView);

        JSONRead jsonRead =new JSONRead();
        jsonRead.SerbestDataRead("http://www.doviz.com/api/v1/currencies/all/latest");
        SerbestList = jsonRead.SerbestGetData();

        if(SerbestList.isEmpty()){
            Toast.makeText(rootView.getContext(),"Lütfen internet bağlantınızı kontrol ediniz.",Toast.LENGTH_LONG);
        }

        popUpList.addAll(SerbestList);
        Data tlData = new Data();
        tlData.Para = "Türk Lirası";
        tlData.Alis = "1";
        tlData.Satis = "1";
        popUpList.add(0,tlData);

        for (int i = 0; i<popUpList.size(); i++){
            paraList.add(popUpList.get(i).Para);
        }


        CustomGrid Cgrid = new CustomGrid(rootView.getContext(),SerbestList);
        simpleGrid.setAdapter(Cgrid);

        simpleGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                View popView = (LayoutInflater.from(rootView.getContext())).inflate(R.layout.pop_window ,null);

                cevrilecekTutar = (EditText) popView.findViewById(R.id.cevirelecekTutar);
                cevrilenTutar = (TextView) popView.findViewById(R.id.cevrilenTutar);
                firstSpinner = (Spinner) popView.findViewById(R.id.firstSpinner);
                secondSpinner = (Spinner) popView.findViewById(R.id.secondSpinner);
                btnCalculate = (Button) popView.findViewById(R.id.btnCalculate);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(rootView.getContext(),
                        android.R.layout.simple_spinner_item, paraList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                firstSpinner.setAdapter(adapter);
                firstSpinner.setSelection(position + 1);
                secondSpinner.setAdapter(adapter);

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(rootView.getContext());
//                alertDialog.setTitle("Hesaplama Aracı");
                alertDialog.setView(popView);


                btnCalculate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int selectedFirstPosition = (int)firstSpinner.getSelectedItemId();
                        int selectedSecondPosition = (int)secondSpinner.getSelectedItemId();
                        double value = (Double.parseDouble(popUpList.get(selectedFirstPosition).Satis)/Double.parseDouble(popUpList.get(selectedSecondPosition).Satis)
                                * Double.parseDouble(cevrilecekTutar.getText().toString()) );
                        cevrilenTutar.setText(Double.toString(value));
                    }
                });

                Dialog dialog = alertDialog.create();
                dialog.show();

            }
        });

        return rootView;
    }
}