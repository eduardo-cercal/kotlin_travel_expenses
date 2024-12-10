package br.com.eduardocercal.travelexpenses

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.eduardocercal.travelexpenses.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private  lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.buttonCalculate.setOnClickListener(this)

    }

    override fun onClick(view: View) {
        calculate()
    }

    private fun calculate(){
        if(isValid()){
            val distance = binding.editDistance.text.toString().toFloat()
            val price = binding.editPrice.text.toString().toFloat()
            val autonomy = binding.editAutonomy.text.toString().toFloat()

            val totalValue = (distance*price)/autonomy


            binding.textTotalValue.text =  "R$ ${"%.2f".format(totalValue)}"
            return
        }

        Toast.makeText(this,R.string.fill_all_fields,Toast.LENGTH_LONG).show()
    }

    private fun isValid(): Boolean{
        return  (binding.editDistance.text.toString()!=""&&binding.editPrice.text.toString()!=""&&binding.editAutonomy.text.toString()!=""&&binding.editAutonomy.text.toString().toFloat()!=0f)
    }
}