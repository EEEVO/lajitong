package com.auts.lajitong.model.response.litteredhat;

import java.util.List;

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
    
    private List<BankResponseModel> banks;

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
    
    public List<BankResponseModel> getBanks() {
		return banks;
	}

	public void setBanks(List<BankResponseModel> banks) {
		this.banks = banks;
	}

	public static class BankResponseModel{
    	
    	private String bankname;
    	
    	private String bankno;
    	
    	private String username;
    	
    	private String bankid;

		public String getBankname() {
			return bankname;
		}

		public void setBankname(String bankname) {
			this.bankname = bankname;
		}

		public String getBankno() {
			return bankno;
		}

		public void setBankno(String bankno) {
			this.bankno = bankno;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getBankid() {
			return bankid;
		}

		public void setBankid(String bankid) {
			this.bankid = bankid;
		}
    }
}
