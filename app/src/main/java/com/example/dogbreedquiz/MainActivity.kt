package com.example.dogbreedquiz

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var correctCount = 0
    private var inCorrectCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dogButt1 = findViewById<ImageButton>(R.id.dog1)
        val dogButt2 = findViewById<ImageButton>(R.id.dog2)
        val dogButt3 = findViewById<ImageButton>(R.id.dog3)


        val resource: String = "smol_dog"
        val resource_id = resources.getIdentifier(resource, "drawable", "com.example.dogbreedquiz")
        dogButt3.setImageResource(resource_id)

        val dogButtList = listOf(dogButt1, dogButt2, dogButt3)

        var dogMap: HashMap<String, String> = HashMap<String, String>()

        dogMap.put("goldie", "Golden Retriever")
        dogMap.put("amgry_humksy", "Husky")
        dogMap.put("sl_waltation", "Wal Dog")
        dogMap.put("smol_dog", "Corgi")
        dogMap.put("some_kinda_shepard", "Nazi Shepard")
        dogMap.put("small_dog_with_big_ears", "Beagle")
        dogMap.put("pit_bull", "Pit Bull")

        shuffleDogs(dogMap, dogButtList)

        val nextButt = findViewById<Button>(R.id.next_butt)
        nextButt.setOnClickListener {
            shuffleDogs(dogMap, dogButtList)
        }

        val finishButt = findViewById<Button>(R.id.finish_butt)
        finishButt.setOnClickListener {
            val finishIntent = Intent(this, FinishActivity::class.java)
            finishIntent.putExtra("correctCount", correctCount)
            finishIntent.putExtra("inCorrectCount", inCorrectCount)
            startActivity(finishIntent)
        }

    }

    private fun shuffleDogs(dogMap: HashMap<String, String>, dogButtList: List<ImageButton>) {
        val dogBreedText = findViewById<TextView>(R.id.breed_name)

        val selectedKeys = dogMap.keys.shuffled().take(3)
        val pickedDogMap = HashMap<String, String>()

        for (key in selectedKeys) {
            pickedDogMap[key] = dogMap[key]!!
        }

        var i = 0
        for ((key, value) in pickedDogMap) {
            assignDogs(dogButtList[i], key, value)
            i++
        }
        dogBreedText.text = pickedDogMap.values.toList().shuffled().first()
        dogBreedText.setTextColor(Color.parseColor("#808080"))
        for (imgButt in dogButtList) {
        }
        checkDog(dogButtList, dogBreedText)

        for (imgButt in dogButtList) {
            imgButt.isEnabled = true
            imgButt.isClickable = true
        }
    }

    private fun assignDogs(imgButt: ImageButton, fileName: String, imgDes: String) {
        val resource_id = resources.getIdentifier(fileName, "drawable", "com.example.dogbreedquiz")
        imgButt.setImageResource(resource_id)
        imgButt.contentDescription = imgDes
    }

    private fun checkDog(dogButtList: List<ImageButton>, dogBreedText: TextView) {
        for (imgButt in dogButtList) {
            imgButt.setOnClickListener() {
                if (imgButt.contentDescription.equals(dogBreedText.text)) {
                    dogBreedText.text = "CORRECT!"
                    dogBreedText.setTextColor(Color.parseColor("#00FF00"))
                    correctCount++
                } else {
                    dogBreedText.text = "INCORRECT!"
                    dogBreedText.setTextColor(Color.parseColor("#FF0000"))
                    inCorrectCount++
                }
                for (imgButt in dogButtList) {
                    imgButt.isEnabled = false
                    imgButt.isClickable = false
                }
            }
        }
    }
}