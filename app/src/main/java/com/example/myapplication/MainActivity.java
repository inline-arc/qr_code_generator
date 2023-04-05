package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.zxing.WriterException;
import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class MainActivity extends AppCompatActivity {

    ImageView qrcodeimg;
    EditText txtinput;
    Button gbutton;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gbutton = findViewById(R.id.button);
        qrcodeimg = findViewById(R.id.qrcode);
        txtinput = findViewById(R.id.input);

        gbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(txtinput.getText().toString())) {

                    // this method showing a toast message.
                    Toast.makeText(MainActivity.this, "Enter some text to generate QR Code", Toast.LENGTH_SHORT).show();
                } else {

                    WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);


                    Display display = manager.getDefaultDisplay();


                    Point point = new Point();
                    display.getSize(point);


                    int width = point.x;
                    int height = point.y;

                    // generating dimension from width and height.
                    int dimen = Math.min(width, height);
                    dimen = dimen * 3 / 4;

                    qrgEncoder = new QRGEncoder(txtinput.getText().toString(), null, QRGContents.Type.TEXT, dimen);
                    try {
                        // getting our qrcode in the form of bitmap.
                        bitmap = qrgEncoder.encodeAsBitmap();
                        qrcodeimg.setImageBitmap(bitmap);
                    } catch (WriterException e) {
                        // this method is called for
                        // exception handling.
                        Log.e("Tag", e.toString());
                    }
                }
            }
        });
    }
}
