package com.example.tjddl.beginning_exam;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView tv_name, tv_tel, tv_pay, tv_disc, tv_totalPay;
    EditText et_chic, et_piz, et_ham;
    CheckBox cb;
    RadioButton rb_bev, rb_coup;
    ImageView iv;
    String name ;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        rb_bev.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if( rb_bev.isChecked() ){
                        rb_bev.setChecked(true);
                        iv.setImageResource(R.drawable.beverage);
                }
            }
        });
        rb_coup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rb_coup.isChecked()){
                    rb_coup.setChecked(true);
                    iv.setImageResource(R.drawable.coupon);
                }
            }
        });




    }

    public void init() {
        linearLayout = (LinearLayout)findViewById(R.id.Linear1);
        tv_name = (TextView) findViewById(R.id.textView_name);
        tv_tel = (TextView) findViewById(R.id.textView_tel);
        tv_pay = (TextView) findViewById(R.id.textView_payway);
        tv_disc = (TextView) findViewById(R.id.textView_discount);
        tv_totalPay = (TextView) findViewById(R.id.textView_totalPay);
        et_chic = (EditText) findViewById(R.id.editText_chick);
        et_ham = (EditText) findViewById(R.id.editText_ham);
        et_piz = (EditText) findViewById(R.id.editText_pizza);
        cb = (CheckBox)findViewById(R.id.checkBox);
        rb_bev = (RadioButton)findViewById(R.id.radioButton_beverage);
        rb_coup=(RadioButton)findViewById(R.id.radioButton_coup);
        iv = (ImageView)findViewById(R.id.imageView);
    }

    public void onClick(View v){
        if(v.getId() == R.id.button_enroll){

            AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
            View dlgview = v.inflate(MainActivity.this, R.layout.dialog,null);
            final EditText editText_dlgName = (EditText) dlgview.findViewById(R.id.editText_dlgName);
            final EditText editText_dlgTel = (EditText) dlgview.findViewById(R.id.editText_dlgTel);
            final RadioButton radioButton_cash = (RadioButton)dlgview.findViewById(R.id.radioButton_cash);
            final RadioButton radioButton_card = (RadioButton)dlgview.findViewById(R.id.radioButton_card);
            RadioButton radioButton_coup = (RadioButton)dlgview.findViewById(R.id.radioButton_coupon);

            dlg.setView(dlgview);
            dlg.setPositiveButton("입력", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                   name = editText_dlgName.getText().toString();
                   String tel =  editText_dlgTel.getText().toString();
                    String payway =null;
                    if( radioButton_card.isChecked() ) {
                        payway = "카드";
                    }
                    else if( radioButton_cash.isChecked() ) {
                        payway = "현금";
                    }
                    else {
                        payway = "쿠폰";
                    }
                    if(tel != null && tel !=null && payway!=null){
                        tv_name.setText("주문자 이름 :"+name);
                        tv_tel.setText("주문자 전화 :"+tel);
                        tv_pay.setText("결재방법 :"+payway);
                    }
                }
            })
                    .setNegativeButton("취소",null)
                    .setTitle("주문자 정보 입력")
                    .setIcon(R.mipmap.ic_launcher);
            dlg.show();
        }

        if(v.getId() == R.id.button_Order){
            int piz = Integer.parseInt(et_piz.getText().toString());
            int ham = Integer.parseInt(et_ham.getText().toString());
            int chic = Integer.parseInt(et_chic.getText().toString());
            int total=12000*piz + 5000*ham + 15000*chic;
            if(cb.isChecked()){
                total = (int) ( total * 0.85);
                tv_disc.setText("할인여부 : "+"할인됨");
            }
            if(total >= 30000) total -= 3000;
            else tv_disc.setText("할인여부 : 없음");
            tv_totalPay.setText("결재금액 : "+total);
            tv_totalPay.setTextColor(Color.RED);
            Toast.makeText(MainActivity.this,name+"님 주문접수되었습니다.",Toast.LENGTH_SHORT).show();

        }

    } // Onclick End

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.imgBig){
            item.setChecked(true);
            iv.setScaleY(2f);
            iv.setScaleX(2f);
        }
        if(item.getItemId() == R.id.imgSmall){
            item.setChecked(true);
            iv.setScaleX(1f);
            iv.setScaleY(1f);
        }
        if(item.getItemId() == R.id.menu_pink){
            item.setChecked(true);
            linearLayout.setBackgroundColor(Color.argb(100,255,192,203));
        }
        if(item.getItemId() == R.id.menu_sky){
            item.setChecked(true);
            linearLayout.setBackgroundColor(Color.argb(100,135,206,250));

        }
        if(item.getItemId() == R.id.menu_white){
            item.setChecked(true);
           linearLayout.setBackgroundColor(Color.WHITE);
        }
        return super.onOptionsItemSelected(item);
    }
}
