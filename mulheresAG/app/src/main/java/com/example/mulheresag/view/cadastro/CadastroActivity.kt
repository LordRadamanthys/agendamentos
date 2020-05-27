package com.example.mulheresag.view.cadastro

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.ImageView
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.example.mulheresag.R
import com.example.mulheresag.data.remote.model.UserModel
import com.example.mulheresag.infra.App
import com.example.mulheresag.infra.ManagePermissions
import com.example.mulheresag.infra.getListPermissions
import com.example.mulheresag.view.DialogExamples
import kotlinx.android.synthetic.main.activity_cadastro.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


class CadastroActivity : AppCompatActivity(), CadastroContract.View {
    private lateinit var presenter: CadastroContract.Presenter
    private lateinit var progressBar: View
    private lateinit var imageUser: ImageView
    private lateinit var switchAdmin: Switch
    private lateinit var picturePath: String
    private lateinit var managePermissions: ManagePermissions
    private val REQUEST_SELECT_IMAGE_IN_ALBUM = 2121
    private val PERMISSION_REQUEST_CODE = 123
    private lateinit var multipartBody: MultipartBody.Part

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        var id = intent.extras?.get("id") as Int
        permissions()
        initComponents()

        if (App.isAdmin) {
            switch_admin.visibility = View.VISIBLE
        }

        actionsButton()
    }

    private fun actionsButton() {
        imageUser.setOnClickListener {

            selectImageInAlbum()
        }

        button_cadastrar.setOnClickListener {
            var userModel = UserModel()
            userModel.name = editText_nomeCad.text.toString()
            userModel.email = editText_emailCad.text.toString()
            userModel.password = editText_senhaCad.text.toString()
            userModel.tokenDevice = App.tokenFirebase
            userModel.admin = switchAdmin.isChecked


            presenter.createUser(userModel)
        }


    }

    private fun initComponents() {
        this.imageUser = imageViewCreateUser
        switchAdmin = switch_admin

        progressBar = ProgressBarCadastro

        presenter = CadastroPresenter(this)
    }

    private fun permissions() {

        managePermissions =
            ManagePermissions(this, getListPermissions(), REQUEST_SELECT_IMAGE_IN_ALBUM)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            managePermissions.checkPermissions()
    }


    fun selectImageInAlbum() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(
            Intent.createChooser(intent, "Selecione uma imagem"),
            REQUEST_SELECT_IMAGE_IN_ALBUM
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_SELECT_IMAGE_IN_ALBUM && resultCode == Activity.RESULT_OK && null != data) {

            getRealPathImageFromURI(data)

            createRequestImage()

            imageUser.rotation = getCameraPhotoOrientation(
                applicationContext,
                data.data!!, picturePath
            ).toFloat()

        } else {
            Toast.makeText(this, "Ocorreu um erro ao pegar a imagem", Toast.LENGTH_LONG).show()
        }


        imageUser.setImageBitmap(BitmapFactory.decodeFile(picturePath))

    }

    private fun getRealPathImageFromURI(data: Intent) {
        val selectedImage = data.data
        val filePathColumn =
            arrayOf(MediaStore.Images.Media.DATA)

        val cursor =
            selectedImage?.let { contentResolver.query(it, filePathColumn, null, null, null) }

        cursor!!.moveToFirst()

        val columnIndex = cursor.getColumnIndex(filePathColumn[0])
        picturePath = cursor.getString(columnIndex)

        cursor.close()
    }

    private fun createRequestImage() {
        val file = File(picturePath)

        val requestFile = RequestBody.create(
            MediaType.parse("image/${getMimeType(picturePath)}"), file
        )
        multipartBody = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
    }

    override fun showProgressBar(key: Boolean) {
        progressBar.visibility = if (key) View.VISIBLE else View.INVISIBLE
    }

    override fun setImage(toke: String) {
        presenter.uploadImage(multipartBody, toke)
    }


    override fun showAlert(key: Boolean, text: String) {
        DialogExamples.showDialogConfirm(text, key, this)

    }

    fun setGlide() {
        val url = "${App.ip}3333/uploads/7"
        val glideUrl = GlideUrl(url) { mapOf(Pair("Authorization", "${App.userToken}")) }

        Glide.with(applicationContext)
            .load(glideUrl)
            .into(imageUser)

//        var picasso =
//            Picasso.Builder(applicationContext).downloader(OkHttp3Downloader(picassoAuth())).build()
//        picasso.load("${App.ip}3333/uploads/1")
//            .into(imageUser, object : Callback {
//                override fun onSuccess() {
//                    println()
//                }
//
//                override fun onError() {
//                    println()
//                }
//
//            })
    }

    fun getMimeType(url: String): String? {
        var type: String? = null
        val extension = MimeTypeMap.getFileExtensionFromUrl(url)
        if (extension != null) {
            type =
                MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
                    ?.replace("image/", "")
        }
        return type
    }

    fun getCameraPhotoOrientation(
        context: Context, imageUri: Uri,
        imagePath: String?
    ): Int {
        var rotate = 0
        try {
            context.contentResolver.notifyChange(imageUri, null)
            val imageFile = File(imagePath)
            val exif = ExifInterface(imageFile.absolutePath)
            val orientation: Int = exif.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL
            )
            when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_270 -> rotate = 270
                ExifInterface.ORIENTATION_ROTATE_180 -> rotate = 180
                ExifInterface.ORIENTATION_ROTATE_90 -> rotate = 90
            }
            Log.i("RotateImage", "Exif orientation: $orientation")
            Log.i("RotateImage", "Rotate value: $rotate")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return rotate
    }


    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                val isPermissionsGranted = managePermissions
                    .processPermissionsResult(requestCode, permissions, grantResults)

                if (isPermissionsGranted) {
                    // Do the task now
                    Toast.makeText(this, "pemissões aceitas", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "pemissões negadas", Toast.LENGTH_LONG).show()
                }
                return
            }
        }
    }


}
