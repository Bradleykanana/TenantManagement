package com.example.tenantmanagement
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tenantmanagement.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private var lastTenant: Tenant? = null

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {

            val name = binding.tenantNameEditText.text.toString()
            val phone = binding.phoneEditText.text.toString()
            val rent = binding.rentEditText.text.toString()

            val tenant = Tenant(name, phone, rent)
            lastTenant = tenant

            binding.tenant = tenant
        }
        binding.callButton.setOnClickListener {
            val tenant = lastTenant

            if (tenant == null) {
                Toast.makeText(
                    this,
                    "Save a tenant first",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val dialIntent = Intent(
                Intent.ACTION_DIAL,
                Uri.parse("tel:${tenant.phone}")
            )

            startActivity(dialIntent)
        }
    }
}

