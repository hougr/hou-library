package cn.hougr.library.log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;

public class HouLogManager {
    private HouLogConfig config;
    private static HouLogManager instance;
    private List<IHouLogPrinter> printers = new ArrayList<>();

    private HouLogManager(HouLogConfig config, IHouLogPrinter[] printers) {
        this.config = config;
        this.printers.addAll(Arrays.asList(printers));
    }

    public static HouLogManager getInstance() {
        return instance;
    }

    //IMPT：这里最后可变参数很好，比传List更简洁
    public static void init(@NonNull HouLogConfig config, IHouLogPrinter... printers) {
        instance = new HouLogManager(config, printers);
    }

    public HouLogConfig getConfig() {
        return config;
    }

    public List<IHouLogPrinter> getPrinters() {
        return printers;
    }

    public void addPrinter(IHouLogPrinter printer) {
        printers.add(printer);
    }

    public void removePrinter(IHouLogPrinter printer) {
        if (printers != null) {
            printers.remove(printer);
        }
    }
}
