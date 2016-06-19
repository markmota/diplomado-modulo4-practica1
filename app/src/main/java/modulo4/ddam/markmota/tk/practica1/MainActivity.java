package modulo4.ddam.markmota.tk.practica1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private double num=0.0;

    private String oper="";
    private double resultado=0.0;
    private boolean mostreResultado=false;

    private TextView calcPantalla;
    private String pantalla;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calcPantalla=(TextView) findViewById(R.id.main_activity_txtPantalla);

        findViewById(R.id.main_activity_btn_0).setOnClickListener(this);
        findViewById(R.id.main_activity_btn_1).setOnClickListener(this);
        findViewById(R.id.main_activity_btn_2).setOnClickListener(this);
        findViewById(R.id.main_activity_btn_3).setOnClickListener(this);
        findViewById(R.id.main_activity_btn_4).setOnClickListener(this);
        findViewById(R.id.main_activity_btn_5).setOnClickListener(this);
        findViewById(R.id.main_activity_btn_6).setOnClickListener(this);
        findViewById(R.id.main_activity_btn_7).setOnClickListener(this);
        findViewById(R.id.main_activity_btn_8).setOnClickListener(this);
        findViewById(R.id.main_activity_btn_9).setOnClickListener(this);
        findViewById(R.id.main_activity_btn_punto).setOnClickListener(this);
        findViewById(R.id.main_activity_btn_mod).setOnClickListener(this);
        findViewById(R.id.main_activity_btn_division).setOnClickListener(this);
        findViewById(R.id.main_activity_btn_add).setOnClickListener(this);
        findViewById(R.id.main_activity_btn_substraction).setOnClickListener(this);
        findViewById(R.id.main_activity_btn_times).setOnClickListener(this);
        findViewById(R.id.main_activity_btn_equal).setOnClickListener(this);
        findViewById(R.id.main_activity_btn_clear).setOnClickListener(this);
        findViewById(R.id.main_activity_btn_switch).setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        pantalla=calcPantalla.getText().toString();
        switch (v.getId()){
            case R.id.main_activity_btn_0:
                pressDigit(0);
                break;
            case R.id.main_activity_btn_1:
                pressDigit(1);
                break;
            case R.id.main_activity_btn_2:
                pressDigit(2);
                break;
            case R.id.main_activity_btn_3:
                pressDigit(3);
                break;
            case R.id.main_activity_btn_4:
                pressDigit(4);
                break;
            case R.id.main_activity_btn_5:
                pressDigit(5);
                break;
            case R.id.main_activity_btn_6:
                pressDigit(6);
                break;
            case R.id.main_activity_btn_7:
                pressDigit(7);
                break;

            case R.id.main_activity_btn_8:
                pressDigit(8);
                break;
            case R.id.main_activity_btn_9:
                pressDigit(9);
                break;
            case R.id.main_activity_btn_punto:
                pressPunto();
                break;
            case R.id.main_activity_btn_clear:
                pressClear();
                break;
            case R.id.main_activity_btn_equal:
                pressResult();
                break;
            case R.id.main_activity_btn_add:
                operation("add");
                break;
            case R.id.main_activity_btn_substraction:
                operation("sub");
                break;
            case R.id.main_activity_btn_division:
                operation("div");
                break;
            case R.id.main_activity_btn_mod:
                operation("mod");
                break;
            case R.id.main_activity_btn_times:
                operation("times");
                break;
            case R.id.main_activity_btn_switch:
                switch_calc();
                break;

        }
    }

    private void switch_calc() {
        pressClear();
        //Change activity
        Intent intent= new Intent(getApplicationContext(),BinaryActivity.class);
        startActivity(intent);
    }

    private void pressResult() {
        num=Double.parseDouble(pantalla);

        if(!oper.isEmpty()){
            opera(oper);
        }
        oper="";
    }


    private void operation(String op) {


        num=Double.parseDouble(pantalla);

        if(!oper.isEmpty()){
            opera(oper);
        }
        else {
                calcPantalla.setText("0.0");
                resultado=num;
        }
        oper=op;


    }

    private void opera(String oper) {
        switch (oper){
            case "add":
                resultado+=num;
                break;
            case "sub":
                resultado-=num;
                break;
            case "mod":
                if(num!=0){
                    resultado%=num;
                }
                else{
                    resultado=0.0;
                    showMessage(R.string.activity_main_message_error_mod);
                }
                break;
            case "times":
                resultado*=num;
                break;
            case "div":
                if(num!=0){
                    resultado/=num;
                }
                else{
                    resultado=0.0;
                    showMessage(R.string.activity_main_message_error_div);
                }
                break;

        }

        mostreResultado = true;
        calcPantalla.setText("" + resultado);
    }

    private void pressClear() {
        calcPantalla.setText("0.0");
        num=0.0;
        resultado=0.0;
        oper="";
    }


    private void pressPunto() {

        if(!pantalla.contains("."))
            calcPantalla.setText(pantalla+".");
        mostreResultado=false;

    }

    private void pressDigit(int digito) {


        if(pantalla.equals("0.0") || mostreResultado){
            calcPantalla.setText(""+digito);
            mostreResultado=false;
        }


        else{
            calcPantalla.setText(pantalla+digito);
        }


    }
    private void showMessage(int resourceString) {
        Toast.makeText(getApplicationContext(),resourceString,Toast.LENGTH_SHORT).show();
    }
}
