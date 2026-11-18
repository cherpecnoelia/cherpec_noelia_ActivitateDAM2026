package com.example.sem5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ImagineAdapter extends BaseAdapter {
    private Context ctx;
    private List<ImaginiDomeniu> imagini;
    private int resursaLayout;

    public ImagineAdapter(Context ctx, List<ImaginiDomeniu> imagini, int resursalayout) {
        this.ctx = ctx;
        this.imagini = imagini;
        this.resursaLayout = resursalayout;
    }

    @Override
    public int getCount() {
        return imagini.size();
    }

    @Override
    public Object getItem(int i) {
        return imagini.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(resursaLayout, viewGroup, false);

        ImageView imagineIV = v.findViewById(R.id.imagineIV);
        TextView textTV = v.findViewById(R.id.textTV);

        ImaginiDomeniu item = (ImaginiDomeniu) getItem(i);

        imagineIV.setImageBitmap(item.getImagine());
        textTV.setText(item.getTextAfisat());

        return v;
    }

}
