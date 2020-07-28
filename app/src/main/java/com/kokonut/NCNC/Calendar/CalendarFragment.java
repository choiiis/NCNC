package com.kokonut.NCNC.Calendar;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.kokonut.NCNC.Calendar.Calendar_PopupFragment;
import com.kokonut.NCNC.Calendar.OneDayDecorator;
import com.kokonut.NCNC.Calendar.customDecorator;
import com.kokonut.NCNC.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

//import org.threeten.bp.DayOfWeek;

import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static android.app.Activity.RESULT_OK;

public class CalendarFragment extends Fragment implements Calendar_PopupFragment.uploadDialogInterface {
    public static final int POPUP_REQUEST_CODE = 1001;

    TextView textView1;
    ViewGroup viewGroup;
    CalendarDay clickedDate;
    MaterialCalendarView materialCalendarView;
    String popupResult;
    Drawable drawable;
    FragmentTransaction ft;

    int temp = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("wow", "customDecorator: is null 77778888");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_calendar,container,false);
        materialCalendarView = viewGroup.findViewById(R.id.calendar_view);
        Log.d("wow", "customDecorator: is null 7777");

        temp = 2;


        textView1 = viewGroup.findViewById(R.id.plan_text1);

//        drawable = this.getResources().getDrawable(R.drawable.calendar_emptycircle_inside_purple);

        drawable = this.getResources().getDrawable(R.drawable.calendar_circle_inside);


        if(materialCalendarView == null){
            Log.d("wow", "customDecorator: is null 11");
        }
        else
            Log.d("wow", "customDecorator: is null 22");

        initCalendar();
/*
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.addToBackStack(null);
        ft.commit();
        */

        //FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction().add(R.id.main_layout, this);
        // 해당 transaction 을 Back Stack 에 저장

        // transaction 실행

        return viewGroup;
    }

    @Override
    public void senddata(String popupResult) {
        this.popupResult = popupResult;
        Log.d("wow", "customDecorator: is null 33");

        if(materialCalendarView == null){
            Log.d("wow", "customDecorator: is null 551");
        }
        else
            Log.d("wow", "customDecorator: is null 662");

//        devidepopupValue(popupResult);
    }



    @Override
    public void onStart(){
        super.onStart();
        textView1.setText("나의 세차 일정");

        Log.d("wow", "customDecorator: is null 77771");
    }

    @Override
    public void onResume(){
        super.onResume();

        Log.d("wow", "customDecorator: is null 77772");

    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d("wow", "customDecorator: is null 77773");

    }

    public void initCalendar() {
        if(materialCalendarView == null){
            Log.d("wow", "customDecorator: is null 553");
        }
        else
            Log.d("wow", "customDecorator: is null 664");



        HashSet<Date> events = new HashSet<>();
        events.add(new Date());

        materialCalendarView.state().edit()
                    //.setFirstDayOfWeek(Calendar.MONDAY)
                .setMinimumDate(CalendarDay.from(2020, 1, 1))
                .setMaximumDate(CalendarDay.from(2030, 11, 30))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        /*오늘 날짜에 초록색 효과를 줌*/
        OneDayDecorator oneDayDecorator = new OneDayDecorator();
        materialCalendarView.addDecorators(oneDayDecorator);

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

                Calendar_PopupFragment calendar_popupfragment = new Calendar_PopupFragment();
                calendar_popupfragment.show(getFragmentManager(), "tag");
                Log.d("111111111116", "1111");

                clickedDate = date;

            }
        });
    }

    private void decideWashPlan(){

    }

    private void callUploadDialog()
    {
/*
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.addToBackStack(null);
        ft.commit();
        */

        Calendar_PopupFragment fragment = new Calendar_PopupFragment();
        fragment.setTargetFragment(this, 0);

        FragmentManager manager;

        manager = getFragmentManager();
        ft = manager.beginTransaction();


        //FragmentTransaction ft = getFragmentManager().beginTransaction();

        //ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_in);
        fragment.show(ft, "UploadDialogFragment");
        fragment.setCancelable(false);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d("wow", "1133");

        if(temp == 1)
            Log.d("wow3", "1133");
        else
            Log.d("wow3", "no");

        super.onActivityResult(requestCode, resultCode, data);
        if(POPUP_REQUEST_CODE == 1001 && resultCode == RESULT_OK){
            Log.d("wow", "11331");

            String value = data.getExtras().getString("at");
            Log.d("wow", "data " + value);

            String[] checkedList = value.split("_");
            Log.d("wow2", "11331");

            Log.d("wow3", "11331");

            devidepopupValue(checkedList);


            //  customDecorator CustomDecorator = new customDecorator(this, clickedDate, checkedList);
            // materialCalendarView.addDecorators(CustomDecorator);

        }else{
            Log.d("wow", "113312111");
        }

        Log.d("wow", "113312");

    }

    public void devidepopupValue(String []checkedList){

        /*value 가 내부 , 외부 , 전체 인지에 따라 동그라미 아래 text 달리해줄것 */

        customDecorator CustomDecorator = new customDecorator(getActivity(), drawable, clickedDate, checkedList);

        Log.d("wow", "customDecorator: is null 44");

        if(materialCalendarView == null){
            Log.d("wow", "customDecorator: is null 55");
        }
        else {
            Log.d("wow", "customDecorator: is null 66");

        }

        materialCalendarView.addDecorators(CustomDecorator);

        Log.d("wow", "customDecorator: is null 77");

    }
}