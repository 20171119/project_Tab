package com.cookandroid.project_tab;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import android.net.Uri;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    ListView list;
    LinearLayout ll;
    ProgressDialog pd;

    private final int GET_GALLERY_IMAGE = 200;
    private ImageView imageview;

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    public int inSampleSize = 1;

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private Uri fileUri;
    private static String basePath;

    public float imageViewRotation = 90;
    public String TAG = "Camera Example :: ";

    private Button takePicBtn;
    private ImageView resultView;
    private TextView imgPath;
    private Gallery customGallery;
    private CustomGalleryAdapter customGalAdapter;

    private String[] imgs;
//    ArrayList<ImageView> imgList = new ArrayList<>();
    ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9, image10,
            image11, image12, image13, image14, image15, image16, image17, image18, image19, image20;
    final int PICTURE_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        ts2.setIndicator("Gallery") ;
        tabHost1.addTab(ts2) ;

        // 세 번째 Tab. (탭 표시 텍스트:"TAB 3"), (페이지 뷰:"content3")
        TabHost.TabSpec ts3 = tabHost1.newTabSpec("Tab Spec 3") ;
        ts3.setContent(R.id.content3) ;
        ts3.setIndicator("Game") ;
        tabHost1.addTab(ts3) ;

        TabHost.TabSpec ts4 = tabHost1.newTabSpec("Tab Spec 4") ;
        ts4.setContent(R.id.content4) ;
        ts4.setIndicator("Gallery2") ;
        tabHost1.addTab(ts4) ;

        TabHost.TabSpec ts5 = tabHost1.newTabSpec("Tab Spec 5") ;
        ts5.setContent(R.id.content5) ;
        ts5.setIndicator("Gallery3") ;
        tabHost1.addTab(ts5) ;


        // Call 변수 미리 설정
        ll = (LinearLayout) findViewById(R.id.content1);
        list = (ListView) findViewById(R.id.listView1);
        LoadContactsAyscn lca = new LoadContactsAyscn();
        lca.execute();

        //Tap2 - Gallery
//        imageview = (ImageView)findViewById(R.id.imageView);
//        imageview.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//
//                Intent intent = new Intent(Intent.ACTION_PICK);
//                intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
//                startActivityForResult(intent, GET_GALLERY_IMAGE);
//            }
//        });

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

//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
//
//            Uri selectedImageUri = data.getData();
//            imageview.setImageURI(selectedImageUri);
//
//        }
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
                                case 4:
                                    image4.setImageURI(urione);
                                    break;
                                case 5:
                                    image5.setImageURI(urione);
                                    break;
                                case 6:
                                    image6.setImageURI(urione);
                                    break;
                                case 7:
                                    image7.setImageURI(urione);
                                    break;
                                case 8:
                                    image8.setImageURI(urione);
                                    break;
                                case 9:
                                    image9.setImageURI(urione);
                                    break;
                                case 10:
                                    image10.setImageURI(urione);
                                    break;
                                case 11:
                                    image11.setImageURI(urione);
                                    break;
                                case 12:
                                    image12.setImageURI(urione);
                                    break;
                                case 13:
                                    image13.setImageURI(urione);
                                    break;
                                case 14:
                                    image14.setImageURI(urione);
                                    break;
                                case 15:
                                    image15.setImageURI(urione);
                                    break;
                                case 16:
                                    image16.setImageURI(urione);
                                    break;
                                case 17:
                                    image17.setImageURI(urione);
                                    break;
                                case 18:
                                    image18.setImageURI(urione);
                                    break;
                                case 19:
                                    image19.setImageURI(urione);
                                    break;
                                case 20:
                                    image20.setImageURI(urione);
                                    break;
                            }
                        }
                    }
                } else if (uri != null) {
                    image1.setImageURI(uri);
                }
            }
        }
    }

    class LoadContactsAyscn extends AsyncTask<Void, Void, ArrayList<String>> {

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            pd = ProgressDialog.show(MainActivity.this, "Loading Contacts",
                    "Please Wait");
        }

        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            // TODO Auto-generated method stub
            ArrayList<String> contacts = new ArrayList<String>();

            Cursor c = getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    null, null, null);
            while (c.moveToNext()) {

                String contactName = c
                        .getString(c
                                .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phNumber = c
                        .getString(c
                                .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                contacts.add(contactName + ":" + phNumber);

            }
            Collections.sort(contacts);
            c.close();

            return contacts;
        }

        @Override
        protected void onPostExecute(ArrayList<String> contacts) {
            // TODO Auto-generated method stub
            super.onPostExecute(contacts);

            pd.cancel();

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    getApplicationContext(), R.layout.text, contacts);

            list.setAdapter(adapter);

        }

    }

}