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
import android.widget.TextView;

import static java.lang.Math.sqrt;
import static miha.calculator.R.id.x2;
import static miha.calculator.R.id.x;
import static miha.calculator.R.id.nox;

/**
 * Created by Миша_2 on 25.04.2017.
 */
public class ActivityQuadr extends AppCompatActivity implements View.OnClickListener {

    Button BtnOK;
    Button BtnReset;
    Button BtnQuit;

    int lengthOfFloatAfterComma(double f) {
        return String.valueOf(f).split("\\.")[1].length();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quadr);

        BtnOK = (Button) findViewById(R.id.buttonOKquad);
        BtnOK.setOnClickListener(this);
        BtnReset = (Button) findViewById(R.id.buttonResetQuad);
        BtnReset.setOnClickListener(this);
        BtnQuit = (Button) findViewById(R.id.buttonQuietQuadr);
        BtnQuit.setOnClickListener(this);
    }

    final int MENU_RESET_ID = 0;
    final int MENU_MAIN_ID = 1;
    final int MENU_STANDART_ID = 2;
    final int MENU_QUIT_ID = 3;

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, MENU_RESET_ID, 0, R.string.reset);
        menu.add(0, MENU_MAIN_ID, 0, R.string.choose);
        menu.add(0, MENU_STANDART_ID, 0,  R.string.standart_actions);
        menu.add(0, MENU_QUIT_ID, 0,  R.string.quit);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_QUIT_ID:
                finish();
                break;
            case MENU_STANDART_ID:
                startActivity(new Intent(this, ActivityStand.class));
                finish();
                break;
            case MENU_MAIN_ID:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            case MENU_RESET_ID:
                ((EditText)findViewById(x2)).setText("");
                ((EditText)findViewById(R.id.x)).setText("");
                ((EditText)findViewById(R.id.nox)).setText("");
                ((TextView)findViewById(R.id.resquad)).setText("");
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonQuietQuadr)
        {
            finish();
        }
        else if (v.getId() == R.id.buttonResetQuad){
            ((EditText)findViewById(x2)).setText("");
            ((EditText)findViewById(R.id.x)).setText("");
            ((EditText)findViewById(R.id.nox)).setText("");
            ((TextView)findViewById(R.id.resquad)).setText("");
        }
        else {
            if (TextUtils.isEmpty(((EditText) findViewById(x2)).getText().toString())
                    || TextUtils.isEmpty(((EditText) findViewById(R.id.x)).getText().toString())
                    || TextUtils.isEmpty(((EditText) findViewById(R.id.nox)).getText().toString())) {
                return;
            }

            double a, b, c;
            a = Double.parseDouble(((EditText) findViewById(x2)).getText().toString());
            b = Double.parseDouble(((EditText) findViewById(x)).getText().toString());
            c = Double.parseDouble(((EditText) findViewById(nox)).getText().toString());

            double D = b * b - 4 * a * c;
            if(D < 0) {
                ((TextView) findViewById(R.id.resquad)).setText(R.string.no_solution);
            } else if(D == 0) {
                ((TextView) findViewById(R.id.resquad)).setText("x1,2 = " + (-b / (2 * a)));
            } else {
                if (lengthOfFloatAfterComma(Math.sqrt(D)) >= 5) {
                    double minusb = b * -1;
                    double a2 = a * 2;
                    String[] squareAndFactor = SquareRoot(D);
                    String square = squareAndFactor[0];
                    double factor = Double.parseDouble(squareAndFactor[1]);

                    if (factor != 1) {
                        double gcd = gcd(factor, minusb, a2);
                        minusb /= gcd;
                        factor /= gcd;
                        a2 /= gcd;
                    }

                    String res = "";
                    res += "D = " + D + "\n\n\n";
                    if (a2 != 1) {
                        int resLen = res.length();
                        if (minusb == 0) minusb = (int) minusb;
                        res += "         ";
                        if (factor == 1) {
                            if(minusb != 0) {
                                res += minusb + " + " + square;
                            } else {
                                res += square;
                            }
                        } else if(factor > 0) {
                            if(minusb != 0) {
                                res += minusb + " + " + factor + square;
                            } else {
                                res += factor + square;
                            }
                        } else {
                            if(minusb != 0) {
                                res += minusb + " - " + (factor * -1) + square;
                            } else {
                                res += factor + square;
                            }
                        }
                        resLen = res.length() - resLen;
                        res += "\n";
                        res += "x1 = ";
                        for (int i = 0; i < resLen; ++i) res += "-";
                        res += "\n";
                        for (int i = 0; i < resLen / 2 + 7; ++i) res += " ";
                        res += a2;

                        res += "\n\n\n         ";

                        if (factor == 1) {
                            if(minusb != 0) {
                                res += minusb + " - " + square;
                            } else {
                                res += square;
                            }
                        }
                        else if(factor > 0) {
                            if(minusb != 0) {
                                res += minusb + " - " + factor + square;
                            } else {
                                res += factor + square;
                            }
                        }
                        else {
                            if(minusb != 0) {
                                res += minusb + " + " + (factor * -1) + square;
                            } else {
                                res += factor + square;
                            }
                        }
                        res += "\n";
                        res += "x2 = ";
                        for (int i = 0; i < resLen; ++i) res += "-";
                        res += "\n";
                        for (int i = 0; i < resLen / 2 + 7; ++i) res += " ";
                        res += a2;
                    } else {
                        res += "x1 = ";
                        if (factor == 1) {
                            if (minusb != 0) {
                                res += minusb + " + " + square;
                            } else {
                                res += square;
                            }
                        } else if (factor > 0) {
                            if (minusb != 0) {
                                res += minusb + " + " + factor + square;
                            } else {
                                res += factor + square;
                            }
                        } else {
                            if (minusb != 0) {
                                res += minusb + " - " + (factor * -1) + square;
                            } else {
                                res += factor + square;
                            }
                        }
                        res += "\n\n\n";

                        res += "x2 = ";
                        if (factor == 1) {
                            if (minusb != 0) {
                                res += minusb + " - " + square;
                            } else {
                                res += square;
                            }
                        } else if (factor > 0) {
                            if (minusb != 0) {
                                res += minusb + " - " + factor + square;
                            } else {
                                res += factor + square;
                            }
                        } else {
                            if (minusb != 0) {
                                res += minusb + " + " + (factor * -1) + square;
                            } else {
                                res += factor + square;
                            }
                        }
                    }
                    ((TextView) findViewById(R.id.resquad)).setText(res);

                } else {
                    double x1 = (-b + Math.sqrt(D)) / (2 * a);
                    double x2 = (-b - Math.sqrt(D)) / (2 * a);
                    if(x1 == 0) x1 = (int) x1;
                    if(x2 == 0) x2 = (int) x2;
                    String res = "";
                    res += "D = " + D + "\n\n\n";
                    if(lengthOfFloatAfterComma(x1) < 5) {
                        res += "x1 = " + x1;
                    } else {
                        double up = -b + Math.sqrt(D);
                        double down = 2 * a;
                        double del = gcd(up, down);
                        up /= del;
                        down /= del;
                        int resLen = res.length();
                        res += "          ";
                        res += Double.toString(up);
                        resLen = res.length() - resLen - 4;
                        res += "\n";
                        res += "x1 = ";
                        for (int i = 0; i < resLen; ++i) res += "-";
                        res += "\n      ";
                        for (int i = 0; i < resLen / 2; ++i) res += " ";
                        res += Double.toString(down);
                    }
                    res += "\n\n\n";
                    if(lengthOfFloatAfterComma(x2) < 5) {
                        res += "x2 = " + x2;
                    } else {
                        double up = -b - Math.sqrt(D);
                        double down = 2 * a;
                        double del = gcd(up, down);
                        up /= del;
                        down /= del;
                        int resLen = res.length();
                        res += "          ";
                        res += Double.toString(up);
                        resLen = res.length() - resLen - 4;
                        res += "\n";
                        res += "x2 = ";
                        for (int i = 0; i < resLen; ++i) res += "-";
                        res += "\n      ";
                        for (int i = 0; i < resLen / 2; ++i) res += " ";
                        res += Double.toString(down);
                    }
                    ((TextView) findViewById(R.id.resquad)).setText(res);
                }
            }
        }
    }

    private double gcd(double a, double b, double c) {
        return gcd(a, gcd(b, c));
    }

    private double gcd(double a, double b) {
        if (a == 0) return b;
        return gcd(b % a, a);
    }

    private String[] SquareRoot(double root) {
        int factor = 1;
        for (int i = 2; i * i < root; ++i) {
            if (root % (i * i) == 0) {
                root /= i * i;
                factor *= i;
                i = 1;
            }
        }
        String[] returnArray = new String[2];

        returnArray[0] = "√" + root;
        returnArray[1] = Integer.toString(factor);

        return returnArray;
    }

}