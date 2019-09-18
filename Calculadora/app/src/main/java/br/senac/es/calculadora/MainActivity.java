package br.senac.es.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    //classe publica com nome de MainActivity  extendida para AppCompatActivity

    private Calculadora calc;
    private boolean usuarioEstadigitandoUmNumero;
    private boolean separadorDecimalFoiDigitado;
    private TextView visor;
    private String separador;
    private char separadorChar;
    //char recebe um caracteres

    //6 variaveis


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Cria escopo(tela), no android
        //chama todos os comandos da classe pai.

        //metodo que liga a MainActivity.JAVA no activity_main.xml (TELA)
        setContentView(R.layout.activity_main);

        calc = new Calculadora();
        //metodo estancia

        usuarioEstadigitandoUmNumero = false;
        //metodo recebe paramentro false

        separadorDecimalFoiDigitado = false;
        //metodo recebe paramentro false

        visor = (TextView) findViewById(R.id.visor);
        //metodos, são amarados ao id que exibem os valores e comando.

        visor.setText("0");
        //metodo que set o argumento 0 no visor

        Locale localizacao = getResources().getConfiguration().locale;
        //metodo = formatação de data ou hora

        NumberFormat formatador = NumberFormat.getInstance(localizacao);
        //metodo = que formata localização

        if (formatador instanceof DecimalFormat) {
            // //condição com paramentro instanceof que ver a instancia do argumento
            DecimalFormatSymbols simbolo = ((DecimalFormat) formatador).getDecimalFormatSymbols();
            //formata
            separadorChar = simbolo.getDecimalSeparator();
            //metodo recebe o simbolo (,)
        }
        separador = String.valueOf(separadorChar);
        //metodo que retorna um valor (separadorChar)

        Button btnSeparador = (Button) findViewById(R.id.button19);
        //obejeto que amarra o button a tela usando id passado

        btnSeparador.setText(separador);
        //metodo que set o argumento 0 no visor


        final Typeface fonteDigital = Typeface.createFromAsset(this.getAssets(), "digital.ttf");
        //metodo que liga fonteDigital ao Assests

        visor.setTypeface(fonteDigital);
        //metodo que muda a fonte do visor

        visor.setOnClickListener(new View.OnClickListener() {
            @Override
            //sobrepoem interface
            public void onClick(View view) {
                //metodo oque sera execultado quando click no botao

                if (visor.getTypeface().equals(fonteDigital)) {
                    //condição
                    visor.setTypeface(Typeface.DEFAULT);
                } else {
                    //condição
                    visor.setTypeface(fonteDigital);

                }
            }
        });
        Toast.makeText(this, "Toque no visor para trocar sua fonte !", Toast.LENGTH_LONG).show();
        //uma ação que pede para click no visor para mudar a fonte

    }


    public void onClickNumeros(View v) {
        //metodo que dispara a ação ao clickar

        Button botaoTocado = (Button) v;
        //metodo que passa o valor exato ao clickar

        String digito = botaoTocado.getText().toString();
        //metodo que converte(toString) em uma String Digito e passa o numero recebido

        String textoNoVisor = visor.getText().toString();
        //metodo que pega oque ta no visor

        if (!usuarioEstadigitandoUmNumero || textoNoVisor.equals("0")) {
            //condição que verifica se a variavel no visor e 0
            visor.setText(digito);
            //metodo
            if (!digito.equals("0")) {
                //condição
                usuarioEstadigitandoUmNumero = true;
                //metodo recebe paramentro false
            }
        } else {
            //condição
            visor.setText(textoNoVisor + digito);
        }

    }

    public void onClickOperacoes(View v) {
        Button botaoTocado = (Button) v;
        //metodo que passa o valor ao clickar

        String operacao = botaoTocado.getText().toString();
        //metodo que converte em uma String Digito e passa o numero recebido

        if (operacao.equals(separador) && !separadorDecimalFoiDigitado) {
            //função
            separadorDecimalFoiDigitado = true;
            //metodo recebe paramentro false
            if (!usuarioEstadigitandoUmNumero)
                //condição
                visor.setText("0" + separador);
            else
                //condição
                visor.setText(visor.getText().toString() + separador);
            usuarioEstadigitandoUmNumero = true;
            //metodo recebe paramentro verdadeira

        } else if (!operacao.equals(separador)) {
            //condição que sobre escreve a operação

            String valorSemVirgula = visor.getText().toString().replace(separadorChar, '.');
            //metodo
            calc.setOperando(Double.parseDouble(valorSemVirgula));
            //metodo que formata valor para Double
            calc.realizarOperacao(operacao);

            String textResultado = String.valueOf(calc.getOperando());
            //metodo que retorna um valor e verificar se e String ou Int

            if (textResultado.endsWith(".0")) {
                //condição que checa se acaba com .0
                textResultado = textResultado.substring(0, textResultado.length() - 2);
            }
            visor.setText(textResultado.replace('.', separadorChar));

            usuarioEstadigitandoUmNumero = false;
            //metodo recebe paramentro false

            separadorDecimalFoiDigitado = false;
            //metodo recebe paramentro false

        }
    }

    public void onClickMemoria(View v) {
        Button botaoTocado = (Button) v;
        //metodo recebe o valor correto do button

        String operacaoMemoria = botaoTocado.getText().toString();
        //metodo

        String valorSemVirgula = visor.getText().toString().replace(separadorChar, '.');
        //metodo que faz a mesma coisa que anteriores so que pega valor sem virgula

        calc.setOperando(Double.parseDouble(valorSemVirgula));
        //metodo com variavel double que pega o valor e converte

        calc.realizarOperacaoDeMemoria(operacaoMemoria);
        //metodo que passa um argumento = paramentro

        usuarioEstadigitandoUmNumero = false;
        //metodo recebe paramentro false

    }
}


