package com.ippopay.sample;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.ippopay.core.IppoPayListener;
import com.ippopay.core.IppoPayLog;
import com.ippopay.core.IppoPayPay;
import com.ippopay.models.Customer;
import com.ippopay.models.OrderData;

public class ActDemoPay extends AppCompatActivity implements IppoPayListener {

    private EditText edtAmount, edtCurrencyCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_demo_pay);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        edtAmount = findViewById(R.id.edt_cost);
        edtCurrencyCode = findViewById(R.id.edt_currency);
        findViewById(R.id.btn_guest_pay).setOnClickListener(v -> onPaymentClick());
    }

    private void onPaymentClick() {
        try {
            String amount = edtAmount.getText().toString();
            String currencyCode = edtCurrencyCode.getText().toString();
            if (amount.isEmpty())
                showToast("Amount is empty");
            else if (currencyCode.isEmpty())
                showToast("CurrencyCode is empty");
            else {
                IppoPayLog.setLogVisible(true);
                IppoPayPay.init(this, "YOUR_MERCHANT_KEY_HERE");
                OrderData orderData = new OrderData();
                orderData.setOrderId("ORDER_ID_HERE");
                orderData.setOrderAmount(Double.parseDouble(amount));
                orderData.setCustomColor("#780991");
                orderData.setFont(ResourcesCompat.getFont(this, R.font.poppins_medium));
                orderData.setOrderDescription("Mobile Phone");
                orderData.setCurrencyCode(currencyCode);
                Customer customer = new Customer();
                customer.setName("John Doe");
                customer.setEmail("email@gmail.com");
                customer.setMobile("123456789");
                orderData.setCustomer(customer);
                IppoPayPay.setPaymentListener(this);
                IppoPayPay.makePayment(orderData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTransactionSuccess(String transactionId) {
        showToast("isSuccess::" + transactionId);
    }

    @Override
    public void onTransactionFailure(String error, String transaction_id) {
        Log.d("Error::", "payment error:: " + error);
        showToast(error);
    }

    @Override
    public void onTransactionCancelled() {
        Log.d("Cancel::", "Cancelled");
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}