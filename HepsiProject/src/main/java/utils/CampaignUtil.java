package utils;

import enums.CampaignStatus;
import model.CampProdOrder;
import model.Campaign;
import model.Order;
import model.Product;
import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;


public class CampaignUtil {

    private static final Logger logger = Logger.getLogger(CampaignUtil.class);

    public static void createCampaign(String[] args){

        String name =  args[1];
        String productCode = args[2];
        int duration = Integer.valueOf(args[3]);
        int pmLimit = Integer.valueOf(args[4]);
        int targetSalesCount = Integer.valueOf(args[5]);

        if(productCode.equals(CampProdOrder.product.getProductCode())) {
            CampProdOrder.campaign = new Campaign(name, productCode, duration, pmLimit, targetSalesCount);
            CampProdOrder.orderList = new ArrayList<>();
        }else{
            logger.error("There is no Product with code " + productCode);
        }
    }

    public static void getCampaignInfo(String[] args){
        String name = args[1];

        String status = CampProdOrder.campaign.getStatus().name();
        int targetSalesCount = CampProdOrder.campaign.getTargetSalesCount();
        int realSalesCount = CampProdOrder.campaign.getRealSalesCount();

        double averagePrice = calculateAveragePrice();
        double turnover = calculateTurnover();
        String avgStr = averagePrice == 0 ? " -" : String.valueOf(averagePrice);

        logger.info("Campaign " + name +" info; Status " + status + ", Target Sales "+ targetSalesCount +
                " Total Sales "+ realSalesCount + ", Turnover "+ turnover +", Average Item Price " + avgStr);
    }

    private static double calculateAveragePrice(){
        int totalOrder = 0;
        double totalPrice = 0;
        List<Order> orderList = CampProdOrder.orderList;
        if(orderList.size() == 0){
            return 0;
        }
        for(Order o: orderList){
            totalOrder = totalOrder + o.getQuantity();
            totalPrice = totalPrice + o.getOrderPrice()*o.getQuantity();
        }

        return totalPrice/totalOrder;
    }

    private static double calculateTurnover(){
        double totalRevenue = 0;
        List<Order> orderList = CampProdOrder.orderList;
        for(Order o: orderList){
            totalRevenue = totalRevenue + o.getOrderPrice()*o.getQuantity();
        }

        return totalRevenue;
    }

    public static void increaseTime(String[] args){

        int hour = Integer.valueOf(args[1]);
        int minute = 0;
        int duration = CampProdOrder.campaign.getDuration();
        int time = CampProdOrder.campaign.getTime();
        int newTime = time + hour;
        if(newTime <= duration) {
            CampProdOrder.campaign.setTime(newTime);
            updatePriceByTime(hour);

            Calendar c1 = Calendar.getInstance();
            c1.set(Calendar.HOUR, newTime);
            c1.set(Calendar.MINUTE, minute);
            SimpleDateFormat df = new SimpleDateFormat("hh:mm");
            String timeStr = df.format(c1.getTime());

            logger.info("Time is " + timeStr);

        }else{
            CampProdOrder.campaign.setStatus(CampaignStatus.ENDED);
        }

    }
    private static void updatePriceByTime(int time){
        double firstPrice = CampProdOrder.product.getFirstPrice();
        int duration = CampProdOrder.campaign.getDuration();
        double price = CampProdOrder.product.getPrice();
        int limit = CampProdOrder.campaign.getPmLimit();
        if(CampProdOrder.campaign.getStatus().equals(CampaignStatus.ACTIVE)) {

            double change = ((duration / 10) * time) * 5;
            double diff = firstPrice - (price - change);
            if ((diff * 100 / firstPrice) <= (firstPrice * limit / 100)) {
                price = price - change;
            }
            CampProdOrder.product.setPrice(price);
        }else{
            CampProdOrder.product.setPrice(firstPrice);
        }
    }

}
