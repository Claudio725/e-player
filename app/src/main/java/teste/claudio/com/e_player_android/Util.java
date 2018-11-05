package teste.claudio.com.e_player_android;

import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;

public class Util {
        public static GradientDrawable Borda_arrendondada(AppCompatActivity activity, int colorId,
                                                          int raio) {
            GradientDrawable shape =  new GradientDrawable();
            shape.setCornerRadius( raio );

            shape.setColor(activity.getResources().getColor(colorId));
            return shape;
        }
}
