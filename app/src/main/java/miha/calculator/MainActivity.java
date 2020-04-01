package miha.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button BtnOK;
    Button BtnQuit;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BtnOK = (Button) findViewById(R.id.buttonOK);
        BtnOK.setOnClickListener(this);
        BtnQuit = (Button) findViewById(R.id.buttonQuietMain);
        BtnQuit.setOnClickListener(this);
        radioGroup = (RadioGroup) findViewById(R.id.choose);
    }

    final int MENU_STANDART_ID = 0;
    final int MENU_QUAD_ID = 1;
    final int MENU_QUIT_ID = 2;

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, MENU_STANDART_ID, 0, R.string.standart_actions);
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
            case MENU_STANDART_ID:
                startActivity(new Intent(this, ActivityStand.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton myRadioButton = (RadioButton) findViewById(checkedRadioButtonId);
        int checkedIndex = radioGroup.indexOfChild(myRadioButton);
        if (checkedIndex == 0) {
            startActivity(new Intent(this, ActivityStand.class));
            finish();
        }
        else if (checkedIndex == 1)
        {
            startActivity(new Intent(this, ActivityQuadr.class));
            finish();
        }
        else if (v.getId() == R.id.buttonQuietMain){
            finish();
        }
    }
}
