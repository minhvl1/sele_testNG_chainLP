package constants;

import helpers.PropertiesHelpers;

public class FrameworkConstants {
    private FrameworkConstants() {
    }

    static {
        PropertiesHelpers.loadAllFiles();
        //System.out.println("Data From FrameworkConstants: " + PropertiesHelpers.getProperties());
    }
    public static final String EXPORT_CAPTURE_PATH = PropertiesHelpers.getValue("EXPORT_CAPTURE_PATH");
    public static final String EXPORT_VIDEO_PATH = PropertiesHelpers.getValue("EXPORT_VIDEO_PATH");
    public static final String RETRY_TEST_FAIL = PropertiesHelpers.getValue("RETRY_TEST_FAIL");
}
