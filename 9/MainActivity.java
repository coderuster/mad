package com.example.multithreading;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView progressText;
    private Button startDownloadButton;
    private Handler mainThreadHandler;
    private int progressStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        progressText = findViewById(R.id.progressText);
        startDownloadButton = findViewById(R.id.startDownloadButton);

        // Initialize the Handler to update the UI on the main thread
        mainThreadHandler = new Handler(Looper.getMainLooper());

        // Start the download simulation on button click
        startDownloadButton.setOnClickListener(v -> startDownload());
    }

    // Method to start the download simulation in a separate thread
    private void startDownload() {
        // Disable the button to prevent multiple downloads at the same time
        startDownloadButton.setEnabled(false);
        progressStatus = 0;

        // Create a new Thread to perform the download
        new Thread(() -> {
            // Simulate a download by incrementing the progress every second
            while (progressStatus < 100) {
                progressStatus += 1;

                // Update the progress bar and text on the UI thread using the Handler
                mainThreadHandler.post(() -> {
                    progressBar.setProgress(progressStatus);
                    progressText.setText("Download Progress: " + progressStatus + "%");
                });

                try {
                    // Sleep for 100 milliseconds to simulate download time
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Re-enable the button when the download is complete
            mainThreadHandler.post(() -> startDownloadButton.setEnabled(true));
        }).start();
    }
}
