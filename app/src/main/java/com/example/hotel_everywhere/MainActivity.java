package com.example.hotel_everywhere;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button myPageBtn, restaurantBtn, reservationBtn, reservationBtn2, roomServiceBtn, meetingRoomBtn;
    String choiceDate, doDelete, reservationName, amount, hotel;
    String profileIMG;
    int randomNumber;
    androidx.appcompat.widget.AppCompatButton hoteltitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myPageBtn = (Button) findViewById(R.id.myPageBtn);
        restaurantBtn = (Button) findViewById(R.id.restaurantBtn);
        reservationBtn = (Button) findViewById(R.id.reservationBtn);
        reservationBtn2 = (Button) findViewById(R.id.reservationBtn2);
        roomServiceBtn = (Button) findViewById(R.id.roomServiceBtn);
        meetingRoomBtn = (Button) findViewById(R.id.meetingRoomBtn);
        hoteltitle = (androidx.appcompat.widget.AppCompatButton) findViewById(R.id.hoteltitle);
    }

    public void takeReservation(View v) { // 메인) 예약하기 버튼을 누르면 예약화면(reservation)으로 이동
        hotel="chungyang";
        Intent intent = new Intent(MainActivity.this, ReservationActivity.class);

        startActivityForResult(intent, 1999);
    }
    public void takeReservation2(View v) { // 메인) 예약하기 버튼을 누르면 예약화면(reservation)으로 이동
        hotel="nonsan";
        Intent intent = new Intent(MainActivity.this, ReservationActivity.class);

        startActivityForResult(intent, 1999);
    }

    public void GomyPage(View v) {
        Intent intent = new Intent(MainActivity.this, MyPageActivity.class);
        intent.putExtra("profileIMG", profileIMG);
        intent.putExtra("reservationName", reservationName);
        intent.putExtra("choiceDate", choiceDate);
        intent.putExtra("amount", amount);
        intent.putExtra("randomNumber", randomNumber);
        intent.putExtra("hotel", hotel);

        startActivityForResult(intent, 1888);
    }

    public void goRoomService(View v) {
        Intent intent = new Intent(MainActivity.this, RoomServiceActivity.class);
        startActivity(intent);
    }

    public void goMeetingroom(View v) {
        Intent intent = new Intent(MainActivity.this, MeetingRoomServiceActivity.class);
        intent.putExtra("hotel", hotel);
        startActivity(intent);
    }

    public void goRes(View v) {
        Intent intent = new Intent(MainActivity.this, RestaurantActivity.class);
        intent.putExtra("hotel", hotel);
        startActivity(intent);
    }


    @Override // 다른 Activity에서 정보를 받아올때 사용하는 메서드
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1999) { // 예약하기 페이지를 통해서 예약날짜 정보를 갖고왔으면
            if (resultCode == RESULT_OK) {
                choiceDate = data.getStringExtra("choiceDate");
                reservationName = data.getStringExtra("reservationName");
                profileIMG = data.getStringExtra("profileIMG");
                amount = data.getStringExtra("amount");
                randomNumber = data.getIntExtra("randomNumber", 1000);

                reservationBtn.setVisibility(View.GONE);
                reservationBtn2.setVisibility(View.GONE);

                myPageBtn.setVisibility(View.VISIBLE);
                restaurantBtn.setVisibility(View.VISIBLE);
                roomServiceBtn.setVisibility(View.VISIBLE);
                meetingRoomBtn.setVisibility(View.VISIBLE);

                if (hotel.equals("chungyang")) {
                    hoteltitle.setBackgroundResource(R.drawable.chungyangtitle);
                } else if (hotel.equals("nonsan")) {
                    hoteltitle.setBackgroundResource(R.drawable.nonsanhoteltitle);
                }

            } else if (resultCode == RESULT_CANCELED) {
                // 돌아가기 버튼을 눌렀을때의 상황 (아무일도 안일어난다)
            }
        }

        if (requestCode == 1888) { // 마이페이지에서 예약취소를 눌렀으면..
            if (resultCode == RESULT_OK) {
                doDelete = data.getStringExtra("doDelete");
                if (doDelete.equals("delete")) {

                    reservationBtn.setVisibility(View.VISIBLE);
                    reservationBtn2.setVisibility(View.VISIBLE);

                    myPageBtn.setVisibility(View.GONE);
                    restaurantBtn.setVisibility(View.GONE);
                    roomServiceBtn.setVisibility(View.GONE);
                    meetingRoomBtn.setVisibility(View.GONE);

                    hoteltitle.setBackgroundResource(R.drawable.hoteltitle);
                }
            } else if (resultCode == RESULT_CANCELED) {
                // 돌아가기 버튼을 눌렀을때의 상황 (아무일도 안일어난다)
            }
        }
    }
}