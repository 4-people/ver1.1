package com.example.programmingknowledge.mybalance_v11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.content.Intent;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BalanceListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //db읽기
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select sleep, work, study, exercise, leisure, other, week from tb_goalbalance", null);


        //카드뷰 생성
        RelativeLayout layout = (RelativeLayout)findViewById(R.id.relative);
        /*while(cursor.moveToNext()){
            CardView card = new CardView(mContext);
            //아랫줄 한줄 저거뭐지
            CardView.LayoutParams params = new CardView.LayoutParams(CardView.LayoutParams.WRAP_CONTENT, CardView.LayoutParams.WRAP_CONTENT);
            card.setLayoutParams(params);
            card.setMinimumHeight(100);
            card.setMinimumWidth(395);
            card.setContentPadding(15, 15, 15, 15);
            TextView tv = new TextView(mContext);
            tv.setText(cursor.getString(0));
            card.addView(tv);*/


        TextView tv = new TextView(this);
        tv.setPadding(15, 15, 15, 15);
        cursor.moveToFirst();
        tv.setText(cursor.getString(6));
        layout.addView(tv);
        //}


        //플러스 버튼 누르면 목표밸런스 추가
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent intent=new Intent(BalanceListActivity.this, SettingBalanceActivity.class);
                startActivity(intent);
            }
        });

        //카드뷰 오래 누르면 카드뷰 삭제
        CardView cardview = findViewById(R.id.cardView01);
        // AlertDialog 빌더를 이용해 종료시 발생시킬 창을 띄운다
        final AlertDialog.Builder alBuilder = new AlertDialog.Builder(this);
        cardview.setOnLongClickListener(new View.OnLongClickListener() {

            public boolean onLongClick(final View view) {
                alBuilder.setMessage("삭제하시겠습니까?");

                // "예" 버튼을 누르면 실행되는 리스너
                alBuilder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        RelativeLayout relative = (RelativeLayout) findViewById(R.id.relative) ;
                        relative.removeView(view);
                        //db삭제 구현하기(아래)

                    }
                });
                // "아니오" 버튼을 누르면 실행되는 리스너
                alBuilder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return; // 아무런 작업도 하지 않고 돌아간다
                    }
                });
                alBuilder.setTitle("목표 밸런스 삭제");
                alBuilder.show(); // AlertDialog.Bulider로 만든 AlertDialog를 보여준다.

                return false;
            }

        });

    }

    public void editBalance(View view){
        Intent intent=new Intent(BalanceListActivity.this, SettingBalanceActivity.class);
        startActivity(intent);
    }

}