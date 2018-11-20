package utils;

import enums.CampaignStatus;
import model.CampProdOrder;
import model.Campaign;
import model.Order;
import org.apache.log4j.Logger;

public class OrderUtil {
    private static final Logger logger = Logger.getLogger(OrderUtil.class);

    public static void createOrder(String[] args){
        try {
            String productCode = args[1];
            int quantity = Integer.valueOf(args[2]);
            Campaign campaign = CampProdOrder.campaign;

            int targetSalesCount = campaign.getTargetSalesCount();
            int realSalesCount = campaign.getRealSalesCount();

            if (targetSalesCount == realSalesCount) {
                CampProdOrder.campaign.setStatus(CampaignStatus.PASSIVE);
            }

            if (CampProdOrder.campaign.getStatus().equals(CampaignStatus.ACTIVE)) {

                double price = CampProdOrder.product.getPrice();
                Order order = new Order(productCode, quantity, price);

                if (quantity <= (targetSalesCount - realSalesCount)) {
                    CampProdOrder.campaign.setRealSalesCount(realSalesCount + quantity);

                    CampProdOrder.orderList.add(order);

                    // update stock
                    ProductUtil.updateStockByOrder(quantity);

                }
            } else {
                double price = CampProdOrder.product.getFirstPrice();
                int stock = CampProdOrder.product.getStock();
                //campaign is over set product price to first price
                CampProdOrder.product.setPrice(price);

                if (quantity + realSalesCount <= stock) {
                    Order order = new Order(productCode, quantity, price);
                    CampProdOrder.orderList.add(order);
                    // decrease stock
                    ProductUtil.updateStockByOrder(quantity);
                }
            }
        }catch (Exception e){
            logger.error(e);
        }
    }
}
