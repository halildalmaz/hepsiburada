package model;

import enums.CampaignStatus;
import org.apache.log4j.Logger;

public class Campaign {
    private static final Logger logger = Logger.getLogger(Campaign.class);
    private String name;
    private String productCode;
    private int duration;
    private int pmLimit;
    private int targetSalesCount;
    private int realSalesCount;
    private CampaignStatus status;
    private int time;

    public Campaign() {
    }

    public Campaign(String name, String productCode, int duration, int pmLimit, int targetSalesCount) {
        this.name = name;
        this.productCode = productCode;
        this.duration = duration;
        this.pmLimit = pmLimit;
        this.targetSalesCount = targetSalesCount;
        this.status = CampaignStatus.ACTIVE;

        logger.info("Campaign created; name " + this.name + ", product " + this.productCode +
                ", duration " + this.duration + ", limit " + this.pmLimit +
                ", target sales count " + this.targetSalesCount);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPmLimit() {
        return pmLimit;
    }

    public void setPmLimit(int pmLimit) {
        this.pmLimit = pmLimit;
    }

    public int getTargetSalesCount() {
        return targetSalesCount;
    }

    public void setTargetSalesCount(int targetSalesCount) {
        this.targetSalesCount = targetSalesCount;
    }

    public CampaignStatus getStatus() {
        return status;
    }

    public void setStatus(CampaignStatus status) {
        this.status = status;
    }

    public int getRealSalesCount() {
        return realSalesCount;
    }

    public void setRealSalesCount(int realSalesCount) {
        this.realSalesCount = realSalesCount;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
