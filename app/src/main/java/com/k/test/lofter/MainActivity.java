package com.k.test.lofter;

import android.content.Context;
import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * @author k
 */
public class MainActivity extends AppCompatActivity {
    private LinearLayoutManager manager;

    @Override
    protected void onStart() {
        super.onStart();
        Fresco.initialize(this);
    }

    private int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        final ImageAdapter adapter = new ImageAdapter();
        adapter.bindToRecyclerView(recyclerView);
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        height = dm.heightPixels;
        for (int i = 0; i < 20; i++) {
            adapter.addData(new ImageBean());
        }
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int start = manager.findFirstVisibleItemPosition();
                int end = manager.findLastVisibleItemPosition();

                for (int i = start; i <= end; i++) {
                    SimpleDraweeView sdv = (SimpleDraweeView) adapter.getViewByPosition(i, R.id
                            .my_image_view);
                    int[] carLoc = new int[2];
                    if (sdv != null) {
                        sdv.getLocationInWindow(carLoc);
                        float fy = 0.5f * carLoc[1] / height + 0.25f;
                        Log.d("---", fy + "");
                        PointF focusPoint = new PointF(0.5f, fy);
                        sdv.getHierarchy().setActualImageFocusPoint(focusPoint);
                    }
                }
            }
        });
    }
}
