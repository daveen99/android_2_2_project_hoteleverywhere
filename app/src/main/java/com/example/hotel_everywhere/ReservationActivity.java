package com.example.hotel_everywhere;

import static android.text.format.DateUtils.getDayOfWeekString;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Random;

public class ReservationActivity extends AppCompatActivity {
    CalendarView calendarView;
    TextView reservationText;
    String choiceDate, name, newName, amount;
    EditText reservationName;
    Uri profileIMG;
    String imagePath;
    de.hdodenhof.circleimageview.CircleImageView miniprofile;
    Random random;
    int randomNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation);
        reservationText = (TextView) findViewById(R.id.reservationText);
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override // 캘릭더뷰의 날짜를 선택하면 작동하는 메서드
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // 캘린더뷰의 month(월)는 0부터 시작함
                month += 1;
                choiceDate = year + "년 " + month + "월 " + dayOfMonth + "일";
                //Toast.makeText(ReservationActivity.this, choiceDate + "을 선택하셨습니다.", Toast.LENGTH_SHORT).show();

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month-1, dayOfMonth);
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

                switch (dayOfWeek) {
                    case Calendar.SUNDAY:
                    case Calendar.SATURDAY:
                        reservationText.setText("" + year + "년 " + month + "월 " + dayOfMonth + "일의 예약금액은\n (주말)250,000원 입니다.");
                        amount = "250,000원";
                        break;
                    default:
                        reservationText.setText("" + year + "년 " + month + "월 " + dayOfMonth + "일의 예약금액은\n (평일)150,000원 입니다.");
                        amount = "150,000원";
                        break;
                }
            }
        });
    }


    public void makeReservation(View view) {
        reservationName = (EditText) findViewById(R.id.reservationName);
        name = reservationName.getText().toString();
        if (choiceDate != null && !TextUtils.isEmpty(name)) {

            Intent resultIntent = new Intent();

            resultIntent.putExtra("choiceDate", choiceDate);
            resultIntent.putExtra("amount", amount);


            resultIntent.putExtra("profileIMG", imagePath);

            random = new Random();
            randomNumber = random.nextInt(1400 - 1001 + 1) + 1001;
            resultIntent.putExtra("randomNumber", randomNumber);

            if (name.length() > 3) {
                newName =  String.valueOf(name.charAt(0)) + String.valueOf(name.charAt(1)) + String.valueOf(name.charAt(2));
                newName += "..";
                resultIntent.putExtra("reservationName", newName);
            } else {
                resultIntent.putExtra("reservationName", name);
            }


            setResult(RESULT_OK, resultIntent);
            finish();
        } else {
            Toast.makeText(ReservationActivity.this, "예약일자와 이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
        }
    }

    public void goBack(View view) { // 돌아가기 버튼을 누르면 처음 메인화면으로 돌아감
        setResult(RESULT_CANCELED);
        finish();
    }


    public void selectPicture(View v) { // 프로필 선택하기 버튼을 누르면 갤러리로 이동한다. 그 후 이미지를 선택해 불러온다!!!
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //startActivityForResult(galleryIntent, 1313);

        // 갤러리권한 확인
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);
            startActivityForResult(galleryIntent, 1313);
        } else {
            startActivityForResult(galleryIntent, 1313);
        }
    }

    @Override // 이미지를 받는 메서드
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1313 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            profileIMG = data.getData();

            try {
                imagePath = saveImageToInternalStorage(profileIMG);

            } catch (IOException e) {
                e.printStackTrace();
            }

            miniprofile = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.miniprofile);
            miniprofile.setImageURI(profileIMG);
        }
    }
    private String saveImageToInternalStorage(Uri uri) throws IOException {
        InputStream inputStream = getContentResolver().openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

        // 내부 저장소에 이미지 파일 저장
        String filePath = getFilesDir().getPath().toString() + "/image.jpg";
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, openFileOutput("image.jpg", MODE_PRIVATE));

        return filePath;
    }
}
