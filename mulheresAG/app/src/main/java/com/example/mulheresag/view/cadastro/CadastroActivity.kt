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
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
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
    private lateinit var btnBackPage: ImageView
    private lateinit var switchAdmin: Switch
    private var picturePath: String = ""
    private lateinit var btnAction: Button
    private lateinit var managePermissions: ManagePermissions
    private val REQUEST_SELECT_IMAGE_IN_ALBUM = 2121
    private val PERMISSION_REQUEST_CODE = 123
    private lateinit var titleCadastro: TextView
    private lateinit var multipartBody: MultipartBody.Part
    private var id = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        permissions()
        initComponents()
        try {
            id = intent.extras?.get("id") as Int
            titleCadastro.text = "Atualizar Cadastro"
            button_cadastrar.text = "Atualizar"
            presenter.getUser(id)
            setGlide(id)
        } catch (e: Exception) {
            id = -1
        }
        if (App.isAdmin) {
            switch_admin.visibility = View.VISIBLE
        }

        actionsButton()
    }

    private fun actionsButton() {
        imageUser.setOnClickListener {

            selectImageInAlbum()
        }

        btnAction.setOnClickListener {
            val userModel = UserModel()
            userModel.name = editText_nomeCad.text.toString()
            userModel.email = editText_emailCad.text.toString()
            userModel.password = editText_senhaCad.text.toString()
            userModel.phone = editText_phoneCad.text.toString()
            userModel.tokenDevice = App.tokenFirebase
            userModel.admin = switchAdmin.isChecked

            if (id < 0) {
                presenter.createUser(userModel)
            } else {
                userModel.id = id
                presenter.updateUser(userModel)
            }
        }


    }

    private fun initComponents() {
        btnAction = button_cadastrar
        this.imageUser = imageViewCreateUser
        titleCadastro = textView_titulo_cadastro
        switchAdmin = switch_admin
        btnBackPage = imageViewCadastroBack
        progressBar = ProgressBarCadastro
        presenter = CadastroPresenter(this)
        backPage()
        switchAdmin.visibility = View.INVISIBLE
        setVisibility()
    }

    private fun permissions() {

        managePermissions =
            ManagePermissions(this, getListPermissions(), REQUEST_SELECT_IMAGE_IN_ALBUM)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            managePermissions.checkPermissions()
    }

    private fun setSwitch(isAdmin: Boolean) {
        switchAdmin.isChecked = isAdmin
    }
    private fun setVisibility(){
        val preferences = App.setPreferences(this)
        val admin = preferences.getBoolean("admin",false)
        if(admin){
            switchAdmin.visibility = View.VISIBLE
        }else{
            switchAdmin.visibility = View.INVISIBLE
        }
    }

    fun backPage() {
        btnBackPage.setOnClickListener {
            onBackPressed()
        }
        return
    }

    private fun selectImageInAlbum() {
        val intent = Intent()
        intent.type = "image/jpeg"
        intent.action = Intent.ACTION_PICK
        startActivityForResult(
            Intent.createChooser(intent, "Selecione uma imagem"),
            REQUEST_SELECT_IMAGE_IN_ALBUM
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_SELECT_IMAGE_IN_ALBUM && resultCode == Activity.RESULT_OK && null != data) {

            try {
                getRealPathImageFromURI(data)
                var image = BitmapFactory.decodeStream(data.data?.let {
                    contentResolver.openInputStream(
                        it
                    )
                }, null, null)

                //  verifySizeImage(image)
                createRequestImage()

                imageUser.rotation = getCameraPhotoOrientation(
                    applicationContext,
                    data.data!!, picturePath
                ).toFloat()
                imageUser.setImageBitmap(BitmapFactory.decodeFile(picturePath))
            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }
        }
//        } else {
//            Toast.makeText(this, "Ocorreu um erro ao pegar a imagem", Toast.LENGTH_LONG).show()
//        }


    }

    private fun verifySizeImage(size: Long) {
        if (size > (7 * 1024 * 1024)) throw Exception("Tamanho maximo da imagem deve ser de 7mb")
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
        verifySizeImage(file.length())

        val requestFile = RequestBody.create(
            MediaType.parse("image/${getMimeType(picturePath)}"), file
        )
        multipartBody = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
    }

    override fun showProgressBar(key: Boolean) {
        progressBar.visibility = if (key) View.VISIBLE else View.INVISIBLE
        btnAction.visibility = if (!key) View.VISIBLE else View.INVISIBLE
        disableFields(!key)
    }

    override fun setImage(toke: String) {
        if (picturePath.isEmpty() || picturePath == null) {
            showAlert(true, "Atualizado")
        } else {
            presenter.uploadImage(multipartBody, toke)
        }
    }

    override fun setFields(user: UserModel) {

        editText_nomeCad.setText(user.name)
        editText_emailCad.setText(user.email)
        editText_senhaCad.setText(user.password)
        editText_phoneCad.setText(user.phone)
        setSwitch(user.admin)
    }

    private fun disableFields(key:Boolean){
        editText_nomeCad.isEnabled = key
        editText_emailCad.isEnabled = key
        editText_senhaCad.isEnabled = key
        editText_phoneCad.isEnabled = key

    }


    override fun showAlert(key: Boolean, text: String) {
        DialogExamples.showDialogConfirm(text, key, this)

    }

    fun setGlide(id: Int) {
        val url = "${App.ip}3333/uploads/$id"
        val glideUrl = GlideUrl(url) { mapOf(Pair("Authorization", App.userToken)) }

        Glide.with(applicationContext)
            .load(glideUrl)
            .fitCenter()
            .skipMemoryCache(true)
            .placeholder(R.drawable.useradd)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(imageUser)

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
