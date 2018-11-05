package teste.claudio.com.e_player_android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


public class PlayerActivity extends AppCompatActivity {
    private static String URL_MC = "http://107.155.164.136:8383/stream";

    private WebView webview;
    private ProgressBar progress;

    private Boolean loaded;

    private static final String ARQUIVO_PREFERENCIAS = "ArquivoPreferencias";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        //Mostrar a Action bar e o título desta activity
        this.getSupportActionBar().show();

        //Titulo da Activity
        this.getSupportActionBar().setTitle((android.text.Html.fromHtml("<font color=\"WHITE\">"
                + "e-Player" + "</font>")));

        loaded = false;

        final WebView webView =  findViewById(R.id.webview);

        webView.getSettings().setJavaScriptEnabled(true); // enable javascript

        webView.setWebViewClient(new WebViewClient());


        final View progress =  findViewById(R.id.progress);
        progress.setVisibility(View.INVISIBLE);

        //Liga o progress bar
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //Liga o progress
                progress.setVisibility(View.VISIBLE);
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                //Desliga o progress
                progress.setVisibility(View.INVISIBLE);
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }

        });

        //Carrega a página
        //webView.loadUrl(URL_MC);
        webView.loadUrl("file:///android_asset/radio.html");

        webView.requestFocus();

        //Tratar os botoes
        onClickEvent();

    }



    //Tratamento dos botões de compartilhar, copiar e marcar(implementar depois)
    private void onClickEvent() {
        findViewById(R.id.share_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compartilhar(URL_MC,"site Sideral!");
            }
        });
        findViewById(R.id.select_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlayerActivity.this, AjustesActivity.class);
                startActivity(intent);
            }
        });
    }

    //Método para compartilhar o versículo nas redes sociais
    public void compartilhar (String assunto, String texto){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT,assunto);
        intent.putExtra(Intent.EXTRA_TEXT, texto);
        startActivity(Intent.createChooser(intent, "Compartilhar Site"));


        startActivity(Intent.createChooser(intent, "Radio Digital"));
    }

    public void preferencias() {
        SharedPreferences sharedPreferences =
                getSharedPreferences(ARQUIVO_PREFERENCIAS, 0);
        if (sharedPreferences.contains("STREAM")) {
            URL_MC = "/"+ sharedPreferences.getString("STREAM", "");
        }

    }


    @Override
    protected  void onResume() {
        super.onResume();
        if (!loaded) {
            loaded = true;
        } else {
            recreate();
        }
    }
}
