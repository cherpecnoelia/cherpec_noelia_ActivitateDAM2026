package com.example.sem5;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ImaginiActivity extends AppCompatActivity {

    private ListView lv;
    private List<ImaginiDomeniu> lista = new ArrayList<>();
    private List<Bitmap> imagini = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagini);

        lv = findViewById(R.id.imagini);


        List<String> linkuriImagini = new ArrayList<>();
        linkuriImagini.add("https://static.vecteezy.com/system/resources/thumbnails/020/484/438/small/hand-drawn-loose-leaf-notebook-or-notepad-vector.jpg");
        linkuriImagini.add("https://www.penworld.eu/media/catalog/product/cache/d043c7a7ec1c468522a299a55a30bc9f/a/c/achterkant_black_1_3.jpg");
        linkuriImagini.add("https://eyeondesign.aiga.org/wp-content/uploads/2016/11/comp-notebook-history13w.jpg");
        linkuriImagini.add("https://bulletjournal.com/cdn/shop/files/Guide_fbdc87f8-cb29-4d55-89c1-cfc527e079c0_1200x.png");
        linkuriImagini.add("https://www.absolutoffice.ro/wp-content/uploads/2021/09/caiet-spirala-oxford-a4-matematica-negru-ox-400134120.jpg");


        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        executor.execute(() -> {

            for (String link : linkuriImagini) {
                HttpURLConnection con = null;
                try {
                    URL url = new URL(link);
                    con = (HttpURLConnection) url.openConnection();
                    con.setRequestProperty("User-Agent", "Mozilla/5.0");
                    InputStream is = con.getInputStream();
                    imagini.add(BitmapFactory.decodeStream(is));
                    is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    imagini.add(null);
                } finally {
                    if (con != null) con.disconnect();
                }
            }


            handler.post(() -> {
                lista.add(new ImaginiDomeniu("Caiet clasic", imagini.get(0), "https://en.wikipedia.org/wiki/Notebook"));
                lista.add(new ImaginiDomeniu("Moleskine", imagini.get(1), "https://en.wikipedia.org/wiki/Moleskine"));
                lista.add(new ImaginiDomeniu("Caiet de compoziție", imagini.get(2), "https://en.wikipedia.org/wiki/Composition_book"));
                lista.add(new ImaginiDomeniu("Bullet journal", imagini.get(3), "https://en.wikipedia.org/wiki/Bullet_journal"));
                lista.add(new ImaginiDomeniu("Caiet spiralat", imagini.get(4), "https://en.wikipedia.org/wiki/Spiral_binding"));

                ImagineAdapter adapter = new ImagineAdapter(ImaginiActivity.this,lista, R.layout.imagine_layout);
                lv.setAdapter(adapter);
            });
        });

        lv.setOnItemClickListener((parent, view, position, id) -> {
            Intent it = new Intent(ImaginiActivity.this, WebViewActivity.class);
            it.putExtra("link", lista.get(position).getLink());
            startActivity(it);
        });
    }
}


