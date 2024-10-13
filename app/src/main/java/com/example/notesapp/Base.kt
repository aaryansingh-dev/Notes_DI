package com.example.notesapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


// It is import to have a base class for dependency injection and thus we make this
// this class extends Application class, and this gives context to the Module function
// also have to define this in Manifest as android:name=".Base"

@HiltAndroidApp
class Base: Application() {

}