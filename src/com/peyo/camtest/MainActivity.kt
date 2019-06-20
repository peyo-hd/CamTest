package com.peyo.camtest

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.hardware.Camera
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceView

class MainActivity : Activity() , SurfaceHolder.Callback{
    lateinit var mCamera: Camera
    lateinit var mHolder: SurfaceHolder

    override fun onCreate(savedInstanceState: Bundle?) {
	    super.onCreate(savedInstanceState)

        val view = SurfaceView(this)
        setContentView(view)

        mHolder = view.holder
        mHolder.addCallback(this)

        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            requestPermissions(arrayOf(Manifest.permission.CAMERA), 0)
        mCamera = Camera.open()
    }

    override fun surfaceCreated(p0: SurfaceHolder?) {
        mCamera.apply {
            setPreviewDisplay(mHolder)
            startPreview()
        }
    }

    override fun onPause() {
        super.onPause()
        finish()
    }

    override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) {
    }

    override fun surfaceDestroyed(p0: SurfaceHolder?) {
    }
}
