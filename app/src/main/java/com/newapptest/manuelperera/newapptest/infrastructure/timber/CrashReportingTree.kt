package com.newapptest.manuelperera.newapptest.infrastructure.timber

import timber.log.Timber

class CrashReportingTree : Timber.Tree() {

    // TODO: Add logs for Instabug
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        when (priority) {
//            Log.INFO -> InstabugLog.i(message)
//            Log.WARN -> InstabugLog.w(message)
//            Log.ERROR -> InstabugLog.e(message)
//            Log.ASSERT -> InstabugLog.wtf(message)
//            else -> InstabugLog.v(message)
        }
    }

}