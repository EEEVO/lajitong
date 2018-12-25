<template>
    <div class="page-walletDetail">
        <div class="walletDetail-list" v-if="walletDetailList.length">
            <div :key="index" class="walletDetail-item"
                 v-for="(item, index) in walletDetailList"
            >
                <div class="walletDetail_item_info title">
                    <!--<div class="type">{{item.type == 0 ? "微信提现": item.type == 1? "支付宝提现": "投递收入"}}</div>-->
                    <div class="type">{{item.msg}}</div>
                    <div class="create-time">{{item.operate_time}}</div>
                </div>
                <!--<div class="walletDetail_item_info amount">{{item.type == 0 || item.type == 1 ? "- ":-->
                    <!--"+ "}}{{item.amount}}-->
                <!--</div>-->
                <div class="walletDetail_item_info amount">{{item.amount}}
                </div>
            </div>
        </div>
        <div v-else class="empty">
            暂无余额明细记录
        </div>
    </div>
</template>

<script>
    import { isForbidden } from "utils/stopRequest";
    import API from "api/apiList";

    export default {
        data() {
            return {
                walletDetailList: []
            };
        },
        onLoad() {
            this.getWalletDetailList();
        },
        methods: {
            async getWalletDetailList() {
                let userId = wx.getStorageSync("userId");
                const res = await this.$get(API.walletDetailList, { userId });
                console.log(res);
                if (res.status !== 200) return;
                this.walletDetailList = [...res.result.data];
            }
        },
        computed: {
            type(type) {
                switch (type) {
                    case 0:
                        return "微信提现";
                    case 1:
                        return "支付宝提现";
                    case 3:
                        return "投递收入";
                }
            }
        }
    };
</script>

<style scoped lang="less">
    .page-walletDetail {
        width: 100vw;
        min-height: 100vh;
        background: #f2f2f2;
        .walletDetail-list {
            padding: 15px;
            .walletDetail-item {
                padding: 10px 0;
                background: #fff;
                border-radius: 4px;
                margin-bottom: 10px;
                position: relative;
                .walletDetail_item_info {
                    padding: 0 10px;
                    font-size: 15px;
                }
                .create-time {
                    color: #bbb;
                    margin-top: 2px;
                    font-size: 13px;
                }
                .type {

                }
                .amount {
                    position: absolute;
                    right: 15px;
                    top: 50%;
                    transform: translate(0, -50%);
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
