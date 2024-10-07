package com.example.kashier_integration
import android.content.Intent
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.annotation.NonNull
import com.kashier.integration.enums.KashierIntegrationStatusCode
import com.kashier.integration.factory.KashierIntegrationFactory
import com.kashier.integration.interfaces.KashierIntegrationCallback
import com.kashier.integration.interfaces.KashierSdk
import com.kashier.integration.keys.Currencies
import com.kashier.integration.models.KashierIntegrationResponse
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.android.FlutterActivityLaunchConfigs
import io.flutter.embedding.android.FlutterFragmentActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel


class MainActivity: FlutterFragmentActivity(), KashierIntegrationCallback{

    private val kashierSdk: KashierSdk = KashierIntegrationFactory.create(this, this)

    private val CHANNEL: String = "kashier_payment_channel"

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->
            if (call.method == "start_sale") {
//                startActivity(Intent(this, NativeActivity::class.java))
                startSale()
                result.success("start_sale")
            }
            else{
                result.success("Error")
            }
        }
    }

    private fun startSale(){
        kashierSdk.startSale(
             "100.0",
            Currencies.EGP,
            "id_value",
            true,
            mapOf(
                "key" to "value"),
            "https://www.weebhook.com"
        )
    }

    override fun onResponse(rspCode: Int, kashierIntegrationResponse: KashierIntegrationResponse) {
        when (rspCode) {
            KashierIntegrationStatusCode.SUCCESS.code -> {

//                Log.d("elsarag", "onResponse: ###################")
            }
            KashierIntegrationStatusCode.FAILURE.code -> {


//                Log.d("elsarag", "onResponse: ############2#######")

            }
            KashierIntegrationStatusCode.CANCELLED.code -> {


//                Log.d("elsarag", "onResponse: ###########3########")

            }
        }
    }



}
