package com.e.mydemo;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class FooWorker extends Worker {

    public FooWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            setProgressAsync(new Data.Builder().putInt("progress", 0).build());
            Thread.sleep(1000);
            setProgressAsync(new Data.Builder().putInt("progress", 50).build());
            Thread.sleep(1000);
            setProgressAsync(new Data.Builder().putInt("progress", 100).build());

            return Result.success();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return Result.failure();
        }
    }
}
