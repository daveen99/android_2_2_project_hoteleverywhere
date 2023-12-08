package com.example.hotel_everywhere;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MeetingRoomServiceActivity extends AppCompatActivity {
    Button btn1, btn2, btn3, btn4;
    TextView text1, text2, text3, text4;
    String hotel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meeting_room);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        text3 = (TextView) findViewById(R.id.text3);
        text4 = (TextView) findViewById(R.id.text4);

        Intent intent = getIntent();
        if (intent != null) {
            hotel = intent.getStringExtra("hotel");
            if (hotel.equals("chungyang")) {
                btn3.setText("고추밭\n체험");
                btn4.setText("청양\n문화관");
            }
            if (hotel.equals("nonsan")) {
                btn3.setText("딸기밭\n체험");
                btn4.setText("논산\n문화관");
            }
        }
    }

    public void goBack(View view) { // 돌아가기 버튼을 누르면 처음 메인화면으로 돌아감
        setResult(RESULT_CANCELED);
        finish();
    }

    public void meeting1(View v) {
        showAlertDialog(this);
    }
    public void meeting2(View v) {
        showAlertDialogQrCode(this);
        //showAlertDialog2(this);
    }
    public void meeting3(View v) {
        showAlertDialogQrCode(this);
        //showAlertDialog2(this);
    }
    public void meeting4(View v) {
        showAlertDialogQrCode(this);
        //showAlertDialog2(this);
    }

    private void showAlertDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("내부시설 알림");
        builder.setMessage("선택하신 시설은 다른 고객님께서 사용중입니다.");

        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showAlertDialog2(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("회의실 알림");
        builder.setMessage("선택하신 회의실은 사용가능한 상태입니다.");

        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showAlertDialogQrCode(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        View customLayout = getLayoutInflater().inflate(R.layout.qrcode, null);
        builder.setView(customLayout);

        builder.setTitle("내부시설 알림");
        builder.setMessage("이용하려면 QR코드를 직원에게 보여주십시오.");

        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
