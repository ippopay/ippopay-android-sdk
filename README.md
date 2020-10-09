# Ippopay Android SDK Integration

## Step - 1 - Add Dependency

We distribute our SDK from the Maven Central Repository. To begin with this SDK open your build.gradle file of Module:app and
add the following dependency.

```

implementation 'com.ippopay:IppoPaySDK:1.0.5'
```

## Step - 2 - Initialize SDK 

To initialize the SDK add the below line of code with the public key you retrieved from ippopay merchant panel. If you don't have a public key create new one.

```
IppoPayPay.init(this, "Your Unique Public Key");
```

## Step - 3 - Create Order Data Object with necessary inputs

You can create the order data or payment input with our OrderData Model class. Here you need to provide order id, descripiton, order amount and customer details like name, email, mobile number.

```

OrderData orderData = new OrderData(); // Ippopay Order Data Model Class Instance
//orderData.setCustomColor("#780991"); // make payment page loading color as app color.
//orderData.setFont(ResourcesCompat.getFont(this,R.font.poppins_medium)); // make payment page text font as app font.
orderData.setOrderDescription("Mobile Phone");  // any description.
orderData.setOrderId(String.valueOf(orderId)); // unique order id.
Customer customer = new Customer();
customer.setName("name");
customer.setEmail("email@gmail.com");
customer.setMobile("1234567890");
orderData.setCustomer(customer);

```

## Step - 4 - Implement Payment Listener

Set and Implement our payment listener to receive the payment result for the payment we going to make in Step - 5. Use the below code to obtain the payment result.

```

//Setting payment listener (paste this line after init() method)
IppoPayPay.setPaymentListener(this);


//Implementing
public class ActDemoPay extends AppCompatActivity implements IppoPayListener {

    @Override
    public void onTransactionSuccess(String transactionId) {
        // Success Callback
    }

    @Override
    public void onTransactionFailure(String error,String transactionId) {
        // Failure Callback.
    }

    @Override
    public void onTransactionCancelled() {
        // Transaction Cancelled by User
    }

}

```

## Step - 5 - Make Transaction with Ippopay

Use the below line of code to make the payment with the order data you created in Step - 3

```

IppoPayPay.makePayment(orderData);

```

## Ippopay Log

You can enable / disable the SDK logs by using the below line of code. By default it will be disabled. 

```

IppoPayLog.setLogVisible(true or false);
```

## Progurad Rules

If you are using Proguard for your builds, modify the Proguard rule file:

```

-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

-keepattributes JavascriptInterface
-keepattributes *Annotation*

-dontwarn com.ippopay.**
-keep class com.ippopay.** {*;}

-optimizations !method/inlining/*

```

## Sample Payment Reference.

Please check [this link](https://github.com/ippopay/ippopay-android-sdk/blob/master/app/src/main/java/com/ippopay/sample/ActDemoPay.java) for sample payment with above steps.

