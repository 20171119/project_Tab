package com.cookandroid.project_tab;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Map;

import android.net.Uri;
import android.widget.ImageView;
import com.cookandroid.project_tab.adapter.CallAdapter;
import com.cookandroid.project_tab.data.Call;

public class MainActivity extends AppCompatActivity {

    // Call
    ListView list;
    ProgressDialog pd;
    public static ArrayList<Call> callList;

    // Gallery
    final int PICTURE_REQUEST_CODE = 100;
    LinearLayout imgLayout;
    LinearLayout.LayoutParams imgParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Tab Host
        TabHost tabHost1 = (TabHost) findViewById(R.id.tabHost1) ;
        tabHost1.setup() ;

        // 첫 번째 Tab. (탭 표시 텍스트:"TAB 1"), (페이지 뷰:"content1")
        TabHost.TabSpec ts1 = tabHost1.newTabSpec("Tab Spec 1") ;
        ts1.setContent(R.id.content1) ;
        ts1.setIndicator("Call") ;
        tabHost1.addTab(ts1)  ;

        // 두 번째 Tab. (탭 표시 텍스트:"TAB 2"), (페이지 뷰:"content2")
        TabHost.TabSpec ts2 = tabHost1.newTabSpec("Tab Spec 2") ;
        ts2.setContent(R.id.content2) ;
        ts2.setIndicator("Photo") ;
        tabHost1.addTab(ts2) ;

        // 세 번째 Tab. (탭 표시 텍스트:"TAB 3"), (페이지 뷰:"content3")
        TabHost.TabSpec ts3 = tabHost1.newTabSpec("Tab Spec 3") ;
        ts3.setContent(R.id.content3) ;
        ts3.setIndicator("Game") ;
        tabHost1.addTab(ts3) ;

        TabHost.TabSpec ts4 = tabHost1.newTabSpec("Tab Spec 4") ;
        ts4.setContent(R.id.content4) ;
        ts4.setIndicator("API") ;
        tabHost1.addTab(ts4) ;

        // Call 변수 미리 설정
        list = (ListView) findViewById(R.id.listView1);
        LoadContactsAyscn lca = new LoadContactsAyscn();
        lca.execute();

        // Call List Click event 리스트뷰 클릭시 상세화면 보기 이벤트
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, CallActivity.class);
                intent.putExtra("POSITION", position);
                startActivity(intent);
            }
        });

        //Gallery ImageView
        Button btnImage = (Button)findViewById(R.id.btnImage);
        imgLayout = (LinearLayout) findViewById(R.id.imgLayout);

        // Gallery button Click event
        btnImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                //사진을 여러개 선택할수 있도록 한다
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),  PICTURE_REQUEST_CODE);
            }
        });

        // Gallery Camera
        Button btnCamera = (Button) findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PhotoActivity.class);
                startActivity(intent);
            }
        });

    }

    // Game - 새 게임
    public void buttonClick1(View v){
        Intent intent = new Intent(getApplicationContext(), GameActivity.class);
        startActivityForResult(intent,1002);
    }

    // Game - 도움말
    public void buttonClick2(View v){
        Intent intent = new Intent(getApplicationContext(), HelpActivity.class);
        startActivityForResult(intent,1002);
    }

    public void buttonClick3(View v){
        Intent intent = new Intent(getApplicationContext(), WeatherActivity.class);
        startActivity(intent);
    }

    public void buttonClick4(View v){
        Intent intent = new Intent(getApplicationContext(), MapActivity.class);
        startActivity(intent);
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        return super.onOptionsItemSelected(item);
//    }

    // make ImageView
    void makeImg(Uri uri) {
        imgParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 600);
        imgParams.setMargins(50,50,50,50);
        ImageView iv = new ImageView(this);  // 새로 추가할 imageView 생성
        iv.setImageURI(uri);  // imageView에 내용 추가
        iv.setLayoutParams(imgParams);  // imageView layout 설정
        imgLayout.addView(iv); // 기존 linearLayout에 imageView 추가
    }

    // Get phone gallery data.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICTURE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                //ClipData 또는 Uri를 가져온다
//                Uri uri = data.getData();
                ClipData clipData = data.getClipData();
                //이미지 URI 를 이용하여 이미지뷰에 순서대로 세팅한다.
                if (clipData != null) {
                    for (int j = 0; j < clipData.getItemCount(); j++) {
                        Uri urione = clipData.getItemAt(j).getUri();
                        makeImg(urione);
                    }
                }
            }
        }
    }


    class LoadContactsAyscn extends AsyncTask<Void, Void, ArrayList<Call>> {

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            pd = ProgressDialog.show(MainActivity.this, "Loading Contacts",
                    "Please Wait");
        }

        @Override
        protected ArrayList<Call> doInBackground(Void... params) {
            // TODO Auto-generated method stubco
            ArrayList<Map<String, String>> contacts = new ArrayList<Map<String, String>>();

            callList = new ArrayList<Call>();

            Cursor c = getContentResolver().query(
                    ContactsContract.Contacts.CONTENT_URI, null,
                    null, null, ContactsContract.Contacts.DISPLAY_NAME_PRIMARY+" asc");

            int caseNum = 0;
            while (c.moveToNext()) {
                //연락처 id값
                String id = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));

                //연락처 대표이름
                String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));

                //id로 전화 정보 조회
                Cursor phoneCursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null);
                String number="";
                //데이터가 있는 경우
                if(phoneCursor.moveToFirst()){
                    number = phoneCursor.getString(phoneCursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER));
                }

                caseNum = caseNum % 5;
                switch (caseNum) {
                    case 0:
                        callList.add(new Call(R.drawable.call3, name, number, R.drawable.people1));
                        break;
                    case 1:
                        callList.add(new Call(R.drawable.call3, name, number, R.drawable.people2));
                        break;
                    case 2:
                        callList.add(new Call(R.drawable.call3, name, number, R.drawable.people3));
                        break;
                    case 3:
                        callList.add(new Call(R.drawable.call3, name, number, R.drawable.people4));
                        break;
                    case 4:
                        callList.add(new Call(R.drawable.call3, name, number, R.drawable.people5));
                        break;

                }
                phoneCursor.close();
                caseNum++;
            } //end while

            c.close();
            return callList;
        }

        @Override
        protected void onPostExecute(ArrayList<Call> callList) {
            // TODO Auto-generated method stub
            super.onPostExecute(callList);
            pd.cancel();
            CallAdapter callAdapter = new CallAdapter(getApplicationContext(), callList);
            list.setAdapter(callAdapter);
        }
    }
}