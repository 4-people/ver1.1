package com.example.programmingknowledge.mybalance_v11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;


public class SettingBalanceActivity extends AppCompatActivity {

    TextView sleepCountTv, workCountTv, studyCountTv, exerciseCountTv, leisureCountTv, othersCountTv;
    double sleepCount, workCount, studyCount, exerciseCount, leisureCount, others;
    Button sleepMinus, workMinus, studyMinus, exerciseMinus, leisureMinus;
    Button sleepPlus, workPlus, studyPlus, exercisePlus, leisurePlus;
    View sleepView, workView, studyView, exerciseView, leisureView;
    CheckBox checkMon, checkTue, checkWed, checkThu, checkFri, checkSat, checkSun;
    ArrayList<CheckBox> checkBoxArrayList = new ArrayList<CheckBox>();
    String checkedWeek="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_balance);
        this.InitializeView();


        //버튼 누르고있으면 반복 실행되게 하는 repeat메소드
        this.repeat(sleepMinus);
        this.repeat(sleepPlus);
        this.repeat(workMinus);
        this.repeat(workPlus);
        this.repeat(studyMinus);
        this.repeat(studyPlus);
        this.repeat(exerciseMinus);
        this.repeat(exercisePlus);
        this.repeat(leisureMinus);
        this.repeat(leisurePlus);

    }

    public void repeat(final Button button){
        button.setOnTouchListener(new View.OnTouchListener() {

            private Handler mHandler;

            @Override public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (mHandler != null) return true;
                        mHandler = new Handler();
                        mHandler.postDelayed(mAction, 300);
                        break;
                    case MotionEvent.ACTION_UP:
                        if (mHandler == null) return true;
                        mHandler.removeCallbacks(mAction);
                        mHandler = null;
                        break;
                }
                return false;
            }

            Runnable mAction = new Runnable() {
                @Override public void run() {
                    MyOnClick(button);
                    mHandler.postDelayed(this, 100);
                }
            };

        });
    }

    public void InitializeView()
    {
        sleepCountTv = (TextView)findViewById(R.id.sleepCount);
        sleepCount = parseInt(sleepCountTv.getText().toString());
        sleepMinus = (Button)findViewById(R.id.sleepMinus);
        sleepPlus = (Button)findViewById(R.id.sleepPlus);
        sleepView = (View)findViewById(R.id.sleep);

        workCountTv = (TextView)findViewById(R.id.workCount);
        workCount = parseInt(workCountTv.getText().toString());
        workMinus = (Button)findViewById(R.id.workMinus);
        workPlus = (Button)findViewById(R.id.workPlus);
        workView = (View)findViewById(R.id.work);

        studyCountTv = (TextView)findViewById(R.id.studyCount);
        studyCount = parseInt(studyCountTv.getText().toString());
        studyMinus = (Button)findViewById(R.id.studyMinus);
        studyPlus = (Button)findViewById(R.id.studyPlus);
        studyView = (View)findViewById(R.id.study);

        exerciseCountTv = (TextView)findViewById(R.id.exerciseCount);
        exerciseCount = parseInt(exerciseCountTv.getText().toString());
        exerciseMinus = (Button)findViewById(R.id.exerciseMinus);
        exercisePlus = (Button)findViewById(R.id.exercisePlus);
        exerciseView = (View)findViewById(R.id.exercise);

        leisureCountTv = (TextView)findViewById(R.id.leisureCount);
        leisureCount = parseInt(leisureCountTv.getText().toString());
        leisureMinus = (Button)findViewById(R.id.leisureMinus);
        leisurePlus = (Button)findViewById(R.id.leisurePlus);
        leisureView = (View)findViewById(R.id.leisure);

        othersCountTv = (TextView)findViewById(R.id.othersCount);

        checkMon = (CheckBox)findViewById(R.id.CheckMon);
        checkTue = (CheckBox)findViewById(R.id.CheckTue);
        checkWed = (CheckBox)findViewById(R.id.CheckWed);
        checkThu = (CheckBox)findViewById(R.id.CheckThu);
        checkFri = (CheckBox)findViewById(R.id.CheckFri);
        checkSat = (CheckBox)findViewById(R.id.CheckSat);
        checkSun = (CheckBox)findViewById(R.id.CheckSun);

        checkBoxArrayList.add(checkMon);
        checkBoxArrayList.add(checkTue);
        checkBoxArrayList.add(checkWed);
        checkBoxArrayList.add(checkThu);
        checkBoxArrayList.add(checkFri);
        checkBoxArrayList.add(checkSat);
        checkBoxArrayList.add(checkSun);
    }

    public void MyOnClick(View view)
    {
        others = 24-(sleepCount+workCount+studyCount+exerciseCount+leisureCount);
        switch (view.getId()) {
            case R.id.sleepMinus:
                if(sleepCount==0) break;
                sleepCount-=0.5;
                sleepCountTv.setText(""+sleepCount);
                sleepView.getLayoutParams().width=(int)(sleepCount*40);
                sleepView.requestLayout();
                others = 24-(sleepCount+workCount+studyCount+exerciseCount+leisureCount);
                othersCountTv.setText(""+others);
                break;
            case R.id.sleepPlus:
                if(others==0) break;
                sleepCount+=0.5;
                sleepCountTv.setText(""+sleepCount);
                sleepView.getLayoutParams().width=(int)(sleepCount*40);
                sleepView.requestLayout();
                others = 24-(sleepCount+workCount+studyCount+exerciseCount+leisureCount);
                othersCountTv.setText(""+others);
                break;

            case R.id.workMinus:
                if(workCount==0) break;
                workCount-=0.5;
                workCountTv.setText(""+workCount);
                workView.getLayoutParams().width=(int)(workCount*40);
                workView.requestLayout();
                others = 24-(sleepCount+workCount+studyCount+exerciseCount+leisureCount);
                othersCountTv.setText(""+others);
                break;
            case R.id.workPlus:
                if(others==0) break;
                workCount+=0.5;
                workCountTv.setText(""+workCount);
                workView.getLayoutParams().width=(int)(workCount*40);
                workView.requestLayout();
                others = 24-(sleepCount+workCount+studyCount+exerciseCount+leisureCount);
                othersCountTv.setText(""+others);
                break;

            case R.id.studyMinus:
                if(studyCount==0) break;
                studyCount-=0.5;
                studyCountTv.setText(""+studyCount);
                studyView.getLayoutParams().width=(int)(studyCount*40);
                studyView.requestLayout();
                others = 24-(sleepCount+workCount+studyCount+exerciseCount+leisureCount);
                othersCountTv.setText(""+others);
                break;
            case R.id.studyPlus:
                if(others==0) break;
                studyCount+=0.5;
                studyCountTv.setText(""+studyCount);
                studyView.getLayoutParams().width=(int)(studyCount*40);
                workView.requestLayout();
                others = 24-(sleepCount+workCount+studyCount+exerciseCount+leisureCount);
                othersCountTv.setText(""+others);
                break;

            case R.id.exerciseMinus:
                if(exerciseCount==0) break;
                exerciseCount-=0.5;
                exerciseCountTv.setText(""+exerciseCount);
                exerciseView.getLayoutParams().width=(int)(exerciseCount*40);
                exerciseView.requestLayout();
                others = 24-(sleepCount+workCount+studyCount+exerciseCount+leisureCount);
                othersCountTv.setText(""+others);
                break;
            case R.id.exercisePlus:
                if(others==0) break;
                exerciseCount+=0.5;
                exerciseCountTv.setText(""+exerciseCount);
                exerciseView.getLayoutParams().width=(int)(exerciseCount*40);
                workView.requestLayout();
                others = 24-(sleepCount+workCount+studyCount+exerciseCount+leisureCount);
                othersCountTv.setText(""+others);
                break;

            case R.id.leisureMinus:
                if(leisureCount==0) break;
                leisureCount-=0.5;
                leisureCountTv.setText(""+leisureCount);
                leisureView.getLayoutParams().width=(int)(leisureCount*40);
                leisureView.requestLayout();
                others = 24-(sleepCount+workCount+studyCount+exerciseCount+leisureCount);
                othersCountTv.setText(""+others);
                break;
            case R.id.leisurePlus:
                if(others==0) break;
                leisureCount+=0.5;
                leisureCountTv.setText(""+leisureCount);
                leisureView.getLayoutParams().width=(int)(leisureCount*40);
                workView.requestLayout();
                others = 24-(sleepCount+workCount+studyCount+exerciseCount+leisureCount);
                othersCountTv.setText(""+others);
                break;
        }
    }

    public void setBalance(View view){

        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View outerview = inflater.inflate(R.layout.content_balance_list, null);

        Intent outerIntent = new Intent(this, BalanceListActivity.class);


        //db 추가하기
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        for(int i=0;i<checkBoxArrayList.size();i++){
            if(checkBoxArrayList.get(i).isChecked()){
                checkedWeek += checkBoxArrayList.get(i).getText();
            }
        }
        db.execSQL("insert into tb_goalbalance (sleep, work, study, exercise, leisure, other, week) values ("+
                sleepCount+","+workCount+","+studyCount+","+exerciseCount+","+leisureCount+","+others+",'"+checkedWeek+"')");
        db.close();

        onBackPressed();

//        CardView cardView = new CardView(outerIntent.this);
//      cardView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        cardView.setCardBackgroundColor(383838);

//        outerview.setContent
//        Intent intent=new Intent(MainActivity.this, SettingActivity.class);
//        startActivity(intent);

    }

}
