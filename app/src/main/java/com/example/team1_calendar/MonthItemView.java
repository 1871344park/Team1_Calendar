package com.example.team1_calendar;

import android.content.Context;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.Gravity;

import androidx.appcompat.widget.AppCompatTextView;

public class MonthItemView extends AppCompatTextView {

    private MonthItem item;

    public MonthItemView(Context context){
        super(context);
        init();
    }

    // 뷰와 상호작용 할 수 있도록 하는 코드
    public MonthItemView(Context context, AttributeSet attrs){
        super(context, attrs);
        init();
    }

    private void init(){

        setBackgroundColor(Color.WHITE);
    }

    public MonthItem getItem(){

        return item;
    }

    public void setItem(MonthItem item){
        this.item = item;

        int day = item.getDay();

        // 날짜가 0이 아닐때 실행되는 코드
        if (day != 0) {
            setText(String.valueOf(day));
            setGravity(Gravity.CENTER);
            setTextColor(Color.BLACK);
            setTextSize(20);
        }

        // 날짜가 0이면 공백으로 남겨둠
        else{
            setText("");
        }
    }
}