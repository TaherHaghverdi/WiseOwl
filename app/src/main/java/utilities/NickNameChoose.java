package utilities;

import android.app.Dialog;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import corporation.rabbit.games.true_or_false.Commands;
import corporation.rabbit.games.true_or_false.G;
import corporation.rabbit.games.true_or_false.HelperUi;
import corporation.rabbit.games.true_or_false.R;


public class NickNameChoose extends ActionBarActivity {

    public static boolean nickname[] = new boolean[30];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getNamesStatus();

        setContentView(R.layout.nick_name_choose);
        final TextView txtPlayedGames = (TextView) findViewById(R.id.txtPlayedGames);
        HelperUi.persianizer((ViewGroup) getWindow().getDecorView());

        final Button btnNickname1 = (Button) findViewById(R.id.nickname1);
        final Button btnNickname2 = (Button) findViewById(R.id.nickname2);
        final Button btnNickname3 = (Button) findViewById(R.id.nickname3);
        final Button btnNickname4 = (Button) findViewById(R.id.nickname4);
        final Button btnNickname5 = (Button) findViewById(R.id.nickname5);
        final Button btnNickname6 = (Button) findViewById(R.id.nickname6);
        final Button btnNickname7 = (Button) findViewById(R.id.nickname7);
        final Button btnNickname8 = (Button) findViewById(R.id.nickname8);
        final Button btnNickname9 = (Button) findViewById(R.id.nickname9);
        final Button btnNickname10 = (Button) findViewById(R.id.nickname10);

        final Button btnNickname11 = (Button) findViewById(R.id.nickname11);
        final Button btnNickname12 = (Button) findViewById(R.id.nickname12);
        final Button btnNickname13 = (Button) findViewById(R.id.nickname13);
        final Button btnNickname14 = (Button) findViewById(R.id.nickname14);
        final Button btnNickname15 = (Button) findViewById(R.id.nickname15);
        final Button btnNickname16 = (Button) findViewById(R.id.nickname16);

        final Button btnNickname17 = (Button) findViewById(R.id.nickname17);
        final Button btnNickname18 = (Button) findViewById(R.id.nickname18);
        final Button btnNickname19 = (Button) findViewById(R.id.nickname19);
        final Button btnNickname20 = (Button) findViewById(R.id.nickname20);
        final Button btnNickname21 = (Button) findViewById(R.id.nickname21);
        final Button btnNickname22 = (Button) findViewById(R.id.nickname22);
        final Button btnNickname23 = (Button) findViewById(R.id.nickname23);
        final Button btnNickname24 = (Button) findViewById(R.id.nickname24);

        final Button btnNickname25 = (Button) findViewById(R.id.nickname25);
        final Button btnNickname26 = (Button) findViewById(R.id.nickname26);
        final Button btnNickname27 = (Button) findViewById(R.id.nickname27);
        final Button btnNickname28 = (Button) findViewById(R.id.nickname28);
        final Button btnNickname29 = (Button) findViewById(R.id.nickname29);

        btnNickname1.setTag(1);
        btnNickname2.setTag(2);
        btnNickname3.setTag(3);
        btnNickname4.setTag(4);
        btnNickname5.setTag(5);
        btnNickname6.setTag(6);
        btnNickname7.setTag(7);
        btnNickname8.setTag(8);
        btnNickname9.setTag(9);
        btnNickname10.setTag(10);
        btnNickname11.setTag(11);
        btnNickname12.setTag(12);
        btnNickname13.setTag(13);
        btnNickname14.setTag(14);
        btnNickname15.setTag(15);
        btnNickname16.setTag(16);
        btnNickname17.setTag(17);
        btnNickname18.setTag(18);
        btnNickname19.setTag(19);
        btnNickname20.setTag(20);
        btnNickname21.setTag(21);
        btnNickname22.setTag(22);
        btnNickname23.setTag(23);
        btnNickname24.setTag(24);
        btnNickname25.setTag(25);
        btnNickname26.setTag(26);
        btnNickname27.setTag(27);
        btnNickname28.setTag(28);
        btnNickname29.setTag(29);

        txtPlayedGames.setText(getResources().getString(R.string.played_games) + ": " + G.playedGames);
        btnNickname1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                dialogToBuy(5, nickname[1], btnNickname1, 1);
            }
        });
        btnNickname10.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                dialogToBuy(5000, nickname[10], btnNickname10, 10);
            }
        });
        OnClickListener Click1 = new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Button btn = (Button) arg0;
                final int tag = (Integer) btn.getTag();
                dialogToBuy(100, nickname[tag], btn, tag);
            }
        };

        OnClickListener Click2 = new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Button btn = (Button) arg0;
                final int tag = (Integer) btn.getTag();
                dialogToLevel(tag, nickname[tag], btn);
            }
        };

        btnNickname2.setOnClickListener(Click1);
        btnNickname3.setOnClickListener(Click1);
        btnNickname4.setOnClickListener(Click1);
        btnNickname5.setOnClickListener(Click1);
        btnNickname6.setOnClickListener(Click1);
        btnNickname7.setOnClickListener(Click1);
        btnNickname8.setOnClickListener(Click1);
        btnNickname9.setOnClickListener(Click1);
        btnNickname11.setOnClickListener(Click2);
        btnNickname12.setOnClickListener(Click2);
        btnNickname13.setOnClickListener(Click2);
        btnNickname14.setOnClickListener(Click2);
        btnNickname15.setOnClickListener(Click2);
        btnNickname16.setOnClickListener(Click2);
        btnNickname17.setOnClickListener(Click2);
        btnNickname18.setOnClickListener(Click2);
        btnNickname19.setOnClickListener(Click2);
        btnNickname20.setOnClickListener(Click2);
        btnNickname21.setOnClickListener(Click2);
        btnNickname22.setOnClickListener(Click2);
        btnNickname23.setOnClickListener(Click2);
        btnNickname24.setOnClickListener(Click2);
        btnNickname25.setOnClickListener(Click2);
        btnNickname26.setOnClickListener(Click2);
        btnNickname27.setOnClickListener(Click2);
        btnNickname28.setOnClickListener(Click2);
        btnNickname29.setOnClickListener(Click2);
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
        final TextView txtHeader = (TextView) view.findViewById(R.id.txtHeader);
        final Animation blink = AnimationUtils.loadAnimation(this, R.anim.blink);
        final ImageView imgBack = (ImageView) view.findViewById(R.id.imgBack);
        HelperUi.persianizer((ViewGroup) view);
        imgBack.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                finish();
                overridePendingTransition(R.anim.come_in_right_to_left, R.anim.chap_be_rast);

            }
        });
        txtHeader.setText(getResources().getString(R.string.nickname));
        imgLogo.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                imgLogo.startAnimation(blink);
            }
        });

    }


    private void dialogToBuy(final int price, final boolean check, Button btn, final int tag)
    {
        final Dialog dialogChooseNickname = new Dialog(NickNameChoose.this, R.style.ExitDialog);
        dialogChooseNickname.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogChooseNickname.setContentView(R.layout.dialog_nickname_choose);
        Button btnOk = (Button) dialogChooseNickname.findViewById(R.id.btnOk);
        Button btnCancel = (Button) dialogChooseNickname.findViewById(R.id.btnCancel);
        final TextView txtNickname = (TextView) dialogChooseNickname.findViewById(R.id.txtNickname);
        dialogChooseNickname.show();

        if (check)
        {
            txtNickname.setText(btn.getText().toString() + "");
            btnOk.setText("انتخاب");

        }
        else
        {
            txtNickname.setText(price + "سکه ");
            btnOk.setText("خرید");

        }
        btnCancel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                dialogChooseNickname.dismiss();

            }
        });
        btnOk.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                dialogChooseNickname.dismiss();
                if (check)
                {
                    ChoseName(tag);
                } else
                {
                    if (G.coins > price)
                    {
                        G.coins -= price;
                        G.saveCoins();
                        ChoseName(tag);

                    }
                    else {
                        Toast.makeText(G.context, R.string.not_enough_coins, Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });

    }


    private void dialogToLevel(final int reason, final boolean check, Button btn)
    {
        final Dialog dialogChooseNickname = new Dialog(NickNameChoose.this, R.style.ExitDialog);
        dialogChooseNickname.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogChooseNickname.setContentView(R.layout.dialog_nickname_choose);
        Button btnOk = (Button) dialogChooseNickname.findViewById(R.id.btnOk);
        ViewGroup LayoutOk = (ViewGroup) dialogChooseNickname.findViewById(R.id.LayoutOk);
        Button btnCancel = (Button) dialogChooseNickname.findViewById(R.id.btnCancel);
        final TextView txtNickname = (TextView) dialogChooseNickname.findViewById(R.id.txtNickname);
        dialogChooseNickname.show();

        if (check)
        {
            txtNickname.setText(btn.getText().toString() + "");
            btnOk.setText("انتخاب");

        }
        else
        {
            btnCancel.setText("باشه");
            LayoutOk.setVisibility(View.GONE);
            String packageName = getPackageName();
            String aString = "res_" + String.valueOf(reason);

            int resId = getResources().getIdentifier(aString, "string", packageName);
            txtNickname.setText(resId);
        }
        btnCancel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                dialogChooseNickname.dismiss();
            }
        });
        btnOk.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                dialogChooseNickname.dismiss();
                if (check)
                {
                    ChoseName(reason);
                }
            }
        });
    }


    private void ChoseName(final int i)
    {
        nickname[i] = true;
        G.nickName = i;
        Editor editor = G.preferences.edit();
        editor.putInt("nickName", G.nickName);
        editor.commit();
        new Thread(new Runnable() {

            @Override
            public void run() {
                Commands.changeNickname(i);
                saveNamesStatus();

            }
        }).start();
    }


    private void getNamesStatus()
    {

        nickname[1] = G.preferences.getBoolean("nickname1", false);
        nickname[2] = G.preferences.getBoolean("nickname2", false);
        nickname[3] = G.preferences.getBoolean("nickname3", false);
        nickname[4] = G.preferences.getBoolean("nickname4", false);
        nickname[5] = G.preferences.getBoolean("nickname5", false);
        nickname[6] = G.preferences.getBoolean("nickname6", false);
        nickname[7] = G.preferences.getBoolean("nickname7", false);
        nickname[8] = G.preferences.getBoolean("nickname8", false);
        nickname[9] = G.preferences.getBoolean("nickname9", false);
        nickname[10] = G.preferences.getBoolean("nickname10", false);
        nickname[11] = G.preferences.getBoolean("nickname11", false);
        nickname[12] = G.preferences.getBoolean("nickname12", false);
        nickname[13] = G.preferences.getBoolean("nickname13", false);
        nickname[14] = G.preferences.getBoolean("nickname14", false);
        nickname[15] = G.preferences.getBoolean("nickname15", false);
        nickname[16] = G.preferences.getBoolean("nickname16", false);
        nickname[17] = G.preferences.getBoolean("nickname17", false);
        nickname[18] = G.preferences.getBoolean("nickname18", false);
        nickname[19] = G.preferences.getBoolean("nickname19", false);
        nickname[20] = G.preferences.getBoolean("nickname20", false);
        nickname[21] = G.preferences.getBoolean("nickname21", false);
        nickname[22] = G.preferences.getBoolean("nickname22", false);
        nickname[23] = G.preferences.getBoolean("nickname23", false);
        nickname[24] = G.preferences.getBoolean("nickname24", false);
        nickname[25] = G.preferences.getBoolean("nickname25", false);
        nickname[26] = G.preferences.getBoolean("nickname26", false);
        nickname[27] = G.preferences.getBoolean("nickname27", false);
        nickname[28] = G.preferences.getBoolean("nickname28", false);
        nickname[29] = G.preferences.getBoolean("nickname29", false);

        if (G.levelAchieve5 >= 10)
            nickname[11] = true;
        if (G.levelAchieve6 >= 10)
            nickname[12] = true;
        if (G.levelAchieve7 >= 10)
            nickname[13] = true;
        if (G.levelAchieve8 >= 10)
            nickname[14] = true;
        if (G.levelAchieve9 >= 10)
            nickname[15] = true;
        if (G.levelAchieve10 >= 10)
            nickname[16] = true;

        if (G.playedGames >= 20)
            nickname[17] = true;
        if (G.levelAchieve5 >= 50)
            nickname[18] = true;
        if (G.playedGames >= 100)
            nickname[19] = true;
        if (G.levelAchieve5 >= 200)
            nickname[20] = true;
        if (G.playedGames >= 1000)
            nickname[21] = true;
        if (G.levelAchieve5 >= 1500)
            nickname[12] = true;
        if (G.playedGames >= 2000)
            nickname[23] = true;
        if (G.levelAchieve5 >= 5000)
            nickname[24] = true;

        if (G.specialLevelAchieve2)
            nickname[25] = true;
        if (G.specialLevelAchieve4)
            nickname[26] = true;
        if (G.specialLevelAchieve6)
            nickname[27] = true;
        if (G.specialLevelAchieve8)
            nickname[28] = true;
        if (G.specialLevelAchieve10)
            nickname[29] = true;
    }


    private void saveNamesStatus()
    {
        Editor editor = G.preferences.edit();
        editor.putBoolean("nickname1", nickname[1]);
        editor.putBoolean("nickname2", nickname[2]);
        editor.putBoolean("nickname3", nickname[3]);
        editor.putBoolean("nickname4", nickname[4]);
        editor.putBoolean("nickname5", nickname[5]);
        editor.putBoolean("nickname6", nickname[6]);
        editor.putBoolean("nickname7", nickname[7]);
        editor.putBoolean("nickname8", nickname[8]);
        editor.putBoolean("nickname9", nickname[9]);
        editor.putBoolean("nickname10", nickname[10]);
        editor.putBoolean("nickname11", nickname[11]);
        editor.putBoolean("nickname12", nickname[12]);
        editor.putBoolean("nickname13", nickname[13]);
        editor.putBoolean("nickname14", nickname[14]);
        editor.putBoolean("nickname15", nickname[15]);
        editor.putBoolean("nickname16", nickname[16]);
        editor.putBoolean("nickname17", nickname[17]);
        editor.putBoolean("nickname18", nickname[18]);
        editor.putBoolean("nickname19", nickname[19]);
        editor.putBoolean("nickname20", nickname[20]);
        editor.putBoolean("nickname21", nickname[21]);
        editor.putBoolean("nickname22", nickname[22]);
        editor.putBoolean("nickname23", nickname[23]);
        editor.putBoolean("nickname24", nickname[24]);
        editor.putBoolean("nickname25", nickname[25]);
        editor.putBoolean("nickname26", nickname[26]);
        editor.putBoolean("nickname27", nickname[27]);
        editor.putBoolean("nickname28", nickname[28]);
        editor.putBoolean("nickname29", nickname[29]);

        editor.commit();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.come_in_right_to_left, R.anim.chap_be_rast);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveNamesStatus();

    }

}
