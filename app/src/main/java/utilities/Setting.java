package utilities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import corporation.rabbit.games.true_or_false.G;
import corporation.rabbit.games.true_or_false.HelperUi;
import corporation.rabbit.games.true_or_false.R;


public class Setting extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        SeekBar seekBarFontSize = (SeekBar) findViewById(R.id.seekBar1);
        final TextView txtTest = (TextView) findViewById(R.id.txtTest);
        HelperUi.persianizer((ViewGroup) getWindow().getDecorView());

        ViewGroup layoutExitDialog = (ViewGroup) findViewById(R.id.layoutExitDialog);
        ViewGroup layoutSounds = (ViewGroup) findViewById(R.id.layoutSounds);

        txtTest.setTextSize(G.fontSize);
        seekBarFontSize.setProgress(20);
        final CheckBox chbExitDialog = (CheckBox) findViewById(R.id.checkBoxExitDialog);
        if (G.onExitDialogShow)
            chbExitDialog.setChecked(false);
        else
            chbExitDialog.setChecked(true);

        layoutExitDialog.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (chbExitDialog.isChecked())
                {
                    G.onExitDialogShow = false;
                    chbExitDialog.setChecked(false);
                }
                else
                {
                    G.onExitDialogShow = true;
                    chbExitDialog.setChecked(true);

                }
                SharedPreferences.Editor editor = G.preferences.edit();
                editor.putBoolean("onExitDialogShow", G.onExitDialogShow);
                editor.commit();
            }
        });
        chbExitDialog.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                if (chbExitDialog.isChecked())
                {
                    G.onExitDialogShow = false;
                }
                else
                {
                    G.onExitDialogShow = true;
                }
                SharedPreferences.Editor editor = G.preferences.edit();
                editor.putBoolean("onExitDialogShow", G.onExitDialogShow);
                editor.commit();

            }
        });
        final CheckBox chbSounds = (CheckBox) findViewById(R.id.CheckBoxSounds);

        if (G.musicPlay)
            chbSounds.setChecked(true);
        else
            chbSounds.setChecked(false);
        layoutSounds.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (chbSounds.isChecked())
                {
                    G.musicPlay = false;
                    chbSounds.setChecked(false);
                }
                else
                {
                    G.musicPlay = true;
                    chbSounds.setChecked(true);

                }
                SharedPreferences.Editor editor = G.preferences.edit();
                editor.putBoolean("musicPlay", G.musicPlay);
                editor.commit();

            }
        });
        chbSounds.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                if (chbSounds.isChecked())
                {
                    G.musicPlay = true;
                }
                else
                {
                    G.musicPlay = false;
                }
                SharedPreferences.Editor editor = G.preferences.edit();
                editor.putBoolean("musicPlay", G.musicPlay);
                editor.commit();

            }
        });
        seekBarFontSize.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar arg0) {}


            @Override
            public void onStartTrackingTouch(SeekBar arg0) {}


            @Override
            public void onProgressChanged(SeekBar arg0, int size, boolean arg2) {
                G.fontSize = size;
                txtTest.setTextSize(G.fontSize);
                SharedPreferences.Editor editor = G.preferences.edit();
                editor.putInt("fontSize", G.fontSize);
                editor.commit();

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
        txtHeader.setText(getResources().getString(R.string.setting));
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
