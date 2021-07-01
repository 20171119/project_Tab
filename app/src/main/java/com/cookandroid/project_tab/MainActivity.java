package com.cookandroid.project_tab;

import android.app.ProgressDialog;
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

//import com.cookandroid.project_tab.customadapter.CustomGalleryAdapter;


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
//    private CustomGalleryAdapter customGalAdapter;

    private String[] imgs;

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
        ts3.setIndicator("TAB 3") ;
        tabHost1.addTab(ts3) ;

//        TabHost.TabSpec ts4 = tabHost1.newTabSpec("Tab Spec 4") ;
//        ts3.setContent(R.id.content4) ;
//        ts3.setIndicator("TAB 4") ;
//        tabHost1.addTab(ts4) ;

        // Call 변수 미리 설정
        ll = (LinearLayout) findViewById(R.id.content1);
        list = (ListView) findViewById(R.id.listView1);
        LoadContactsAyscn lca = new LoadContactsAyscn();
        lca.execute();

        imageview = (ImageView)findViewById(R.id.imageView);
        imageview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent. setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        });

        // App.을 실행하자 마자 지정한 경로의 생성 및 접근에 용이하도록 아래와 같이 생성
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("MyCameraApp", "failed to create directory");
//                return null;
            }
        }

        basePath = mediaStorageDir.getPath();

//        imgPath = (TextView)findViewById(R.id.imgpath);
//        resultView = (ImageView)findViewById(R.id.resultview);
//        takePicBtn = (Button)findViewById(R.id.takepicbtn);
        // Button click시, Camera Intent를 불러 옴
        takePicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create Intent to take a picture and return control to the calling application
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

//                fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name
//
//                // start the image capture Intent
//                startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                File imagesFolder = new File(Environment.getExternalStorageDirectory(), "MyImages");
                imagesFolder.mkdirs(); // <----
                File image = new File(imagesFolder, "image_001.jpg");
                Uri uriSavedImage = Uri.fromFile(image);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
                startActivityForResult(intent,0);
            }
        });

        File file = new File(basePath);
        imgs = file.list();
        for(int i=0; i<imgs.length; i++){
            imgPath.setText(imgs[i]);
        }

//        customGallery = (Gallery)findViewById(R.id.customgallery); // activity_main.xml에서 선언한 Gallery를 연결
//        customGalAdapter = new CustomGalleryAdapter(getApplicationContext(), basePath); // 위 Gallery에 대한 Adapter를 선언
//        customGallery.setAdapter(customGalAdapter); // Gallery에 위 Adapter를 연결
        // Gallery의 Item을 Click할 경우 ImageView에 보여주도록 함
        customGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bitmap bm = BitmapFactory.decodeFile(basePath+ File.separator +imgs[position]);
                Bitmap bm2 = ThumbnailUtils.extractThumbnail(bm, bm.getWidth() / inSampleSize, bm.getHeight() / inSampleSize);
                resultView.setImageBitmap(bm2);
                imgPath.setText(basePath+File.separator+imgs[position]);
            }
        });
        // Gallery의 Item을 LongClick할 경우 해당 File을 삭제하도록 함.
        // 이 부분에서 동작은 하나, 삭제 후 결과가 View에 반영이 안되어 추가 보완 필요
        customGallery.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                File temp = new File(basePath+File.separator+imgs[position]);
                temp.delete();
                return false;
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
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri selectedImageUri = data.getData();
            imageview.setImageURI(selectedImageUri);

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