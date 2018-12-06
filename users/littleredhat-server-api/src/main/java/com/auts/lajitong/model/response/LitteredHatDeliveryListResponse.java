package com.auts.lajitong.model.response;

import java.util.List;

public class LitteredHatDeliveryListResponse {

    private List<DeliverModel> data;

    public List<DeliverModel> getData() {
        return data;
    }

    public void setData(List<DeliverModel> data) {
        this.data = data;
    }

    public static class DeliverModel{
        private String order_id;

        private String device_id;

        private String bin_no;

        private String order_type;

        private String delivery_time;

        private String weight;

        private String price;

        private String amount;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getDevice_id() {
            return device_id;
        }

        public void setDevice_id(String device_id) {
            this.device_id = device_id;
        }

        public String getBin_no() {
            return bin_no;
        }

        public void setBin_no(String bin_no) {
            this.bin_no = bin_no;
        }

        public String getOrder_type() {
            return order_type;
        }

        public void setOrder_type(String order_type) {
            this.order_type = order_type;
        }

        public String getDelivery_time() {
            return delivery_time;
        }

        public void setDelivery_time(String delivery_time) {
            this.delivery_time = delivery_time;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }
}
