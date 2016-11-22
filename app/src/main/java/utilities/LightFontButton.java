package utilities;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;


public class LightFontButton extends Button {

    public LightFontButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }


    public LightFontButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public LightFontButton(Context context) {
        super(context);
        init();
    }


    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/light.ttf");
        setTypeface(tf, 1);

    }

}