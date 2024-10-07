package com.example.kashier_integration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import com.kashier.integration.factory.KashierIntegrationFactory
import com.kashier.integration.interfaces.KashierIntegrationCallback
import com.kashier.integration.interfaces.KashierSdk
import com.kashier.integration.keys.Currencies
import com.kashier.integration.models.KashierIntegrationResponse

class NativeActivity : ComponentActivity(), KashierIntegrationCallback {
    private val kashierSdk: KashierSdk = KashierIntegrationFactory.create(this, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Text("Hello from Native!")
            Button(onClick ={
                startSale()
            } ,content = {Text("click")} )
        }
//        startSale()
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
        TODO("Not yet implemented")
        Log.d("KKKKKKKKKKKKKAAAAAAAA",kashierIntegrationResponse.toString())
    }
}

