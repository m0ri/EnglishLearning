package com.example.pavel.mynewapss;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import models.Word;

/**
 * Created by pavel on 12/07/2016.
 */
public class SecondActivity extends Activity {
    TextView test;
    int totalHeight;
    int heightTextView;
    int padding;
    ImageView background;
    Word[] words = new Word[10];
    static boolean isPaused = false;
    Word correctword;
    Word[] viewWords;
    Thread run;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        ImageButton pause = (ImageButton) findViewById(R.id.Pause);
        TextView textView1 = (TextView) findViewById(R.id.textView1);
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        TextView textView3 = (TextView) findViewById(R.id.textView3);
        TextView textView4 = (TextView) findViewById(R.id.textView4);

        test = (TextView) findViewById(R.id.test);


        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuMyDilog pause = new MenuMyDilog(SecondActivity.this);
                pause.setActivity(SecondActivity.this);
                pause.show();
                isPaused = true;
            }
        });



        for (int i = 0; i < 10; i++) {
            words[i] = new Word("text" + i,"ТЕКСТ "+ i);
        }


        background = (ImageView) findViewById(R.id.background);

        ViewTreeObserver vto = background.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {

            public boolean onPreDraw() {
                background.getViewTreeObserver().removeOnPreDrawListener(this);
                size();
                startMoving();
                return true;
            }
        });

        textView1.setOnClickListener(new Listener(test));
        textView2.setOnClickListener(new Listener(test));
        textView3.setOnClickListener(new Listener(test));
        textView4.setOnClickListener(new Listener(test));
    }

    public void setViews(Word[] wordsInWork, Word correctword) {

        TextView textView1 = (TextView) findViewById(R.id.textView1);
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        TextView textView3 = (TextView) findViewById(R.id.textView3);
        TextView textView4 = (TextView) findViewById(R.id.textView4);

        textView1.setText(wordsInWork[0].getRussian());
        textView2.setText(wordsInWork[1].getRussian());
        textView3.setText(wordsInWork[2].getRussian());
        textView4.setText(wordsInWork[3].getRussian());

        test.setText(correctword.getEnglish());

    }

    public void move() {
        SecondActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                test.setPadding(0, test.getPaddingTop() + 20, 0, 0);
                Log.d("tag", "padding = " + test.getPaddingTop());
            }
        });

    }

    public void size() {
        this.totalHeight = background.getHeight();
        this.heightTextView = test.getHeight();
        this.padding = test.getPaddingTop();
    }

    public void startMoving() {


        viewWords = prepareWords();
        correctword = prepareCorrectWord(viewWords);
        setViews(viewWords, correctword);


        run = new Thread(new Runnable() {
            @Override
            public void run() {
                size();
                try {

                    while (heightTextView < totalHeight) {
                        Thread.sleep(100l);
                        if (isPaused == false) {
                            move();
                        }
                        Log.d("tag", "thread = " + this);
                        //  Log.d("tag", "heightTextView = " + heightTextView);
                        //  Log.d("tag", "totalHeight = " + totalHeight);
                        size();
                    }
                    finishMoving();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        run.start();

    }

    public void finishMoving() {

        SecondActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                test.setPadding(0, 0, 0, 0);
                size();
                test.setText(getWord().getEnglish());
                if (run != null) {
                    run.interrupt();
                }
                startMoving();

            }
        });

    }

    public void increaseScoreCount() {
        TextView scoreCount = (TextView) findViewById(R.id.scoreCount);
        scoreCount.setText("" + (Integer.parseInt(scoreCount.getText().toString()) + 1));
    }

    public void decreaseScoreCount() {
        TextView scoreCount = (TextView) findViewById(R.id.scoreCount);
        int count = Integer.parseInt(scoreCount.getText().toString());
        if (count > 0) {
            scoreCount.setText("" + (Integer.parseInt(scoreCount.getText().toString()) - 1));
        }
    }

    public Word getWord() {
        int index = (int) (Math.random() * words.length);
        return words[index];
    }

    public Word[] prepareWords() {
        Word[] masskey = new Word[4];

        for (int i = 0; i < masskey.length; i++) {

            int randomindex = (int) (Math.random() * words.length);


          /*  if (words[randomindex] != masskey[0] && words[randomindex] != masskey[1] && words[randomindex] != masskey[2] && words[randomindex] != masskey[3]) {

                masskey[i] = words[randomindex];
            } else {
                i--;
            }   */

            masskey[i] = words[randomindex];
            for (int j = 0; j < i; j++) {

                if (words[randomindex].equals(masskey[j])) {
                    i--;
                    break;
                }
            }

        }
        return masskey;
    }

    public Word prepareCorrectWord(Word[] wordsInWork) {

        int index = (int) (Math.random() * wordsInWork.length);
        return wordsInWork[index];

    }

    public class Listener implements View.OnClickListener {
        TextView test;

        public Listener(TextView test) {
            this.test = test;
        }

        @Override
        public void onClick(View v) {

            TextView text = (TextView) v;
            //String fallingWord = test.getText().toString();
            String choosenWord = text.getText().toString();

           // Log.d("tag", "fallingWord = " + fallingWord + " choosenWord " + choosenWord);

            if (correctword.getRussian().equals(choosenWord)) {

                increaseScoreCount();
               // Log.d("tag", "fallingWord = " + fallingWord + " choosenWord " + choosenWord);
                finishMoving();
            } else {
                decreaseScoreCount();
                finishMoving();
            }

        }
    }


}

