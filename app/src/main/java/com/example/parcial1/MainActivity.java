package com.example.parcial1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button btnLoad, btnSend;
    ImageView img;
    Uri selectedImageUri;

    int SELECT_PICTURE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLoad = findViewById(R.id.btnLoad);
        btnSend = findViewById(R.id.btnSend);
        img = findViewById(R.id.img);

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImage();
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                try {
                    Intent intet = new Intent(Intent.ACTION_SEND);

                    intet.setData(Uri.parse("mailto:"));
                    intet.setType("text/plain");
                    intet.putExtra(Intent.EXTRA_EMAIL, "enzoxaero@gmail.com");
                    intet.putExtra(Intent.EXTRA_SUBJECT, "Image");
                    intet.putExtra(Intent.EXTRA_TEXT, "Is a Image");
                    intet.putExtra(Intent.EXTRA_STREAM, selectedImageUri);
                    intet.setType("image/*");
                    startActivity(Intent.createChooser(intet, "Send mail..."));
                    //finish();
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    private void getImage(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent,  200);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    img.setImageURI(selectedImageUri);
                }
            }
        }
    }

}