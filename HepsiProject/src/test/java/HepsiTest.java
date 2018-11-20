import enums.CampaignStatus;
import model.CampProdOrder;
import model.Campaign;
import model.Product;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.OrderUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HepsiTest {
    private static final Logger logger = Logger.getLogger(HepsiTest.class);

    @Test
    public void createCampaign() {
        try {
            Product p = new Product("P1", 100, 1000);
            Campaign c = new Campaign("C1", "P1", 10, 20, 100);
            CampProdOrder.campaign = c;
            CampProdOrder.product = p;

            assertEquals(c.getStatus(), CampaignStatus.ACTIVE);

        }catch (Exception e){
            logger.error(e.getStackTrace());
        }
    }

    @Test
    public void createOrder(){
        String[] args = {"","P1","30"};
        OrderUtil.createOrder(args);
        int salesCount = CampProdOrder.campaign.getRealSalesCount();
        assertEquals(30, salesCount);
    }

    @Test
    public void createOrder2(){
        String[] args = {"P1","40"};
        Product p = new Product("P1", 100, 1000);
        CampProdOrder.product = p;
        OrderUtil.createOrder(args);
        int salesCount = CampProdOrder.campaign.getRealSalesCount();
        assertEquals(70, salesCount);
    }
}
