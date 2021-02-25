package com.ippopay.sample;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.ippopay.core.IppoPayListener;
import com.ippopay.core.IppoPayLog;
import com.ippopay.core.IppoPayPay;
import com.ippopay.models.OrderData;

public class ActDemoPay extends AppCompatActivity implements IppoPayListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_demo_pay);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        findViewById(R.id.btn_guest_pay).setOnClickListener(v -> onPaymentClick());
    }

    private void onPaymentClick() {
        try {
            IppoPayLog.setLogVisible(true);
            IppoPayPay.init(this, "PUBLIC_KEY");
            OrderData orderData = new OrderData();
            orderData.setOrderId("ORDER_ID");
            orderData.setCustomColor("#780991");
            orderData.setFont(ResourcesCompat.getFont(this, R.font.poppins_medium));
            IppoPayPay.setPaymentListener(this);
            IppoPayPay.makePayment(orderData);
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