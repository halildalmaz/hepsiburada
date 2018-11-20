package utils;

import model.CampProdOrder;
import model.Campaign;
import model.Product;
import org.apache.log4j.Logger;

public class ProductUtil {
    private static final Logger logger = Logger.getLogger(ProductUtil.class);

    public static void createProduct(String[] args){
        String productCode = args[1];
        int price = Integer.valueOf(args[2]);
        int stock = Integer.valueOf(args[3]);

        CampProdOrder.product = new Product(productCode, price, stock);;
    }

    public static void getProductInfo(String[] args){
        String productCode = args[1];
        double price = CampProdOrder.product.getPrice();
        int stock = CampProdOrder.product.getStock();

        logger.info("Product "+ productCode +" info; price " + price + ", stock " + stock);
    }

    public static void updateStockByOrder(int orderCount){
        int stock = CampProdOrder.product.getStock();

        if(stock > 0 && orderCount <= stock) {
            CampProdOrder.product.setStock(stock-orderCount);
        }else {
            logger.info("Stock is not enough.");
        }
    }
}
