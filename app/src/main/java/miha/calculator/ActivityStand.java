package miha.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * Created by Миша_2 on 25.04.2017.
 */
public class ActivityStand extends AppCompatActivity implements View.OnClickListener {

    Button BtnOK;
    Button BtnReset;
    Button BtnQuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standart);

        BtnQuit = (Button) findViewById(R.id.buttonQuietStand);
        BtnQuit.setOnClickListener(this);
        BtnReset = (Button) findViewById(R.id.buttonResetStand);
        BtnReset.setOnClickListener(this);
        BtnOK = (Button) findViewById(R.id.buttonOKstand);
        BtnOK.setOnClickListener(this);
    }

    final int MENU_RESET_ID = 0;
    final int MENU_MAIN_ID = 1;
    final int MENU_QUAD_ID = 2;
    final int MENU_QUIT_ID = 3;

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, MENU_RESET_ID, 0, R.string.reset);
        menu.add(0, MENU_MAIN_ID, 0, R.string.choose);
        menu.add(0, MENU_QUAD_ID, 0, R.string.quadratic_equations);
        menu.add(0, MENU_QUIT_ID, 0, R.string.quit);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_QUIT_ID:
                finish();
                break;
            case MENU_QUAD_ID:
                startActivity(new Intent(this, ActivityQuadr.class));
                finish();
                break;
            case MENU_MAIN_ID:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            case MENU_RESET_ID:
                ((EditText)findViewById(R.id.num1)).setText("");
                ((EditText)findViewById(R.id.num2)).setText("");
                ((TextView)findViewById(R.id.resstand)).setText("");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
       if (v.getId() == R.id.buttonQuietStand)
       {
           finish();
       }
       else if (v.getId() == R.id.buttonResetStand){
           ((EditText)findViewById(R.id.num1)).setText("");
           ((EditText)findViewById(R.id.num2)).setText("");
           ((TextView)findViewById(R.id.resstand)).setText("");
       }
       else
       {
           String oper = "";
           EditText etNum1 = (EditText) findViewById(R.id.num1);
           EditText etNum2 = (EditText) findViewById(R.id.num2);

           float num1 = 0;
           float num2 = 0;
           float result = 0;

           if (TextUtils.isEmpty(etNum1.getText().toString())
                || TextUtils.isEmpty(etNum2.getText().toString())) {
           return;
           }

           num1 = Float.parseFloat(etNum1.getText().toString());
           num2 = Float.parseFloat(etNum2.getText().toString());


           Spinner spinner = (Spinner) findViewById(R.id.symb);
           String deistvie = spinner.getSelectedItem().toString();

           switch (deistvie){
               case "+":
                   oper = "+";
                   result = num1 + num2;
                   break;
               case "-":
                   oper = "-";
                   result = num1 - num2;
                   break;
               case "/":
                   oper = "/";
                       result = num1 / num2;
                   break;
               case "*":
                   oper = "*";
                   result = num1 * num2;
                   break;
           }
           if (oper == "/" && num2 == 0)
           {
               ((TextView) findViewById(R.id.resstand)).setText("ERROR:Divide By Zero");
           }
           else
           {
               ((TextView) findViewById(R.id.resstand)).setText(num1 + " " + oper + " " + num2 + " = " + result);
           }
       }
    }
}
