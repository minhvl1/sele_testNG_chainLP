package helpers;


import org.apache.commons.io.FileUtils;
import utils.LogUtils;

import java.io.File;

public class FileHelpers {
    public static void cleanFiles(String path) {
        try {
            FileUtils.deleteDirectory(new File(path));

            LogUtils.info("================ DELETE " + path + " ================");
        } catch (Exception e) {
            LogUtils.info(e.getMessage());
        }

    }
}
