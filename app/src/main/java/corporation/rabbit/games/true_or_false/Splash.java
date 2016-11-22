package corporation.rabbit.games.true_or_false;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class Splash extends ActionBarActivity {

    private ProgressDialog dialogLoading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        G.currentPlace = this;
        final ImageView imglogo = (ImageView) findViewById(R.id.imgLogo);
        final ImageView imgWelcome = (ImageView) findViewById(R.id.imgWelcome);
        final Animation animation = AnimationUtils.loadAnimation(this,
                R.anim.splash_anim);
        final Animation fade_in = AnimationUtils.loadAnimation(this,
                R.anim.fade_in_fast);
        imgWelcome.setVisibility(View.INVISIBLE);
        imglogo.startAnimation(animation);

        dialogLoading = new ProgressDialog(Splash.this);
        dialogLoading.setMessage(getResources().getString(R.string.first_loading));

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                imgWelcome.startAnimation(fade_in);
                imgWelcome.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {

                        new AsyncTask<Void, Void, Void>() {

                            @Override
                            public void onPreExecute() {
                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {
                                        if (G.isFirstTime) {
                                            dialogLoading.show();
                                        }

                                    }
                                });

                            }


                            @Override
                            public void onPostExecute(Void result) {
                                if ( !G.gNewVersion) {
                                    dialogLoading.dismiss();
                                    G.showOnUiThread(R.string.loading_finished);
                                }
                                Intent intent = new Intent(Splash.this, Menu2.class);
                                startActivity(intent);
                                finish();

                            }


                            @Override
                            protected Void doInBackground(Void... arg0) {
                                if (G.isFirstTime) {
                                    Commands.read();
                                    G.isFirstTime = false;
                                    Editor editor = G.preferences.edit();
                                    editor.putBoolean("isFirstTime", G.isFirstTime);
                                    editor.commit();
                                }
                                return null;

                            }
                        }.execute();

                    }
                }, fade_in.getDuration() + 200);
            }
        }, animation.getDuration() + 100);

    }


    @Override
    protected void onPause() {
        super.onPause();

        if (dialogLoading != null)
            dialogLoading.dismiss();

    }

}
