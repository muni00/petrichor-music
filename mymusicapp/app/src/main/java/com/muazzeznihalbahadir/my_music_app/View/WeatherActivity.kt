package com.muazzeznihalbahadir.my_music_app.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.muazzeznihalbahadir.my_music_app.R
import com.muazzeznihalbahadir.my_music_app.databinding.ActivityWeatherBinding
import org.json.JSONException
import org.json.JSONObject

class WeatherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWeatherBinding
    var latitude = ""
    var longitude = ""
    var weather = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        latitude = intent.getStringExtra("latitude").toString()
        longitude = intent.getStringExtra("longitude").toString()

        Toast.makeText(this, latitude, Toast.LENGTH_LONG).show()

        getJsonData(latitude,longitude)

        binding.btnAdvice.setOnClickListener{
            val intentAdvice = Intent(this, AdvicePageActivity::class.java)
            intentAdvice.putExtra("weather", weather )
            startActivity(intentAdvice)
        }

    }

    private fun getJsonData(lat:String?,lon:String?){

        val API_KEY = "6770512dfbbdeca4422e56ad3d126730"
        val url = "https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${API_KEY}"
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {response ->
                try {
                    val jsonObject = JSONObject(response)
                    Toast.makeText(this, "Response is: ${response}", Toast.LENGTH_SHORT).show()
                    setValues(jsonObject)

                }catch (e: JSONException){
                    e.printStackTrace()
                }
                Toast.makeText(this, "Response is: ${response}", Toast.LENGTH_SHORT).show()
            },
            { Toast.makeText(this, "That didn't work! : " + it.message , Toast.LENGTH_SHORT).show()})

        queue.add(stringRequest)
    }
    private fun setValues(response: JSONObject) {
        var tempr = response.getJSONObject("main").getString("temp")
        tempr = ((tempr.toDouble()-273.15).toInt()).toString()
        binding.derece.text = "${tempr}°C"
        Toast.makeText(this,tempr,Toast.LENGTH_LONG).show()
        weather =  response.getJSONArray("weather").getJSONObject(0).getString("main")
        binding.ilce.text = response.getJSONArray("weather").getJSONObject(0).getString("description")

        when(weather){
            "Thunderstorm" -> binding.layoutMain.setBackgroundResource(R.drawable.thunderstorm)
            "Drizzle"      -> binding.layoutMain.setBackgroundResource(R.drawable.drizzle)
            "Rain"         -> binding.layoutMain.setBackgroundResource(R.drawable.rain)
            "Snow"         -> binding.layoutMain.setBackgroundResource(R.drawable.snow)
            "Mist"         -> binding.layoutMain.setBackgroundResource(R.drawable.mist)
            "Smoke"        -> binding.layoutMain.setBackgroundResource(R.drawable.mist)
            "Haze"         -> binding.layoutMain.setBackgroundResource(R.drawable.mist)
            "Dust"         -> binding.layoutMain.setBackgroundResource(R.drawable.mist)
            "Fog"          -> binding.layoutMain.setBackgroundResource(R.drawable.mist)
            "Sand"         -> binding.layoutMain.setBackgroundResource(R.drawable.mist)
            "Ash"          -> binding.layoutMain.setBackgroundResource(R.drawable.mist)
            "Squall"       -> binding.layoutMain.setBackgroundResource(R.drawable.squalls)
            "Tornado"      -> binding.layoutMain.setBackgroundResource(R.drawable.tornado)
            "Clear"        -> binding.layoutMain.setBackgroundResource(R.drawable.clear)
            "Clouds"       -> binding.layoutMain.setBackgroundResource(R.drawable.cloudy)

        }
        binding.sehir.text = response.getString("name")
    }
}



