package com.example.team1_calendar;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    GridView monthView; // 그리드뷰 객체 생성
    TextView monthText; // 텍스트뷰 객체 생성
    MonthAdapter adt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        monthView = findViewById(R.id.monthView); //그리드뷰 객체 참조
        adt = new MonthAdapter(this); //어댑터 객체 생성
        monthView.setAdapter(adt); //그리드뷰에 어댑터 설정

        monthText = findViewById(R.id.monthText);
        setMonthText();

        Button monthPre = findViewById(R.id.monthPrevious);
        Button monthNext = findViewById(R.id.monthNext);

        // 이전버튼 클릭시 이전달로 이동하는 이벤트 정의
        monthPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adt.setPreMonth();
                adt.notifyDataSetChanged(); //어댑터 데이터 갱신하고 뷰 다시 뿌리기
                setMonthText();
            }
        });

        // 다음버튼 클릭시 다음달로 이동하는 이벤트 정의
        monthNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adt.setNextMonth();
                adt.notifyDataSetChanged(); //어댑터 데이터 갱신하고 뷰 다시 뿌리기
                setMonthText();
            }
        });


        // 그리드뷰 아이템 클릭시 현재 년월을 토스트 메시지로 표시
        monthView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int curYear = adt.getCurYear();
                int curMonth = adt.getCurMonth();
                adt.getCurDate();

                Toast.makeText(getApplicationContext(), curYear+"년 "+(curMonth+1)+"월", Toast.LENGTH_SHORT).show();
            }
        });

    }

    // 텍스트뷰에 현재 년월 표시
    public void setMonthText(){
        int curYear = adt.getCurYear();
        int curMonth = adt.getCurMonth();
        monthText.setText(curYear+"년 "+(curMonth+1)+"월");
    }
}