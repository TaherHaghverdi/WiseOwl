package utilities;

import java.io.File;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import corporation.rabbit.games.true_or_false.G;
import corporation.rabbit.games.true_or_false.HelperUi;
import corporation.rabbit.games.true_or_false.R;


public class Donation extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donation);
        Button btnShop = (Button) findViewById(R.id.btnShop);
        Button btnSendApp = (Button) findViewById(R.id.btnSendApp);
        Button btnSticker = (Button) findViewById(R.id.btnSticker);
        Button btnVideo = (Button) findViewById(R.id.btnVideo);
        Button btnSendEmail = (Button) findViewById(R.id.btnSendEmail);
        final MediaPlayer shop = MediaPlayer.create(G.context, R.raw.shop);
        HelperUi.persianizer((ViewGroup) getWindow().getDecorView());

        btnShop.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (G.musicPlay)
                    shop.start();
                Intent intent = new Intent(Donation.this, Shop.class);
                startActivity(intent);
                overridePendingTransition(R.anim.come_in_left_to_right, R.anim.rast_be_chap);
            }
        });

        btnSendApp.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                try {

                    if ( !G.gSendApp)
                    {
                        G.giftDialog(100, 5, R.string.gSendApp);
                        G.gSendApp = true;
                        Editor editor = G.preferences.edit();
                        editor.putBoolean("gSendApp", G.gSendApp);
                        editor.commit();
                    }

                    PackageManager pm = G.context.getPackageManager();
                    ApplicationInfo ai = pm.getApplicationInfo(G.context.getPackageName(), 0);
                    File srcFile = new File(ai.publicSourceDir);
                    Intent share = new Intent();
                    share.setAction(Intent.ACTION_SEND);
                    share.setType("application/*");
                    share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(srcFile));
                    startActivity(Intent.createChooser(share, "ارسال برنامه به دوستان"));
                    if ( !G.gSendApp)
                    {
                        G.giftDialog(100, 5, R.string.gSendApp);
                        G.gSendApp = true;
                        Editor editor = G.preferences.edit();
                        editor.putBoolean("gSendApp", G.gSendApp);
                        editor.commit();
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        btnSticker.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://telegram.me/addstickers/wiseowl"));
                startActivity(browserIntent);
                if ( !G.gAddSticker)
                {
                    G.giftDialog(0, 5, R.string.gAddSticker);
                    G.gAddSticker = true;
                    Editor editor = G.preferences.edit();
                    editor.putBoolean("gAddSticker", G.gAddSticker);
                    editor.commit();
                }
            }
        });

        btnSendEmail.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{ "corporation.rabbit@gmail.com" });
                i.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.donate_mail_subject));
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                }
                catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(G.context, R.string.no_email_client_installed, Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnVideo.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String text = getResources().getString(R.string.video_share) + " \n" +
                        "http://www.aparat.com/v/0o9Ev";
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, text);

                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                if ( !G.gSendVideo)
                {
                    G.giftDialog(100, 0, R.string.gEveryThing);
                    G.gSendVideo = true;
                    Editor editor = G.preferences.edit();
                    editor.putBoolean("gSendVideo", G.gSendVideo);
                    editor.commit();
                }
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        View view = G.inflater.inflate(R.layout.action_bar_simple, null);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(view, new ActionBar.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
        final ImageView imgLogo = (ImageView) view.findViewById(R.id.imgLogo);
        final ImageView imgBack = (ImageView) view.findViewById(R.id.imgBack);
        HelperUi.persianizer((ViewGroup) view);

        imgBack.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                finish();
                overridePendingTransition(R.anim.come_in_right_to_left, R.anim.chap_be_rast);

            }
        });
        final TextView txtHeader = (TextView) view.findViewById(R.id.txtHeader);
        final Animation blink = AnimationUtils.loadAnimation(this, R.anim.blink);
        txtHeader.setText(getResources().getString(R.string.donation));
        imgLogo.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                imgLogo.startAnimation(blink);
            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.come_in_right_to_left, R.anim.chap_be_rast);
    }
}
