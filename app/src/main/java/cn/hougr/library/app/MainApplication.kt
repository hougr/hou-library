package cn.hougr.library.app

import android.app.Application
import cn.hougr.library.log.HouLogConfig
import cn.hougr.library.log.HouLogManager
import cn.hougr.library.log.printer.HouConsolePrinter
import cn.hougr.library.log.printer.HouFilePrinter
import com.google.gson.Gson

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        HouLogManager.init(
            object : HouLogConfig() {
                override fun injectJsonParser(): JsonParser? {
                    return JsonParser { src -> Gson().toJson(src) }
                }

                override fun getGlobalTag(): String {
                    return "MApplication"
                }

                override fun enable(): Boolean {
                    return true
                }

                override fun includeThread(): Boolean {
                    return true
                }

                override fun stackTraceDepth(): Int {
                    return 5
                }
            },
            HouConsolePrinter(),
            HouFilePrinter.getInstance(applicationContext.cacheDir.absolutePath, 0)
        )
    }
}