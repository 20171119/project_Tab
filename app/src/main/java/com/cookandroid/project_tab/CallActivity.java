package com.cookandroid.project_tab;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cookandroid.project_tab.data.Call;

/**
 * Created by Administrator on 2016-04-25.
 */
public class CallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail);

        ImageView img = (ImageView)findViewById(R.id.img);
        TextView title = (TextView)findViewById(R.id.txtTitle);
        TextView content = (TextView)findViewById(R.id.txtContent);
        Button btn = (Button)findViewById(R.id.close);

        Intent intent = getIntent();
        int id = intent.getExtras().getInt("POSITION");

        Call dto = MainActivity.callList.get(id);

        img.setImageResource(dto.getDiffImg());
        title.setText(dto.getName());
        content.setText(dto.getPhoneNum());

        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
