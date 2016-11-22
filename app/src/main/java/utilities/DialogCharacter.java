package utilities;

import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import corporation.rabbit.games.true_or_false.Commands;
import corporation.rabbit.games.true_or_false.G;
import corporation.rabbit.games.true_or_false.R;


public class DialogCharacter extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_character);

        ImageView imgBack = (ImageView) findViewById(R.id.imgBack);

        ImageButton img_1 = (ImageButton) findViewById(R.id.img_1);
        ImageButton img_2 = (ImageButton) findViewById(R.id.img_2);
        ImageButton img_3 = (ImageButton) findViewById(R.id.img_3);
        ImageButton img_4 = (ImageButton) findViewById(R.id.img_4);
        ImageButton img_5 = (ImageButton) findViewById(R.id.img_5);
        ImageButton img_6 = (ImageButton) findViewById(R.id.img_6);
        ImageButton img_7 = (ImageButton) findViewById(R.id.img_7);
        ImageButton img_8 = (ImageButton) findViewById(R.id.img_8);
        ImageButton img_9 = (ImageButton) findViewById(R.id.img_9);

        ImageButton img_10 = (ImageButton) findViewById(R.id.img_10);
        ImageButton img_11 = (ImageButton) findViewById(R.id.img_11);
        ImageButton img_12 = (ImageButton) findViewById(R.id.img_12);
        ImageButton img_13 = (ImageButton) findViewById(R.id.img_13);
        ImageButton img_14 = (ImageButton) findViewById(R.id.img_14);
        ImageButton img_15 = (ImageButton) findViewById(R.id.img_15);
        ImageButton img_16 = (ImageButton) findViewById(R.id.img_16);
        ImageButton img_17 = (ImageButton) findViewById(R.id.img_17);
        ImageButton img_18 = (ImageButton) findViewById(R.id.img_18);
        ImageButton img_19 = (ImageButton) findViewById(R.id.img_19);

        ImageButton img_20 = (ImageButton) findViewById(R.id.img_20);
        ImageButton img_21 = (ImageButton) findViewById(R.id.img_21);
        ImageButton img_22 = (ImageButton) findViewById(R.id.img_22);
        ImageButton img_23 = (ImageButton) findViewById(R.id.img_23);
        ImageButton img_24 = (ImageButton) findViewById(R.id.img_24);
        ImageButton img_25 = (ImageButton) findViewById(R.id.img_25);
        ImageButton img_26 = (ImageButton) findViewById(R.id.img_26);
        ImageButton img_27 = (ImageButton) findViewById(R.id.img_27);
        ImageButton img_28 = (ImageButton) findViewById(R.id.img_28);
        ImageButton img_29 = (ImageButton) findViewById(R.id.img_29);

        ImageButton img_30 = (ImageButton) findViewById(R.id.img_30);
        ImageButton img_31 = (ImageButton) findViewById(R.id.img_31);
        ImageButton img_32 = (ImageButton) findViewById(R.id.img_32);
        ImageButton img_33 = (ImageButton) findViewById(R.id.img_33);
        ImageButton img_34 = (ImageButton) findViewById(R.id.img_34);
        ImageButton img_35 = (ImageButton) findViewById(R.id.img_35);
        ImageButton img_36 = (ImageButton) findViewById(R.id.img_36);
        ImageButton img_37 = (ImageButton) findViewById(R.id.img_37);
        ImageButton img_38 = (ImageButton) findViewById(R.id.img_38);
        ImageButton img_39 = (ImageButton) findViewById(R.id.img_39);

        ImageButton img_40 = (ImageButton) findViewById(R.id.img_40);
        ImageButton img_41 = (ImageButton) findViewById(R.id.img_41);
        ImageButton img_42 = (ImageButton) findViewById(R.id.img_42);
        ImageButton img_43 = (ImageButton) findViewById(R.id.img_43);
        ImageButton img_44 = (ImageButton) findViewById(R.id.img_44);
        ImageButton img_45 = (ImageButton) findViewById(R.id.img_45);
        ImageButton img_46 = (ImageButton) findViewById(R.id.img_46);
        ImageButton img_47 = (ImageButton) findViewById(R.id.img_47);
        ImageButton img_48 = (ImageButton) findViewById(R.id.img_48);
        ImageButton img_49 = (ImageButton) findViewById(R.id.img_49);

        ImageButton img_50 = (ImageButton) findViewById(R.id.img_50);

        img_1.setTag(1);
        img_2.setTag(2);
        img_3.setTag(3);
        img_4.setTag(4);
        img_5.setTag(5);
        img_6.setTag(6);
        img_7.setTag(7);
        img_8.setTag(8);
        img_9.setTag(9);

        img_10.setTag(10);
        img_11.setTag(11);
        img_12.setTag(12);
        img_13.setTag(13);
        img_14.setTag(14);
        img_15.setTag(15);
        img_16.setTag(16);
        img_17.setTag(17);
        img_18.setTag(18);
        img_19.setTag(19);

        img_20.setTag(20);
        img_21.setTag(21);
        img_22.setTag(22);
        img_23.setTag(23);
        img_24.setTag(24);
        img_25.setTag(25);
        img_26.setTag(26);
        img_27.setTag(27);
        img_28.setTag(28);
        img_29.setTag(29);

        img_30.setTag(30);
        img_31.setTag(31);
        img_32.setTag(32);
        img_33.setTag(33);
        img_34.setTag(34);
        img_35.setTag(35);
        img_36.setTag(36);
        img_37.setTag(37);
        img_38.setTag(38);
        img_39.setTag(39);

        img_40.setTag(40);
        img_41.setTag(41);
        img_42.setTag(42);
        img_43.setTag(43);
        img_44.setTag(44);
        img_45.setTag(45);
        img_46.setTag(46);
        img_47.setTag(47);
        img_48.setTag(48);
        img_49.setTag(49);

        img_50.setTag(50);

        OnClickListener onClick = new OnClickListener() {

            @Override
            public void onClick(View view) {
                ConnectionDetector cnd = new ConnectionDetector(DialogCharacter.this);
                if (cnd.isConnectingToInternet())
                {
                    ImageButton img = (ImageButton) view;
                    final int tag = (Integer) img.getTag();
                    G.profileImage = tag;
                    new Thread(new Runnable() {

                        @Override
                        public void run() {
                            Commands.UpdateProfilePicture(G.email, tag);
                        }
                    }).start();

                    Editor editor = G.preferences.edit();
                    editor.putInt("profileImage", tag);
                    editor.commit();
                    finish();
                    overridePendingTransition(R.anim.fade_in_animation, R.anim.up_to_down);

                }
                else
                {
                    Toast.makeText(G.context, R.string.internet_required, Toast.LENGTH_SHORT).show();

                }

            }
        };

        img_1.setOnClickListener(onClick);
        img_2.setOnClickListener(onClick);
        img_3.setOnClickListener(onClick);
        img_4.setOnClickListener(onClick);
        img_5.setOnClickListener(onClick);
        img_6.setOnClickListener(onClick);
        img_7.setOnClickListener(onClick);
        img_8.setOnClickListener(onClick);
        img_9.setOnClickListener(onClick);

        img_10.setOnClickListener(onClick);
        img_11.setOnClickListener(onClick);
        img_12.setOnClickListener(onClick);
        img_13.setOnClickListener(onClick);
        img_14.setOnClickListener(onClick);
        img_15.setOnClickListener(onClick);
        img_16.setOnClickListener(onClick);
        img_17.setOnClickListener(onClick);
        img_18.setOnClickListener(onClick);
        img_19.setOnClickListener(onClick);

        img_20.setOnClickListener(onClick);
        img_21.setOnClickListener(onClick);
        img_22.setOnClickListener(onClick);
        img_23.setOnClickListener(onClick);
        img_24.setOnClickListener(onClick);
        img_25.setOnClickListener(onClick);
        img_26.setOnClickListener(onClick);
        img_27.setOnClickListener(onClick);
        img_28.setOnClickListener(onClick);
        img_29.setOnClickListener(onClick);

        img_30.setOnClickListener(onClick);
        img_31.setOnClickListener(onClick);
        img_32.setOnClickListener(onClick);
        img_33.setOnClickListener(onClick);
        img_34.setOnClickListener(onClick);
        img_35.setOnClickListener(onClick);
        img_36.setOnClickListener(onClick);
        img_37.setOnClickListener(onClick);
        img_38.setOnClickListener(onClick);
        img_39.setOnClickListener(onClick);

        img_40.setOnClickListener(onClick);
        img_41.setOnClickListener(onClick);
        img_42.setOnClickListener(onClick);
        img_43.setOnClickListener(onClick);
        img_44.setOnClickListener(onClick);
        img_45.setOnClickListener(onClick);
        img_46.setOnClickListener(onClick);
        img_47.setOnClickListener(onClick);
        img_48.setOnClickListener(onClick);
        img_49.setOnClickListener(onClick);

        img_50.setOnClickListener(onClick);

        imgBack.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                finish();
                overridePendingTransition(R.anim.fade_in_animation, R.anim.up_to_down);

            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in_animation, R.anim.up_to_down);
    }
}