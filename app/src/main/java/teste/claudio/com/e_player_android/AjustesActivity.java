package teste.claudio.com.e_player_android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

public class AjustesActivity extends AppCompatActivity {

    private Button btnAplicar;
    private static final String ARQUIVO_PREFERENCIAS = "ArquivoPreferencias";
    private EditText txtRadio;
    String campoDigitado="";
    ShimmerTextView tv;
    Shimmer shimmer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ajustes);

        //Mostrar a Action bar e o título desta activity
        getSupportActionBar().show();

        //Titulo da Activity
        getSupportActionBar().setTitle((android.text.Html.fromHtml("<font color=\"WHITE\">"
                + "Configurações" + "</font>")));

        //Adiciona o botão de voltar na Action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        txtRadio = (EditText) findViewById(R.id.txtRadio);
        btnAplicar = (Button) findViewById(R.id.btnConfirmar);
        tv = (ShimmerTextView) findViewById(R.id.shimmer_tv);

        //Label com animação
        shimmer = new Shimmer();
        shimmer.start(tv);

        //Atribui borda arredondada
        btnAplicar.setBackground(setBorderRounded());
        btnAplicar.getBackground().setAlpha(90);

        btnAplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Tamanho do fonte
                Toast.makeText(AjustesActivity.this,"Configurações realizadas!",Toast.LENGTH_SHORT).show();


                //Gravar os valores no arquivo do usuário - SharedPreferences
                SharedPreferences prefs =
                        AjustesActivity.this.getSharedPreferences(ARQUIVO_PREFERENCIAS,
                                AjustesActivity.this.MODE_PRIVATE);

                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("STREAM",txtRadio.toString());
                editor.commit();

                //Fecha a tela atual
                AjustesActivity.this.finish();
            }
        });

        //Esconde o teclado quando abrir a tela
        this.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        txtRadio.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                campoDigitado = txtRadio.getText().toString();
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    // Aciona o botao Aplicar
                    // Perform action on key press
                    if ((!campoDigitado.equals(null)) && (!campoDigitado.equals(" "))
                            && (!campoDigitado.equals(""))) {
                        btnAplicar.performClick();
                    } else {
                        Toast.makeText(AjustesActivity.this,
                                "Entre com o steam desejado!", Toast.LENGTH_LONG).show();
                    }
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public GradientDrawable setBorderRounded(){
        GradientDrawable shape =  new GradientDrawable();
        shape.setCornerRadius( 40 );

        shape.setColor(getResources().getColor(R.color.colorPrimary));
        return shape;
    }

}
