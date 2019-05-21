package me.effer.counter

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var currentCount = 0
    private var highCount = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        highCount = getHighCount()
        txt_highCount.text = getString(R.string.txt_highCount_prefix, highCount)
        txt_count.text = getString(R.string.txt_count_prefix, currentCount)

        btn_plus.setOnClickListener {
            currentCount += 1
            txt_count.text = getString(R.string.txt_count_prefix, currentCount)
            if (currentCount > highCount) {
                highCount = currentCount
                setHighCount(highCount)
                txt_highCount.text = getString(R.string.txt_highCount_prefix, highCount)
            }
        }

    }

    private fun getHighCount():Int {
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
        return sharedPref.getInt(getString(R.string.saved_high_count_key), 0)
    }

    private fun setHighCount(n:Int){
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
        with(sharedPref.edit()){
            putInt(getString(R.string.saved_high_count_key), n)
            apply()
        }
    }


}
