package cn.hougr.library.app.demo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import cn.hougr.library.app.R
import cn.hougr.library.log.HouLog
import cn.hougr.library.log.HouLogConfig
import cn.hougr.library.log.HouLogManager
import cn.hougr.library.log.HouLogType
import cn.hougr.library.log.printer.HouViewPrinter

class HouLogDemoActivity : AppCompatActivity() {
    var viewPrinter: HouViewPrinter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hi_log_demo)
        viewPrinter = HouViewPrinter(this)
        findViewById<View>(R.id.btn_log).setOnClickListener {
            printLog()
        }
        viewPrinter!!.viewProvider.showFloatingView()
    }

    private fun printLog() {
        HouLogManager.getInstance().addPrinter(viewPrinter)
        HouLog.log(object : HouLogConfig() {
            override fun includeThread(): Boolean {
                return false
            }
        }, HouLogType.E, "---", "5566")
        HouLog.a("9900")
    }
}
