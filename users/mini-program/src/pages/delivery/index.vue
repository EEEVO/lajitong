<template>
    <div class="page-delivery">
        <div class="delivery-list" v-if="deliveryList.length">
            <div :key="index" class="delivery-item"
                 v-for="(item, index) in deliveryList">
                <div class="delivery_item_info delivery-order_id">订单编号：{{item.order_id}}</div>
                <!--<div class="delivery_item_info delivery-device_id">设备编号：{{item.device_id}}</div>-->
                <!--<div class="delivery_item_info delivery-bin_no">内桶编号：{{item.bin_no}}</div>-->
                <div class="delivery_item_info delivery-order_type">投递类型：{{item.order_type}}</div>
                <div class="delivery_item_info delivery-delivery_time">投递时间：{{item.delivery_time}}</div>
                <div class="delivery_item_info delivery-weight">重量（g）：{{item.weight}}</div>
                <div class="delivery_item_info delivery-price">价格（kg）：{{item.price}}</div>
                <div class="delivery_item_info delivery-amount">收益：{{item.amount}}</div>
            </div>
        </div>
        <div v-else class="empty">
            暂无投递记录
        </div>
    </div>
</template>

<script>
    import { isForbidden } from "utils/stopRequest";
    import API from "api/apiList";

    export default {
        data() {
            return {
                deliveryList: []
            };
        },
        onLoad() {
            this.getDeliveryList();
        },
        methods: {
            async getDeliveryList() {
                let userId = wx.getStorageSync("userId");
                const res = await this.$get(API.deliveryList, { userId });
                console.log(res);
                if (res.status !== 200) return;
                this.deliveryList = [...res.result.data];
            }
        }
    };
</script>

<style scoped lang="less">
    .page-delivery {
        width: 100vw;
        min-height: 100vh;
        background: #f2f2f2;
        .delivery-list {
            padding: 15px;
            .delivery-item {
                padding: 10px 0;
                background: #fff;
                border-radius: 4px;
                margin-bottom: 10px;
                /*box-shadow: 0 0 2px 1px rgba(0,0,0,.1);*/
                .delivery_item_info {
                    padding: 0 10px;
                    font-size: 14px;
                }
            }
        }
        .empty {
            width: 100vw;
            height: 100vh;
            display: flex;
            justify-content: center;
            padding-top: 200px;
            font-size: 16px;
        }
    }
</style>
