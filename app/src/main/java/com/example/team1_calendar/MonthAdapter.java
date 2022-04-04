package com.example.team1_calendar;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import java.util.Calendar;

public class MonthAdapter extends BaseAdapter {


    Calendar cal; // 캘린더 클래스 객체 생성
    Context mContext; // 콘텍스트 클래스 객체 생성 // 왜 Context 클래스가 필요한가 : 어플리케이션과 관련된 정보에 접근하고자 하거나 어플리케이션과 연관된 시스템 레벨의 함수를 호출하고자 할 때 사용된다.
    MonthItem[] items;
    int curYear;
    int curMonth;

    MonthAdapter(Context context){
        super();
        mContext = context;
        init();
    }

    public void init(){
        cal = Calendar.getInstance(); //Calendar 객체 가져오기
        items = new MonthItem[7*6]; //아이템 크기 결정 7행 6열
        MonthSize(); //날짜 계산해서 items[] 배열 값 설정
    }

    public void MonthSize(){ // 각 달의 날짜의 수 계산하는 함수
        for(int i=0; i<items.length; i++){ //items[] 모든 값 0으로 초기화
            items[i] = new MonthItem(0);
        }

        cal.set(Calendar.DAY_OF_MONTH, 1); //1일로 설정

        int startDay = cal.get(Calendar.DAY_OF_WEEK); //현재 달 1일의 요일 (1: 일요일, . . . 7: 토요일)
        int lastDay = cal.getActualMaximum(Calendar.DATE); //달의 마지막 날짜

        int count = 1;
        for(int i=startDay-1; i<startDay-1+lastDay; i++){ //1일의 요일에 따라 시작위치 다르고 마지막 날짜까지 값 지정
            items[i] = new MonthItem(count);
            count++;
        }

        curYear = cal.get(Calendar.YEAR);
        curMonth = cal.get(Calendar.MONTH);
    }

    public void setPreMonth(){ //한 달 앞으로 가고 다시 계산
        cal.add(Calendar.MONTH, -1);
        MonthSize();
    }

    public void setNextMonth(){
        cal.add(Calendar.MONTH, 1); //한 달 뒤로가고 다시 계산
        MonthSize();
    }

    @Override
    public int getCount() {

        return items.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MonthItemView view = new MonthItemView(mContext);
        MonthItem item = items[position];
        view.setItem(item); //날짜 값이 0이면 ""으로, 아니면 날짜값으로 TextView의 Text 지정
        GridView.LayoutParams params = new GridView.LayoutParams( GridView.LayoutParams.MATCH_PARENT,150);
        view.setLayoutParams(params);

        return view; //뷰 뿌려주기
    }

    @Override
    public Object getItem(int position) {

        return items[position];
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    public int getCurYear(){

        return curYear;
    }

    public int getCurMonth(){

        return curMonth;
    }

    public void getCurDate() {
    }
}