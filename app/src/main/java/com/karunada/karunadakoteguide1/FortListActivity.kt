package com.karunada.karunadakoteguide1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class FortListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_fort_list)

        // CARD REFERENCES

        val chitradurga =
            findViewById<CardView>(R.id.chitradurgaCard)

        val bidar =
            findViewById<CardView>(R.id.bidarCard)

        val belagavi =
            findViewById<CardView>(R.id.belagaviCard)

        // CHITRADURGA

        chitradurga.setOnClickListener {

            val intent =
                Intent(
                    this,
                    FortDetailActivity::class.java
                )

            intent.putExtra(
                "FORT_NAME",
                "Chitradurga Kote"
            )

            startActivity(intent)
        }

        // BIDAR

        bidar.setOnClickListener {

            val intent =
                Intent(
                    this,
                    FortDetailActivity::class.java
                )

            intent.putExtra(
                "FORT_NAME",
                "Bidar Kote"
            )

            startActivity(intent)
        }

        // BELAGAVI

        belagavi.setOnClickListener {

            val intent =
                Intent(
                    this,
                    FortDetailActivity::class.java
                )

            intent.putExtra(
                "FORT_NAME",
                "Belagavi Kote"
            )

            startActivity(intent)
        }
    }
}