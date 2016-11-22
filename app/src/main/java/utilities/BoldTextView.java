package utilities;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;


public class BoldTextView extends TextView {

    public BoldTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }


    public BoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public BoldTextView(Context context) {
        super(context);
        init();
    }


    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/bold.ttf");
        setTypeface(tf, 1);

    }

}