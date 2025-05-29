package ru.nvgsoft.worklist.presentation

import android.app.Application
import ru.nvgsoft.worklist.di.DaggerApplicationComponent

class WorkListApp: Application() {

val component by lazy {
    DaggerApplicationComponent.factory().create(this)
}
}