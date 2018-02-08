package seo.com.dovizvealtinkurlari;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jubat on 6.8.2017.
 */

public class PopUp extends Activity {

    EditText cevrilecekTutar;
    TextView cevrilenTutar;
    Spinner firstSpinner;
    Spinner secondSpinner;

    public static ArrayList<Data> list =new ArrayList<Data>();
    public static ArrayList<String> paraList =new ArrayList<String>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_window);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int w = dm.widthPixels;
        int h = dm.heightPixels;

        getWindow().setLayout((int)(w*.8),(int)(h*.5));

    }

}
