package teste.claudio.com.e_player_android;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;


public class MainActivity extends android.support.v4.app.FragmentActivity  implements Runnable{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Handler handler = new Handler();
        handler.postDelayed(MainActivity.this, 3000);
    }

    public void run(){
        startActivity(new Intent(MainActivity.this, PlayerActivity.class));
        finish();
    }

}
