package com.auts.lajitong.model.response.litteredhat;

import java.util.List;

public class LitteredBalanceListResponseModel {

    private List<BalanceData> data;

    public List<BalanceData> getData() {
        return data;
    }

    public void setData(List<BalanceData> data) {
        this.data = data;
    }

    public static class BalanceData{
        private String amount;

        private int type;
        
        private String msg;

		private String operate_time;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getOperate_time() {
            return operate_time;
        }

        public void setOperate_time(String operate_time) {
            this.operate_time = operate_time;
        }
        public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}
    }
}
