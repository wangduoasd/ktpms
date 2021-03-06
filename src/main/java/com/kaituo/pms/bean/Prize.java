package com.kaituo.pms.bean;

public class Prize {
    private Integer prizeId;

    private String prizeName;

    private String prizeImage;

    private Integer prizeAmount;

    private Integer prizePrice;

    private Integer prizeQuota;

    private Integer prizeCondition;

    private Integer prizeStatus;

    private Integer prizeBuy;

    public Prize(Integer prizeId, String prizeName, String prizeImage, Integer prizeAmount, Integer prizePrice, Integer prizeQuota, Integer prizeCondition, Integer prizeStatus, Integer prizeBuy) {
        this.prizeId = prizeId;
        this.prizeName = prizeName;
        this.prizeImage = prizeImage;
        this.prizeAmount = prizeAmount;
        this.prizePrice = prizePrice;
        this.prizeQuota = prizeQuota;
        this.prizeCondition = prizeCondition;
        this.prizeStatus = prizeStatus;
        this.prizeBuy = prizeBuy;
    }

    public Prize() {
        super();
    }

    public Integer getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(Integer prizeId) {
        this.prizeId = prizeId;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName == null ? null : prizeName.trim();
    }

    public String getPrizeImage() {
        return prizeImage;
    }

    public void setPrizeImage(String prizeImage) {
        this.prizeImage = prizeImage == null ? null : prizeImage.trim();
    }

    public Integer getPrizeAmount() {
        return prizeAmount;
    }

    public void setPrizeAmount(Integer prizeAmount) {
        this.prizeAmount = prizeAmount;
    }

    public Integer getPrizePrice() {
        return prizePrice;
    }

    public void setPrizePrice(Integer prizePrice) {
        this.prizePrice = prizePrice;
    }

    public Integer getPrizeQuota() {
        return prizeQuota;
    }

    public void setPrizeQuota(Integer prizeQuota) {
        this.prizeQuota = prizeQuota;
    }

    public Integer getPrizeCondition() {
        return prizeCondition;
    }

    public void setPrizeCondition(Integer prizeCondition) {
        this.prizeCondition = prizeCondition;
    }

    public Integer getPrizeStatus() {
        return prizeStatus;
    }

    public void setPrizeStatus(Integer prizeStatus) {
        this.prizeStatus = prizeStatus;
    }

    public Integer getPrizeBuy() {
        return prizeBuy;
    }

    public void setPrizeBuy(Integer prizeBuy) {
        this.prizeBuy = prizeBuy;
    }

    @Override
    public String toString() {
        return "Prize{" +
                "prizeId=" + prizeId +
                ", prizeName='" + prizeName + '\'' +
                ", prizeImage='" + prizeImage + '\'' +
                ", prizeAmount=" + prizeAmount +
                ", prizePrice=" + prizePrice +
                ", prizeQuota=" + prizeQuota +
                ", prizeCondition=" + prizeCondition +
                ", prizeStatus=" + prizeStatus +
                ", prizeBuy=" + prizeBuy +
                '}';
    }
}