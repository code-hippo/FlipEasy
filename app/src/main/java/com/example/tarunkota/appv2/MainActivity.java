package com.example.tarunkota.appv2;

import android.app.SearchManager;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static java.lang.StrictMath.max;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = "key";
    TextToSpeech t1;

    private static final int SPEECH_REQUEST_CODE = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
                String toSpeak = "Welcome to Flipkart. Swipe Left to Search. Swipe Right to Browse. Swipe down to go to cart. ";
                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });


        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setOnTouchListener(new OnSwipeTouchListener(this) {


            @Override
            public void onSwipeLeft() {
                displaySpeechRecognizer();
            }

            public void onSwipeRight() {

                TextView ta = (TextView)findViewById(R.id.textView);
                ta.setText("right");

                Intent i = new Intent(getApplicationContext(), ListProducts.class);
                i.putExtra("title","Browsing Categories");
                i.putExtra("max","10");

                startActivity(i);

            }

            public void onSwipeDown() {

            }

            public void onSwipeUp() {


            }


        });




    }














    private void displaySpeechRecognizer() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
// Start the activity, the intent will be populated with the speech text
        startActivityForResult(intent, SPEECH_REQUEST_CODE);
        final List<String> results = intent.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
            List<String> results = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            final String spokenText = results.get(0);
            // Do something with spokenText
            final String sText = spokenText;
            ((TextView)this.findViewById(R.id.textView)).setText(spokenText);

            Log.i(TAG, "key8");
            t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status != TextToSpeech.ERROR) {
                        t1.setLanguage(Locale.UK);
                    }
                    Log.i(TAG, "key7");
                    String toSpeak = "searching for "+ sText;
                    t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                    Log.i(TAG, "key6");
                }
            });

            //detected sound
            Log.i(TAG, "key5");

            data ds = new data();
            ds.init();
            List<Node> ls= ds.ct.allNodes;
            List<String> list1 = new ArrayList<>();
            Log.i(TAG, "key3");

            for(int i =0;i<ls.size();i++)
            {
                    list1.add( ls.get(i).Name);
            }

            Log.i(TAG, "key1");
            Log.i("list",list1.toString());
            ArrayList<Double> index=new ArrayList<>();
            for(int i=0;i<list1.size();i++){
                index.add(Similarity.similarity(spokenText, list1.get(i)));
            }
            Log.i(TAG, "key2");

            int bestmatch=-1;
            for(int i=0;i<index.size();i++){
                if(index.get(i)>index.get(max(0,bestmatch))){
                    bestmatch=i;
                }
            }
            Log.i("list",index.toString());
            Log.i(TAG, "key4");

            String ftext=list1.get(bestmatch);

            Log.i(TAG, sText + "stext" + ftext + "ftext");


            Intent i = new Intent(getApplicationContext(), ListProducts.class);
            i.putExtra("title",sText);
            i.putExtra("max", "10");
            Log.i(TAG, "key8");

            startActivity(i);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            ((TextView)this.findViewById(R.id.textView)).setText(query);

        }
    }




}
