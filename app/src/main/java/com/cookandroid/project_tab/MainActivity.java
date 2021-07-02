package com.cookandroid.project_tab;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.net.Uri;
import android.widget.ImageView;
import android.widget.Toast;

import com.cookandroid.project_tab.customadapter.CallAdapter;
import com.cookandroid.project_tab.data.Call;


public class MainActivity extends AppCompatActivity {

    ListView list;
    LinearLayout ll;
    ProgressDialog pd;


    ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9, image10,
            image11, image12, image13, image14, image15, image16, image17, image18, image19, image20;
    final int PICTURE_REQUEST_CODE = 100;

    public static ArrayList<Call> callList = new ArrayList<Call>();

    public void imgVisible() {
        for (int i =0 ; i < 20; i++) {
            switch (i) {
                case 0:
                    image1.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    image2.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    image3.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    image4.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    image5.setVisibility(View.VISIBLE);
                    break;
                case 5:
                    image6.setVisibility(View.VISIBLE);
                    break;
                case 6:
                    image7.setVisibility(View.VISIBLE);
                    break;
                case 7:
                    image8.setVisibility(View.VISIBLE);
                    break;
                case 8:
                    image9.setVisibility(View.VISIBLE);
                    break;
                case 9:
                    image10.setVisibility(View.VISIBLE);
                    break;
                case 10:
                    image11.setVisibility(View.VISIBLE);
                    break;
                case 11:
                    image12.setVisibility(View.VISIBLE);
                    break;
                case 12:
                    image13.setVisibility(View.VISIBLE);
                    break;
                case 13:
                    image14.setVisibility(View.VISIBLE);
                    break;
                case 14:
                    image15.setVisibility(View.VISIBLE);
                    break;
                case 15:
                    image16.setVisibility(View.VISIBLE);
                    break;
                case 16:
                    image17.setVisibility(View.VISIBLE);
                    break;
                case 17:
                    image18.setVisibility(View.VISIBLE);
                    break;
                case 18:
                    image19.setVisibility(View.VISIBLE);
                    break;
                case 19:
                    image20.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

    public void imgInvisible(int num) {
        for (; num < 20; num++) {
            switch (num) {
                case 0:
                    image1.setVisibility(View.GONE);
                    break;
                case 1:
                    image2.setVisibility(View.GONE);
                    break;
                case 2:
                    image3.setVisibility(View.GONE);
                    break;
                case 3:
                    image4.setVisibility(View.GONE);
                    break;
                case 4:
                    image5.setVisibility(View.GONE);
                    break;
                case 5:
                    image6.setVisibility(View.GONE);
                    break;
                case 6:
                    image7.setVisibility(View.GONE);
                    break;
                case 7:
                    image8.setVisibility(View.GONE);
                    break;
                case 8:
                    image9.setVisibility(View.GONE);
                    break;
                case 9:
                    image10.setVisibility(View.GONE);
                    break;
                case 10:
                    image11.setVisibility(View.GONE);
                    break;
                case 11:
                    image12.setVisibility(View.GONE);
                    break;
                case 12:
                    image13.setVisibility(View.GONE);
                    break;
                case 13:
                    image14.setVisibility(View.GONE);
                    break;
                case 14:
                    image15.setVisibility(View.GONE);
                    break;
                case 15:
                    image16.setVisibility(View.GONE);
                    break;
                case 16:
                    image17.setVisibility(View.GONE);
                    break;
                case 17:
                    image18.setVisibility(View.GONE);
                    break;
                case 18:
                    image19.setVisibility(View.GONE);
                    break;
                case 19:
                    image20.setVisibility(View.GONE);
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        ts4.setIndicator("New") ;
        tabHost1.addTab(ts4) ;

        // Call 변수 미리 설정
//        ll = (LinearLayout) findViewById(R.id.content1);
        list = (ListView) findViewById(R.id.listView1);
        LoadContactsAyscn lca = new LoadContactsAyscn();
        lca.execute();
        // 이벤트 처리
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*Toast.makeText(getApplicationContext(),
                                parent.getItemAtPosition(position).toString(),
                                Toast.LENGTH_LONG).show();*/
                Intent intent =
                        new Intent(MainActivity.this, CallActivity.class);
                intent.putExtra("POSITION", position);
                startActivity(intent);
            }
        });

        //UI
        image1 = (ImageView)findViewById(R.id.img1);
        image2 = (ImageView)findViewById(R.id.img2);
        image3 = (ImageView)findViewById(R.id.img3);
        image4 = (ImageView)findViewById(R.id.img4);
        image5 = (ImageView)findViewById(R.id.img5);
        image6 = (ImageView)findViewById(R.id.img6);
        image7 = (ImageView)findViewById(R.id.img7);
        image8 = (ImageView)findViewById(R.id.img8);
        image9 = (ImageView)findViewById(R.id.img9);
        image10 = (ImageView)findViewById(R.id.img10);
        image11 = (ImageView)findViewById(R.id.img11);
        image12 = (ImageView)findViewById(R.id.img12);
        image13 = (ImageView)findViewById(R.id.img13);
        image14 = (ImageView)findViewById(R.id.img14);
        image15 = (ImageView)findViewById(R.id.img15);
        image16 = (ImageView)findViewById(R.id.img16);
        image17 = (ImageView)findViewById(R.id.img17);
        image18 = (ImageView)findViewById(R.id.img18);
        image19 = (ImageView)findViewById(R.id.img19);
        image20 = (ImageView)findViewById(R.id.img20);

        Button btnImage = (Button)findViewById(R.id.btnImage);
        imgInvisible(0);
        btnImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                //사진을 여러개 선택할수 있도록 한다
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),  PICTURE_REQUEST_CODE);
                imgVisible();
            }
        });

    }

    // 새게임
    public void buttonClick1(View v){
        Intent intent = new Intent(getApplicationContext(), GameActivity.class);
        startActivityForResult(intent,1002);
    }

    //도움말버튼 이벤트
    public void buttonClick2(View v){
        Intent intent = new Intent(getApplicationContext(), HelpActivity.class);
        startActivityForResult(intent,1002);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICTURE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                //기존 이미지 지우기
                image1.setImageResource(0);
                image2.setImageResource(0);
                image3.setImageResource(0);
                image4.setImageResource(0);
                image5.setImageResource(0);
                image6.setImageResource(0);
                image7.setImageResource(0);
                image8.setImageResource(0);
                image9.setImageResource(0);
                image10.setImageResource(0);
                image11.setImageResource(0);
                image12.setImageResource(0);
                image13.setImageResource(0);
                image14.setImageResource(0);
                image15.setImageResource(0);
                image16.setImageResource(0);
                image17.setImageResource(0);
                image18.setImageResource(0);
                image19.setImageResource(0);
                image20.setImageResource(0);

                //ClipData 또는 Uri를 가져온다
                Uri uri = data.getData();
                ClipData clipData = data.getClipData();

                int num = 0;
                //이미지 URI 를 이용하여 이미지뷰에 순서대로 세팅한다.
                if (clipData != null) {

                    for (int i = 0; i < 20; i++) {
                        if (i < clipData.getItemCount()) {
                            Uri urione = clipData.getItemAt(i).getUri();
                            switch (i) {
                                case 0:
                                    image1.setImageURI(urione);
                                    break;
                                case 1:
                                    image2.setImageURI(urione);
                                    break;
                                case 2:
                                    image3.setImageURI(urione);
                                    break;
                                case 3:
                                    image4.setImageURI(urione);
                                    break;
                                case 4:
                                    image5.setImageURI(urione);
                                    break;
                                case 5:
                                    image6.setImageURI(urione);
                                    break;
                                case 6:
                                    image7.setImageURI(urione);
                                    break;
                                case 7:
                                    image8.setImageURI(urione);
                                    break;
                                case 8:
                                    image9.setImageURI(urione);
                                    break;
                                case 9:
                                    image10.setImageURI(urione);
                                    break;
                                case 10:
                                    image11.setImageURI(urione);
                                    break;
                                case 11:
                                    image12.setImageURI(urione);
                                    break;
                                case 12:
                                    image13.setImageURI(urione);
                                    break;
                                case 13:
                                    image14.setImageURI(urione);
                                    break;
                                case 14:
                                    image15.setImageURI(urione);
                                    break;
                                case 15:
                                    image16.setImageURI(urione);
                                    break;
                                case 16:
                                    image17.setImageURI(urione);
                                    break;
                                case 17:
                                    image18.setImageURI(urione);
                                    break;
                                case 18:
                                    image19.setImageURI(urione);
                                    break;
                                case 19:
                                    image20.setImageURI(urione);
                                    break;
                            }
                            num++;
                        }
                    }
                } else if (uri != null) {
                    image1.setImageURI(uri);
                }
                System.out.println("uri_num: " + num);
                imgInvisible(num);
            }
        }
    }

    class LoadContactsAyscn extends AsyncTask<Void, Void, ArrayList<Map<String,String>>> {

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            pd = ProgressDialog.show(MainActivity.this, "Loading Contacts",
                    "Please Wait");
        }

        @Override
        protected ArrayList<Map<String, String>> doInBackground(Void... params) {
            // TODO Auto-generated method stub
            ArrayList<Map<String, String>> contacts = new ArrayList<Map<String, String>>();

            Cursor c = getContentResolver().query(
                    ContactsContract.Contacts.CONTENT_URI, null,
                    null, null, ContactsContract.Contacts.DISPLAY_NAME_PRIMARY+" asc");

            int caseNum = 0;
            while (c.moveToNext()) {
                HashMap<String, String> map = new HashMap<String, String>();


                //연락처 id값
                String id = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));

                //연락처 대표이름
                String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));

                map.put("name", name);

                //id로 전화 정보 조회
                Cursor phoneCursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null);
                String number="";
                //데이터가 있는 경우
                if(phoneCursor.moveToFirst()){
                    number = phoneCursor.getString(phoneCursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER));
                    map.put("phone", number);
                }

                caseNum = caseNum % 5;
                System.out.println("caseNum: " + caseNum);
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
                contacts.add(map);
                caseNum++;
            } //end while

            c.close();
            return contacts;
        }

        @Override
        protected void onPostExecute(ArrayList<Map<String, String>> contacts) {
            // TODO Auto-generated method stub
            super.onPostExecute(contacts);
            pd.cancel();
            CallAdapter callAdapter = new CallAdapter(getApplicationContext(), callList);
            list.setAdapter(callAdapter);
        }
    }

}