package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity  implements TextWatcher,SeekBar.OnSeekBarChangeListener{


    // ----------> Rene Clever <------------------------------------------------------------------


    //declare your variables for the widgets
    private EditText editTextBillAmount;
    private TextView textViewBillAmount;
    private TextView textViewPercent;
    private TextView tipTextView;
    private TextView totalTextView;
    private SeekBar ss;




    //declare the variables for the calculations
    private double billAmount = 0.0;
    private double percent = .15;

    //set the number formats to be used for the $ amounts , and % amounts
    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //add Listeners to Widgets
        editTextBillAmount = (EditText)findViewById(R.id.editText_BillAmount);
        textViewPercent = (TextView)findViewById(R.id.percent_textView);
        tipTextView = (TextView)findViewById(R.id.tipAmount_textView);
        totalTextView = (TextView)findViewById(R.id.textView9);

        editTextBillAmount.addTextChangedListener((TextWatcher) this);

        textViewBillAmount = (TextView)findViewById(R.id.textView_BillAmount);


         ss = (SeekBar) findViewById(R.id.percent_seekBar);
         ss.setOnSeekBarChangeListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    /*
    Note:   int i, int i1, and int i2
            represent start, before, count respectively
            The charSequence is converted to a String and parsed to a double for you
     */
    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        Log.d("MainActivity", "inside onTextChanged method: charSequence= "+charSequence);



        try { // get bill amount and display currency formatted value
             billAmount = Double.parseDouble(charSequence.toString()) / 100.0;
             textViewBillAmount.setText(currencyFormat.format(billAmount));
             }
        catch (NumberFormatException e) { // if s is empty or non-numeric
            textViewBillAmount.setText("");
            billAmount = 0.0;
            }

         calculate();
//        billAmount = Double.parseDouble(charSequence.toString()) / 100; Log.d("MainActivity", "Bill Amount = "+billAmount);
//
//        textViewBillAmount.setText(currencyFormat.format(billAmount));
//
//        calculate();
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }


    // calculate and display tip and total amounts
    private void calculate() {
        Log.d("MainActivity", "inside calculate method");

        textViewPercent.setText(percentFormat.format(percent));
       double tip = billAmount * percent;
       double total = billAmount + tip;
       tipTextView.setText(currencyFormat.format(tip));
       totalTextView.setText(currencyFormat.format(total));


    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        percent = progress / 100.0; //calculate percent based on seeker value
        calculate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //textViewPercent.setText(seekBar.getProgress()/100);

    }
}
