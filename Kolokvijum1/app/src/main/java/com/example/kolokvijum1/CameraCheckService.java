package com.example.kolokvijum1;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

/**
 * Servis koji se pokrecE na svaki minut i proverava dozvolu kamere.
 * Ako je kamera dozvoljena, salje broadcast fragmentu da prikaze ImageView.
 */
public class CameraCheckService extends Service {

    private static final long CHECK_INTERVAL_MS = 60 * 1000L; // 1 minut

    private Handler handler;
    private Runnable cameraCheckRunnable;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler = new Handler(Looper.getMainLooper());

        cameraCheckRunnable = new Runnable() {
            @Override
            public void run() {
                checkCameraPermission();
                handler.postDelayed(this, CHECK_INTERVAL_MS);
            }
        };

        // Prva provera odmah, pa svaki minut
        handler.post(cameraCheckRunnable);

        return START_STICKY;
    }

    private void checkCameraPermission() {
        boolean cameraGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED;

        if (cameraGranted) {
            Intent broadcastIntent = new Intent(RecipeFragment.ACTION_CAMERA_GRANTED);
            LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent);
        }
    }

    @Override
    public void onDestroy() {
        if (handler != null && cameraCheckRunnable != null) {
            handler.removeCallbacks(cameraCheckRunnable);
        }
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
