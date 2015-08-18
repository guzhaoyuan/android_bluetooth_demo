package com.example.gzy.demo2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * 此处不断刷新交易记录
 * 有开始按钮，开启电池
 * 有暂停和停止按钮，分别暂停和停止交易
 * 按下暂停按钮，交易暂停，电池断电
 * 按下停止按钮，交易完成，跳出《交易清单》对话框和返回按钮
 */
public class DealInfo extends AppCompatActivity {

    private Button start ;
    private Button stop ;
    private Button pause ;

    @Override
    protected void onResume(){
        super.onResume();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_info);
        setupVariables();
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送开始指令
                //开启数据收发线程
                start.setVisibility(View.INVISIBLE);
                pause.setVisibility(View.VISIBLE);
                stop.setVisibility(View.VISIBLE);
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送电池关闭指令
                pause.setVisibility(View.INVISIBLE);
                start.setVisibility(View.VISIBLE);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送电池关闭指令
                //处理数据账单，跳出账单信息对话框(含确定按钮)
                //关闭socket，返回info.activity
                Intent intent = new Intent(DealInfo.this,info.class);
                startActivity(intent);
                DealInfo.this.finish();
            }
        });
    }

    private void setupVariables(){
        start =(Button)findViewById(R.id.start);
        pause = (Button)findViewById(R.id.pause);
        stop = (Button)findViewById(R.id.stop);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_deal_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
