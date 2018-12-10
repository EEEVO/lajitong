package com.auts.lajitong.model.response.litteredhat;

import com.auts.lajitong.model.dao.UserModel;

public class LitteredHatInfosResponseModel {

    public LitteredHatInfosResponseModel(){}

    public LitteredHatInfosResponseModel(UserModel userModel){
        this.phonenumber = userModel.getAccountId();
        this.nickname = userModel.getNickName();
        this.current_profit = userModel.getCurrentProfit();
        this.deliver_count = userModel.getDeliverCount();
        this.total_profit = userModel.getTotalProfit();
    }

    private String phonenumber;

    private String nickname;

    private String current_profit;

    private int deliver_count;

    private String total_profit;

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCurrent_profit() {
        return current_profit;
    }

    public void setCurrent_profit(String current_profit) {
        this.current_profit = current_profit;
    }

    public int getDeliver_count() {
        return deliver_count;
    }

    public void setDeliver_count(int deliver_count) {
        this.deliver_count = deliver_count;
    }

    public String getTotal_profit() {
        return total_profit;
    }

    public void setTotal_profit(String total_profit) {
        this.total_profit = total_profit;
    }
}