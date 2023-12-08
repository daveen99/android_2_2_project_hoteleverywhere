package com.example.hotel_everywhere;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RestaurantActivity extends AppCompatActivity {
    String hotel;
    ImageView menupan;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant);

        menupan = (ImageView) findViewById(R.id.menupan);

        Intent intent = getIntent();
        if (intent != null) {
            hotel = intent.getStringExtra("hotel");
            if (hotel.equals("chungyang")) {
                menupan.setImageResource(R.drawable.cungyangmenu);
            }
            if (hotel.equals("nonsan")) {
                menupan.setImageResource(R.drawable.nonsanmenu);
            }
        }
    }

    public void openMap(View view) {
        Intent intent =  new Intent();
        if (hotel.equals("chungyang")) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:36.4370987,126.8016555?z=15"));
        }
        else if (hotel.equals("nonsan")) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:36.1995916,127.0885241?z=15"));
        }
        startActivity(intent);
    }

    public void goBack(View view) { // 돌아가기 버튼을 누르면 처음 메인화면으로 돌아감
        setResult(RESULT_CANCELED);
        finish();
    }
}
