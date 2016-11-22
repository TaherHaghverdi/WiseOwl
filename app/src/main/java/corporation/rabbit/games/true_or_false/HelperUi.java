package corporation.rabbit.games.true_or_false;

import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class HelperUi {

    public static void persianizer(ViewGroup viewGroup) {

        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; ++i)
        {
            View child = viewGroup.getChildAt(i);
            if (child instanceof ViewGroup) {
                persianizer((ViewGroup) child);
                continue;
            }
            if (child instanceof TextView) {
                ((TextView) child).setTypeface(G.gameFont);
            }
        }
    }
}
