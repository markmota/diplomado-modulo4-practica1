package modulo4.ddam.markmota.tk.practica1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class BinaryActivity extends AppCompatActivity implements View.OnClickListener {
    private String num="0";

    private String oper="";
    private String resultado="0";

    private boolean mostreResultado=false;
    private TextView calcPantalla;
    private String pantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binary);
        calcPantalla=(TextView) findViewById(R.id.main_activity_txtPantalla);
        findViewById(R.id.activity_binary_btn_0).setOnClickListener(this);
        findViewById(R.id.activity_binary_btn_1).setOnClickListener(this);
        findViewById(R.id.activity_binary_btn_add).setOnClickListener(this);
        findViewById(R.id.activity_binary_btn_equal).setOnClickListener(this);
        findViewById(R.id.activity_binary_btn_switch).setOnClickListener(this);
        findViewById(R.id.activity_binary_btn_clear).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        pantalla=calcPantalla.getText().toString();
        switch(v.getId()){
            case R.id.activity_binary_btn_0:
                pressDigit(0);
                break;
            case R.id.activity_binary_btn_1:
                pressDigit(1);
                break;
            case R.id.activity_binary_btn_equal:
                pressResult();
                break;
            case R.id.activity_binary_btn_add:
                operation("add");
                break;
            case R.id.activity_binary_btn_switch:
                switch_calc();
                break;
            case R.id.activity_binary_btn_clear:
                pressClear();
                break;
        }
    }

    private void pressResult() {
        num=pantalla;

        if(!oper.isEmpty()){
            opera(oper);
        }
        oper="";
    }

    private void pressDigit(int digito) {
        if(pantalla.equals("0") || mostreResultado){
            calcPantalla.setText(""+digito);
            mostreResultado=false;
        }


        else{
            calcPantalla.setText(pantalla+digito);
        }
    }

    private void switch_calc() {
       finish();
    }

    private void operation(String op) {
        num=pantalla;

        if(!oper.isEmpty()){
            opera(oper);
        }
        else {
            calcPantalla.setText("0");
            resultado=num;
        }
        oper=op;
    }

    private void pressClear() {
        calcPantalla.setText("0");
        num="0";
        resultado="0";
        oper="";
    }

    private void opera(String oper) {
        switch (oper) {
            case "add":
                resultado = addBinaryNumbers(num,resultado);
                break;
        }
        mostreResultado = true;
        calcPantalla.setText("" + resultado);
    }

    public static String addBinaryNumbers(String num1, String num2) {
        int p1 = num1.length() - 1;
        int p2 = num2.length() - 1;
        StringBuilder buf = new StringBuilder();
        int carry = 0;
        while (p1 >= 0 || p2 >= 0) {
            int sum = carry;
            if (p1 >= 0) {
                sum += num1.charAt(p1) - '0';
                p1--;
            }
            if (p2 >= 0) {
                sum += num2.charAt(p2) - '0';
                p2--;
            }
            carry = sum >> 1;
            sum = sum & 1;
            buf.append(sum == 0 ? '0' : '1');
        }
        if (carry > 0) {
            buf.append('1');
        }
        buf.reverse();
        return buf.toString();
    }
}
