package com.karunada.karunadakoteguide1

import android.Manifest
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.Location
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.io.File
import java.io.FileOutputStream

class FortDetailActivity : AppCompatActivity() {

    private lateinit var fortTitle: TextView
    private lateinit var fortDesc: TextView
    private lateinit var fortMap: ImageView
    private lateinit var capturedImage: ImageView
    private lateinit var progressBar: ProgressBar
    private lateinit var xpText: TextView
    private lateinit var achievementText: TextView
    private lateinit var storyCountText: TextView
    private lateinit var touristJourney: TextView

    private lateinit var fusedLocationClient:
            FusedLocationProviderClient

    private lateinit var sharedPreferences:
            SharedPreferences

    private var isKannada = false

    private var currentStory = 0

    private var xp = 0

    private var visitedForts = 0

    private lateinit var stories: Array<String>

    private lateinit var storiesKannada: Array<String>

    private lateinit var mediaPlayer: MediaPlayer

    companion object {

        const val CAMERA_REQUEST = 100

        const val CAMERA_PERMISSION_CODE = 101

        const val LOCATION_PERMISSION_CODE = 102
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_fort_detail)

        // INITIALIZE

        fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(this)

        sharedPreferences =
            getSharedPreferences(
                "KarunadaTouristData",
                MODE_PRIVATE
            )

        // VIEW IDS

        fortTitle =
            findViewById(R.id.fortTitle)

        fortDesc =
            findViewById(R.id.fortDescription)

        fortMap =
            findViewById(R.id.fortMap)

        capturedImage =
            findViewById(R.id.capturedImage)

        progressBar =
            findViewById(R.id.storyProgressBar)

        xpText =
            findViewById(R.id.xpText)

        achievementText =
            findViewById(R.id.achievementText)

        storyCountText =
            findViewById(R.id.storyCountText)

        touristJourney =
            findViewById(R.id.touristJourney)

        val playBtn =
            findViewById<Button>(R.id.playStoryBtn)

        val challengeBtn =
            findViewById<Button>(R.id.challengeBtn)

        val languageBtn =
            findViewById<Button>(R.id.languageBtn)

        val aiGuideBtn =
            findViewById<Button>(R.id.aiGuideBtn)

        val galleryBtn =
            findViewById<Button>(R.id.galleryBtn)

        val mapBtn =
            findViewById<Button>(R.id.mapBtn)

        val fortName =
            intent.getStringExtra("FORT_NAME")

        // LOAD SAVED DATA

        xp =
            sharedPreferences.getInt("XP", 0)

        visitedForts =
            sharedPreferences.getInt(
                "VISITED_FORTS",
                0
            )

        xpText.text =
            "Tourist XP: $xp"

        touristJourney.text =
            "Visited Forts: $visitedForts"

        // FORT DATA

        when (fortName) {

            "Chitradurga Kote" -> {

                fortTitle.text =
                    "Chitradurga Kote"

                fortMap.setImageResource(
                    R.drawable.chitradurga_map
                )

                stories = arrayOf(

                    "Onake Obavva defended Chitradurga Fort bravely using a wooden pestle against enemy soldiers.",

                    "Chitradurga Fort was protected by seven concentric massive stone walls.",

                    "The fort used secret tunnels and advanced water harvesting systems."
                )

                storiesKannada = arrayOf(

                    "ಒನಕೆ ಓಬವ್ವ ಶತ್ರು ಸೈನಿಕರನ್ನು ಒನಕೆಯಿಂದ ಹೊಡೆದು ಕೋಟೆಯನ್ನು ರಕ್ಷಿಸಿದರು.",

                    "ಚಿತ್ರದುರ್ಗ ಕೋಟೆ ಏಳು ಬಲಿಷ್ಠ ಗೋಡೆಗಳಿಂದ ರಕ್ಷಿಸಲ್ಪಟ್ಟಿತ್ತು.",

                    "ಕೋಟೆಯಲ್ಲಿ ಗುಪ್ತ ಸುರಂಗಗಳು ಮತ್ತು ನೀರು ಸಂಗ್ರಹ ವ್ಯವಸ್ಥೆಗಳು ಇದ್ದವು."
                )
            }

            "Bidar Kote" -> {

                fortTitle.text =
                    "Bidar Kote"

                fortMap.setImageResource(
                    R.drawable.bidar
                )

                stories = arrayOf(

                    "Bidar Fort was the capital of the Bahmani Sultanate.",

                    "The fort contains Persian architecture and royal palaces.",

                    "Bidar Fort had underground water systems called Karez."
                )

                storiesKannada = arrayOf(

                    "ಬಿದರ್ ಕೋಟೆ ಬಹಮನಿ ಸುಲ್ತಾನರ ರಾಜಧಾನಿಯಾಗಿತ್ತು.",

                    "ಈ ಕೋಟೆಯಲ್ಲಿ ಪರ್ಷಿಯನ್ ಅರಮನೆಗಳಿವೆ.",

                    "ಬಿದರ್ ಕೋಟೆಯಲ್ಲಿ ಕರೇಜ್ ಎಂಬ ಭೂಗತ ನೀರಿನ ವ್ಯವಸ್ಥೆ ಇತ್ತು."
                )
            }

            else -> {

                fortTitle.text =
                    "Belagavi Kote"

                fortMap.setImageResource(
                    R.drawable.belagavi
                )

                stories = arrayOf(

                    "Belagavi Fort was ruled by several dynasties.",

                    "The fort is surrounded by strong military walls.",

                    "Ancient temples reflect Karnataka heritage."
                )

                storiesKannada = arrayOf(

                    "ಬೆಳಗಾವಿ ಕೋಟೆಯನ್ನು ಅನೇಕ ವಂಶಗಳು ಆಳಿದವು.",

                    "ಕೋಟೆ ಬಲಿಷ್ಠ ಸೈನಿಕ ಗೋಡೆಗಳಿಂದ ಸುತ್ತುವರಿದಿದೆ.",

                    "ಕೋಟೆಯ ದೇವಾಲಯಗಳು ಕರ್ನಾಟಕದ ಪರಂಪರೆಯನ್ನು ತೋರಿಸುತ್ತವೆ."
                )
            }
        }

        // DEFAULT STORY

        fortDesc.text =
            stories[0]

        storyCountText.text =
            "Story 1 / 3"

        // STORY PLAY SYSTEM

        playBtn.setOnClickListener {

            if (currentStory < 3) {

                val selectedStory =
                    if (isKannada)
                        storiesKannada[currentStory]
                    else
                        stories[currentStory]

                fortDesc.text =
                    selectedStory

                // MEDIA PLAYER AUDIO

                mediaPlayer =
                    MediaPlayer.create(
                        this,
                        R.raw.chitradurga
                    )

                mediaPlayer.start()

                currentStory++

                progressBar.progress =
                    currentStory * 33

                xp += 40

                xpText.text =
                    "Tourist XP: $xp"

                storyCountText.text =
                    "Story $currentStory / 3"

                saveTouristData()

                // ACHIEVEMENTS

                if (currentStory == 1) {

                    achievementText.text =
                        "Achievement Unlocked: Explorer Badge 🏆"
                }

                if (currentStory == 2) {

                    achievementText.text =
                        "Achievement Unlocked: Historian Badge 🏰"
                }

                if (currentStory == 3) {

                    achievementText.text =
                        "Achievement Unlocked: Karnataka Pride 👑"

                    playBtn.text =
                        "All Stories Completed ✓"

                    playBtn.isEnabled =
                        false

                    visitedForts++

                    touristJourney.text =
                        "Visited Forts: $visitedForts"

                    saveTouristData()
                }

                Toast.makeText(
                    this,
                    "Story Narration Playing...",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // LANGUAGE TOGGLE

        languageBtn.setOnClickListener {

            isKannada = !isKannada

            if (isKannada) {

                languageBtn.text =
                    "Switch to English"

                fortTitle.text =
                    when (fortName) {

                        "Chitradurga Kote" ->
                            "ಚಿತ್ರದುರ್ಗ ಕೋಟೆ"

                        "Bidar Kote" ->
                            "ಬಿದರ್ ಕೋಟೆ"

                        else ->
                            "ಬೆಳಗಾವಿ ಕೋಟೆ"
                    }

                fortDesc.text =
                    storiesKannada[
                        currentStory.coerceAtLeast(1) - 1
                    ]

            } else {

                languageBtn.text =
                    "Switch to Kannada"

                fortTitle.text =
                    fortName

                fortDesc.text =
                    stories[
                        currentStory.coerceAtLeast(1) - 1
                    ]
            }
        }

        // AI GUIDE

        aiGuideBtn.setOnClickListener {

            Toast.makeText(
                this,
                "AI Guide: This fort used advanced military engineering, hidden tunnels and strategic watch towers.",
                Toast.LENGTH_LONG
            ).show()
        }

        // CAMERA SYSTEM

        challengeBtn.setOnClickListener {

            checkCameraPermission()
        }

        // TOURIST GALLERY

        galleryBtn.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    TouristGalleryActivity::class.java
                )
            )
        }

        // LIVE MAP

        mapBtn.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    FortMapActivity::class.java
                )
            )
        }

        // INTERACTIVE MAP POINT

        fortMap.setOnClickListener {

            Toast.makeText(
                this,
                "Must-See Point Selected!",
                Toast.LENGTH_SHORT
            ).show()

            checkTouristLocation()
        }
    }

    // SAVE DATA

    private fun saveTouristData() {

        sharedPreferences.edit()

            .putInt("XP", xp)

            .putInt(
                "VISITED_FORTS",
                visitedForts
            )

            .apply()
    }

    // GPS LOCATION

    private fun checkTouristLocation() {

        if (
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION
                ),
                LOCATION_PERMISSION_CODE
            )

            return
        }

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->

                if (location != null) {

                    Toast.makeText(
                        this,
                        "Beacon Activated Near Tourist Landmark!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    // CAMERA PERMISSION

    private fun checkCameraPermission() {

        if (
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {

            openCamera()

        } else {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_CODE
            )
        }
    }

    // OPEN CAMERA

    private fun openCamera() {

        val cameraIntent =
            Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        startActivityForResult(
            cameraIntent,
            CAMERA_REQUEST
        )
    }

    // CAMERA RESULT

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {

        super.onActivityResult(
            requestCode,
            resultCode,
            data
        )

        if (
            requestCode == CAMERA_REQUEST &&
            resultCode == RESULT_OK &&
            data != null
        ) {

            val photo =
                data.extras?.get("data") as? Bitmap

            if (photo != null) {

                capturedImage.setImageBitmap(photo)

                saveImage(photo)

                xp += 50

                xpText.text =
                    "Tourist XP: $xp"

                saveTouristData()

                Toast.makeText(
                    this,
                    "Fort Photo Saved Permanently! +50 XP",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    // SAVE IMAGE

    private fun saveImage(bitmap: Bitmap) {

        val folder =
            File(
                getExternalFilesDir(
                    Environment.DIRECTORY_PICTURES
                ),
                "KarunadaKote"
            )

        if (!folder.exists()) {

            folder.mkdirs()
        }

        val file =
            File(
                folder,
                "FORT_${System.currentTimeMillis()}.jpg"
            )

        val outputStream =
            FileOutputStream(file)

        bitmap.compress(
            Bitmap.CompressFormat.JPEG,
            100,
            outputStream
        )

        outputStream.flush()

        outputStream.close()
    }

    override fun onDestroy() {

        super.onDestroy()

        if (::mediaPlayer.isInitialized) {

            mediaPlayer.stop()

            mediaPlayer.release()
        }
    }
}