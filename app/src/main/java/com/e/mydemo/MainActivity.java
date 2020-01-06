package com.e.mydemo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    ProgressBar progressBar;

    @SuppressLint("SetTextI18n")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        progressBar = findViewById(R.id.progressBar);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(FooWorker.class)
                        .build();

                WorkManager.getInstance(MainActivity.this).beginUniqueWork(
                        "test", ExistingWorkPolicy.REPLACE, request).enqueue();

                WorkManager.getInstance(MainActivity.this).getWorkInfosForUniqueWorkLiveData("test").observe(MainActivity.this, new Observer<List<WorkInfo>>() {
                    @Override
                    public void onChanged(List<WorkInfo> workInfos) {
                        if (workInfos.size() > 0) {
                            WorkInfo info = workInfos.get(0);
                            int progress = info.getProgress().getInt("progress", -1);
                            //Do something with progress variable

                            progressBar.setProgress(progress);

                        }

                    }
                });
            }
        });


    }
}
