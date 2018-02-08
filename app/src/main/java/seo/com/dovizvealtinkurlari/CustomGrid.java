package seo.com.dovizvealtinkurlari;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jubat on 6.8.2017.
 */

public class CustomGrid extends BaseAdapter {

    private Context mContext;
    private ArrayList<Data> List = new ArrayList<Data>();


    public CustomGrid(Context context,ArrayList<Data> ParamList) {

        this.List=ParamList;
        this.mContext=context;
    }


    @Override
    public int getCount() {
        return List.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int LastPosition=position;

        if (convertView == null || LastPosition<List.size()) {
            grid = new View(mContext);
            grid = inflater.inflate(R.layout.grid_single,null);
            ImageView imageViewbayrak = (ImageView)grid.findViewById(R.id.bayrak);
            TextView textViewpara = (TextView) grid.findViewById(R.id.para);
            TextView textViewalis = (TextView) grid.findViewById(R.id.alis);
            TextView textViewsatis = (TextView) grid.findViewById(R.id.satis);
            ImageView imageVieworan = (ImageView)grid.findViewById(R.id.oran);
            String x=List.get(position).Para;
            textViewpara.setText(List.get(position).Para);
            textViewalis.setText(List.get(position).Alis);
            textViewsatis.setText(List.get(position).Satis);
            imageViewbayrak.setImageResource(List.get(position).Bayrak);
            imageVieworan.setImageResource(List.get(position).Oran);

        }
        else
        {
            grid = (View) convertView;
        }

        return grid;
    }
}