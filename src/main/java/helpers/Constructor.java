package helpers;

import java.util.ArrayList;

public class Constructor {
    static ArrayList<String> productName = new ArrayList<>();
    static ArrayList<String> productId = new ArrayList<>();

    public static ArrayList<String> getProductId() {
        return productId;
    }

    public static void setProductId(String productId) {
        Constructor.productId.add(productId);
    }

    public static ArrayList<String> getProductName() {
        return productName;
    }

    public static String getLastProductName() {
        return getProductName().get(getProductName().size() - 1);
    }

    public static void setProductName(String productName) {
        Constructor.productName.add(productName);
    }

    public static void cleanProductName() {
        Constructor.productName.clear();
    }

    public static void cleanProductId() {
        Constructor.productId.clear();
    }

}
