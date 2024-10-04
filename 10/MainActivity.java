package com.example.graphicalprimitive;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content view to the custom GraphicsView
        setContentView(new InteractiveGraphicsView(this));
    }
}
