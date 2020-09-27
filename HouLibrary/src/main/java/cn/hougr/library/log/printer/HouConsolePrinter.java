package cn.hougr.library.log.printer;

import android.util.Log;

import androidx.annotation.NonNull;
import cn.hougr.library.log.HouLogConfig;
import cn.hougr.library.log.IHouLogPrinter;

import static cn.hougr.library.log.HouLogConfig.MAX_LEN;


public class HouConsolePrinter implements IHouLogPrinter {

    @Override
    public void print(@NonNull HouLogConfig config, int level, String tag, @NonNull String printString) {
        int len = printString.length();
        int countOfSub = len / MAX_LEN;//IMPT:考虑换行情况，平时在终端看到的都是换行后的。区分一行和多行。
        if (countOfSub > 0) {
            StringBuilder log = new StringBuilder();
            int index = 0;
            for (int i = 0; i < countOfSub; i++) {
                log.append(printString.substring(index, index + MAX_LEN));
                index += MAX_LEN;
            }
            if (index != len) {
                log.append(printString.substring(index, len));
            }
            Log.println(level, tag, log.toString());
        } else {
            Log.println(level, tag, printString);
        }
    }
}
