package com.karunada.karunadakoteguide1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)

        val exploreBtn =
            findViewById<Button>(R.id.exploreBtn)

        val logoutBtn =
            findViewById<Button>(R.id.logoutBtn)

        exploreBtn.setOnClickListener {

            startActivity(
                Intent(this, FortListActivity::class.java)
            )
        }

        logoutBtn.setOnClickListener {

            startActivity(
                Intent(this, LoginActivity::class.java)
            )

            finish()
        }
    }
}