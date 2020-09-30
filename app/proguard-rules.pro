-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

-keepattributes JavascriptInterface
-keepattributes *Annotation*

-dontwarn com.ippopay.**
-keep class com.ippopay.** {*;}

-optimizations !method/inlining/*