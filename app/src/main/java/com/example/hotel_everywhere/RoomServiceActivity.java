package com.example.hotel_everywhere;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;


public class RoomServiceActivity extends AppCompatActivity {
    FrameLayout fragmentContainer, fragmentContainer2;
    LinearLayout roomlayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_service);

        fragmentContainer = findViewById(R.id.fragment_container);
        fragmentContainer2 = findViewById(R.id.fragment_container2);
        roomlayout = (LinearLayout) findViewById(R.id.roomlayout);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new RoomFragment())
                .commit();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container2, new RoomFragment2())
                .commit();
    }

    public void food(View v) { // 음식 버튼을 누르면 서비스프래그먼트가 안보이게된다
        fragmentContainer.setVisibility(View.VISIBLE);
        fragmentContainer2.setVisibility(View.GONE);

        int backgroundColor = Color.parseColor("#FFE4C4"); // Use your color code here
        ColorDrawable colorDrawable = new ColorDrawable(backgroundColor);
        roomlayout.setBackground(colorDrawable);
    }

    public void service(View v) { // 서비스 버튼을 누르면 음식프래그먼트가 안보이게된다
        fragmentContainer.setVisibility(View.GONE);
        fragmentContainer2.setVisibility(View.VISIBLE);

        int backgroundColor = Color.parseColor("#F0FFF0"); // Use your color code here
        ColorDrawable colorDrawable = new ColorDrawable(backgroundColor);
        roomlayout.setBackground(colorDrawable);
    }

    public void goBack(View view) { // 돌아가기 버튼을 누르면 처음 메인화면으로 돌아감
        setResult(RESULT_CANCELED);
        finish();
    }

    public static class RoomFragment extends Fragment {

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.room_service2, container, false);
        }
        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            List<ListItem> data = new ArrayList<>();
            data.add(new ListItem("스테이크\n 170,000원", R.drawable.steak));
            data.add(new ListItem("파스타\n 50,000원", R.drawable.pasta));
            data.add(new ListItem("리조또\n 50,000원", R.drawable.risotto));
            data.add(new ListItem("비프 웰링턴\n 298,000원", R.drawable.beefwell));
            data.add(new ListItem("피쉬앤칩스\n 55,000원", R.drawable.fish));
            data.add(new ListItem("피자\n 60,000원", R.drawable.pizza));
            data.add(new ListItem("치킨\n 55,000원", R.drawable.chicken));
            data.add(new ListItem("초밥\n 143,000원", R.drawable.sushi));
            data.add(new ListItem("라멘\n 50,000원", R.drawable.ramen));
            data.add(new ListItem("오마카세 출장\n 465,000원", R.drawable.omakase));

            CustomAdapter adapter = new CustomAdapter(requireContext(), data);

            ListView listView = view.findViewById(R.id.listView);
            listView.setAdapter(adapter);
        }

    }
    public static class RoomFragment2 extends Fragment {

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            // 프래그먼트의 UI를 초기화하고 반환
            return inflater.inflate(R.layout.room_service3, container, false);
        }
        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            List<ListItem> data = new ArrayList<>();
            data.add(new ListItem("마사지\n 100,000원", R.drawable.massage));
            data.add(new ListItem("피부관리\n 190,000원", R.drawable.skin));
            data.add(new ListItem("두피관리\n 149,000원", R.drawable.hairskin));
            data.add(new ListItem("메이크업\n 88,000원", R.drawable.makeup));
            data.add(new ListItem("무에타이 체험 풀코스\n 600,000원", R.drawable.full));
            data.add(new ListItem("정장대여\n 88,000원", R.drawable.suit));

            CustomAdapter adapter = new CustomAdapter(requireContext(), data);

            ListView listView = view.findViewById(R.id.listView);
            listView.setAdapter(adapter);
        }
    }
}