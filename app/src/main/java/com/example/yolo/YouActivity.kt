package com.example.yolo

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class YouActivity : AppCompatActivity() {

    private lateinit var imgProfile: ImageView

    private val PICK_IMAGE = 1
    private val CAMERA_REQUEST = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_you)

        // PROFILE IMAGE

        imgProfile =
            findViewById(R.id.imgProfile)

        // USERNAME

        val txtUsername =
            findViewById<TextView>(R.id.txtUsername)

        val sharedPref =
            getSharedPreferences(
                "UserData",
                MODE_PRIVATE
            )

        val username =
            sharedPref.getString(
                "username",
                "YOLO User"
            )

        txtUsername.text = username

        // EDIT PROFILE BUTTON

        val btnEditProfile =
            findViewById<Button>(R.id.btnEditProfile)

        btnEditProfile.setOnClickListener {

            val dialogView =
                layoutInflater.inflate(
                    R.layout.dialog_profile,
                    null
                )

            val dialog =
                AlertDialog.Builder(this)
                    .setView(dialogView)
                    .create()

            dialog.show()

            // CAMERA

            val layoutCamera =
                dialogView.findViewById<LinearLayout>(
                    R.id.layoutCamera
                )

            layoutCamera.setOnClickListener {

                val cameraIntent =
                    Intent(
                        MediaStore.ACTION_IMAGE_CAPTURE
                    )

                startActivityForResult(
                    cameraIntent,
                    CAMERA_REQUEST
                )

                dialog.dismiss()
            }

            // GALLERY

            val layoutGallery =
                dialogView.findViewById<LinearLayout>(
                    R.id.layoutGallery
                )

            layoutGallery.setOnClickListener {

                val intent =
                    Intent(Intent.ACTION_GET_CONTENT)

                intent.type = "image/*"

                startActivityForResult(
                    intent,
                    PICK_IMAGE
                )

                dialog.dismiss()
            }

            // DELETE PHOTO

            val layoutDelete =
                dialogView.findViewById<LinearLayout>(
                    R.id.layoutDelete
                )

            layoutDelete.setOnClickListener {

                imgProfile.setImageResource(
                    R.drawable.ic_user
                )

                Toast.makeText(
                    this,
                    "Profile Photo Deleted",
                    Toast.LENGTH_SHORT
                ).show()

                dialog.dismiss()
            }
        }

        // EDIT USERNAME BUTTON

        val btnEditUsername =
            findViewById<Button>(R.id.btnEditUsername)

        btnEditUsername.setOnClickListener {

            val editText =
                EditText(this)

            editText.hint =
                "Enter new username"

            AlertDialog.Builder(this)
                .setTitle("Edit Username")
                .setView(editText)

                .setPositiveButton(
                    "Save"
                ) { _, _ ->

                    val newUsername =
                        editText.text.toString()

                    if (
                        newUsername.isNotEmpty()
                    ) {

                        txtUsername.text =
                            newUsername

                        val sharedPref =
                            getSharedPreferences(
                                "UserData",
                                MODE_PRIVATE
                            )

                        sharedPref.edit()
                            .putString(
                                "username",
                                newUsername
                            )
                            .apply()

                        Toast.makeText(
                            this,
                            "Username Updated",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                .setNegativeButton(
                    "Cancel",
                    null
                )

                .show()
        }

        // PURCHASE DROPDOWN

        val btnPurchases =
            findViewById<LinearLayout>(
                R.id.btnPurchases
            )

        val layoutPurchases =
            findViewById<LinearLayout>(
                R.id.layoutPurchases
            )

        val txtArrow =
            findViewById<TextView>(
                R.id.txtArrow
            )

        btnPurchases.setOnClickListener {

            if (
                layoutPurchases.visibility
                == View.GONE
            ) {

                layoutPurchases.visibility =
                    View.VISIBLE

                txtArrow.text = "⌃"

            } else {

                layoutPurchases.visibility =
                    View.GONE

                txtArrow.text = "⌄"
            }
        }

        // HOME

        val navHome =
            findViewById<LinearLayout>(
                R.id.navHome
            )

        navHome.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    MainActivity::class.java
                )
            )
        }

        // CATEGORY

        val navCategory =
            findViewById<LinearLayout>(
                R.id.navCategory
            )

        navCategory.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    CategoryActivity::class.java
                )
            )
        }

        // CART

        val navCart =
            findViewById<LinearLayout>(
                R.id.navCart
            )

        navCart.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    CartActivity::class.java
                )
            )
        }

        // LOGOUT

        val navLogout =
            findViewById<LinearLayout>(
                R.id.navLogout
            )

        navLogout.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    LoginActivity::class.java
                )
            )

            finish()
        }
    }

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

        // GALLERY IMAGE

        if (
            requestCode == PICK_IMAGE &&
            resultCode == Activity.RESULT_OK &&
            data != null &&
            data.data != null
        ) {

            val imageUri: Uri? =
                data.data

            imgProfile.setImageURI(
                imageUri
            )
        }

        // CAMERA IMAGE

        else if (
            requestCode == CAMERA_REQUEST &&
            resultCode == Activity.RESULT_OK
        ) {

            val photo =
                data?.extras?.get("data")

            imgProfile.setImageBitmap(
                photo as Bitmap
            )
        }
    }
}