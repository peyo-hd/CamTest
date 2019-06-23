package com.peyo.camtest

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.TextureView
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraX
import androidx.camera.core.Preview
import androidx.camera.core.PreviewConfig

class MainActivity : AppCompatActivity() {
    lateinit var viewFinder: TextureView

    override fun onCreate(savedInstanceState: Bundle?) {
	    super.onCreate(savedInstanceState)

        viewFinder = TextureView(this)
        setContentView(viewFinder)

        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            requestPermissions(arrayOf(Manifest.permission.CAMERA), 0)
        else
            viewFinder.post{ startCamera() }

    }

    private fun startCamera() {
        val config = PreviewConfig.Builder().build()
        val preview = Preview(config)

        preview.setOnPreviewOutputUpdateListener {
            val parent = viewFinder.parent as ViewGroup
            parent.removeView(viewFinder)
            parent.addView(viewFinder, 0)
            viewFinder.surfaceTexture = it.surfaceTexture
        }

        CameraX.bindToLifecycle(this, preview)
    }
}
