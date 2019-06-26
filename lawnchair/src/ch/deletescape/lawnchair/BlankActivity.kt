/*
 *     This file is part of Lawnchair Launcher.
 *
 *     Lawnchair Launcher is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Lawnchair Launcher is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Lawnchair Launcher.  If not, see <https://www.gnu.org/licenses/>.
 */

package ch.deletescape.lawnchair

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver
import android.support.v4.app.ActivityCompat

class BlankActivity : Activity() {

    private val requestCode by lazy { intent.getIntExtra("requestCode", 0) }
    private val permissionRequestCode by lazy { intent.getIntExtra("permissionRequestCode", 0) }
    private val resultReceiver by lazy { intent.getParcelableExtra("callback") as ResultReceiver }
    private var resultSent = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.hasExtra("intent")) {
            startActivityForResult(intent.getParcelableExtra("intent"), requestCode)
        } else if (intent.hasExtra("permissions")) {
            ActivityCompat.requestPermissions(this, intent.getStringArrayExtra("permissions"), permissionRequestCode)
        } else {
            super.finish()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == permissionRequestCode) {
            resultReceiver.send(RESULT_OK, Bundle(2).apply {
                putStringArray("permissions", permissions)
                putIntArray("grantResults", grantResults)
            })
            resultSent = true
            finish()
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == this.requestCode) {
            resultReceiver.send(resultCode, data?.extras)
            resultSent = true
            finish()
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun finish() {
        if (!resultSent)
            resultReceiver.send(RESULT_CANCELED, null)
        super.finish()
    }

    companion object {

        fun startActivityForResult(context: Context, targetIntent: Intent, requestCode: Int,
                                   flags: Int, callback: (Int, Bundle?) -> Unit) {
            val intent = Intent(context, BlankActivity::class.java).apply {
                putExtra("intent", targetIntent)
                putExtra("requestCode", requestCode)
                putExtra("callback", object : ResultReceiver(Handler()) {

                    override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
                        callback(resultCode, resultData)
                    }
                })
            }
            start(context, intent)
        }

        inline fun requestPermission(context: Context, permission: String, requestCode: Int,
                                     crossinline callback: (Boolean) -> Unit) {
            requestPermissions(context, arrayOf(permission), requestCode) { _, _, grantResults ->
                callback(grantResults.all { it == PackageManager.PERMISSION_GRANTED })
            }
        }

        fun requestPermissions(context: Context, permissions: Array<String>, requestCode: Int,
                              callback: (Int, Array<String>, IntArray) -> Unit) {
            val intent = Intent(context, BlankActivity::class.java).apply {
                putExtra("permissions", permissions)
                putExtra("permissionRequestCode", requestCode)
                putExtra("callback", object : ResultReceiver(Handler()) {

                    override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
                        if (resultCode == RESULT_OK && resultData != null) {
                            callback(requestCode,
                                    resultData.getStringArray("permissions")!!,
                                    resultData.getIntArray("grantResults")!!)
                        } else {
                            callback(requestCode, permissions,
                                    IntArray(permissions.size) { PackageManager.PERMISSION_DENIED })
                        }
                    }
                })
            }
            start(context, intent)
        }

        private fun start(context: Context, intent: Intent) {
            val foreground = context.lawnchairApp.activityHandler.foregroundActivity ?: context
            if (foreground === context) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            foreground.startActivity(intent)
        }
    }
}
