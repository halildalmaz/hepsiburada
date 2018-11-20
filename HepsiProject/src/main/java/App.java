import constants.CampaignConstants;
import constants.OrderConstants;
import constants.ProductConstants;
import model.Campaign;
import model.Order;
import model.Product;
import org.apache.log4j.Logger;
import utils.CampaignUtil;
import utils.OrderUtil;
import utils.ProductUtil;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;

public class App {

    private static final Logger logger = Logger.getLogger(App.class);

    public static void main (String[] args) {

        Scanner scanner = null;

        for (File file : getFilesFromFolder("scenarios")) {
            logger.info(file);

            try {
                scanner = new Scanner(file);

                while(scanner.hasNextLine()){
                    String line = scanner.nextLine();
                    //logger.info(line);

                    parseAndSendCommand(line);

                }
            } catch (Exception e) {
                logger.error(e);
            }

        }
    }


    private static File[] getFilesFromFolder (String folder) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource(folder);
        String path = url.getPath();
        return new File(path).listFiles();
    }

    private static void parseAndSendCommand(String line){
        String[] splittedLine = line.split(" ");
        if(splittedLine[0].equals("")){
            return;
        }

        switch (splittedLine[0]){
            case ProductConstants.CREATE_PRODUCT:
                ProductUtil.createProduct(splittedLine);
                break;
            case ProductConstants.GET_PRODUCT_INFO:
                ProductUtil.getProductInfo(splittedLine);
                break;
            case CampaignConstants.CREATE_CAMPAIGN:
                CampaignUtil.createCampaign(splittedLine);
                break;
            case CampaignConstants.GET_CAMPAIGN_INFO:
                CampaignUtil.getCampaignInfo(splittedLine);
                break;
            case CampaignConstants.INCREASE_TIME:
                CampaignUtil.increaseTime(splittedLine);
                break;
            case OrderConstants.CREATE_ORDER:
                OrderUtil.createOrder(splittedLine);
                break;
            default:
                logger.info("Command not defined!");
        }
    }

}
