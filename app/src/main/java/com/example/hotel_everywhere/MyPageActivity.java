package com.example.hotel_everywhere;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.Random;

public class MyPageActivity extends AppCompatActivity {
    String doDelete;
    Dialog delete_alert;
    TextView clientName, reservationDate;
    String choiceDate, reservationName, amount;
    String profileIMG, hotel, hotelName;
    de.hdodenhof.circleimageview.CircleImageView profileBIG;
    int randomNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);

        clientName = (TextView) findViewById(R.id.clientName);
        profileBIG = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.profileBIG);
        reservationDate = (TextView) findViewById(R.id.reservationDate);

        delete_alert = new Dialog(MyPageActivity.this);
        delete_alert.setContentView(R.layout.delete_alert);
        delete_alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));



        Intent intent = getIntent();
        if (intent != null) {
            choiceDate = intent.getStringExtra("choiceDate");
            reservationName = intent.getStringExtra("reservationName");
            profileIMG = intent.getStringExtra("profileIMG");
            amount = intent.getStringExtra("amount");
            randomNumber = intent.getIntExtra("randomNumber", 1000);
            hotel = intent.getStringExtra("hotel");

            if (hotel.equals("chungyang")) {
                hotelName = "[예약장소]\n 청양호텔\n";
            } else if (hotel.equals("nonsan")) {
                hotelName = "[예약장소]\n 논산호텔\n";
            }

            clientName.setText("" + reservationName + " 고객님");
            reservationDate.setText( hotelName + "[예약일자]\n" + choiceDate + "\n" + "[예약금액]\n" + amount +
                    "\n\n" + "[배정된 호실]\n" + randomNumber + "호");


                if (!TextUtils.isEmpty(profileIMG)) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeFile(profileIMG);
                        profileBIG.setImageBitmap(bitmap);
                        //profileBIG.setImageURI(profileIMG);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }




        }
        Button btnYes = delete_alert.findViewById(R.id.btnYes);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();

                doDelete = "delete";
                resultIntent.putExtra("doDelete", doDelete);

                setResult(RESULT_OK, resultIntent);
                finish();

            }
        });
        Button btnNo = delete_alert.findViewById(R.id.btnNo);
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete_alert.dismiss();
            }
        });
    }

    public void showDialog(View v) {

        delete_alert.show();
    }

    public void goBack(View view) { // 돌아가기 버튼을 누르면 처음 메인화면으로 돌아감
        setResult(RESULT_CANCELED);
        finish();
    }

//    public void cancelReservation(View v) {
//        Intent resultIntent = new Intent();
//
//        doDelete = "delete";
//        resultIntent.putExtra("doDelete", doDelete);
//
//        setResult(RESULT_OK, resultIntent);
//        finish();
//    }

//    @Override // 다른 Activity에서 정보를 받아올때 사용하는 메서드
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//                choiceDate = data.getStringExtra("choiceDate");
//                reservationName = data.getStringExtra("reservationName");
//                profileIMG = Uri.parse(data.getStringExtra("profileIMG"));
//
//                Toast.makeText(MyPageActivity.this, choiceDate, Toast.LENGTH_SHORT).show();
//                Toast.makeText(MyPageActivity.this, reservationName, Toast.LENGTH_SHORT).show();
//                Toast.makeText(MyPageActivity.this, profileIMG.toString(), Toast.LENGTH_SHORT).show();
//
//                clientName.setText("" + reservationName + " 고객님");
//
//                profileBIG.setImageURI(profileIMG);
//
//                reservationDate.setText(choiceDate);
//
//
//    }

}
