<template>
    <div class="page-wallet">
        <div class="icon"></div>
        <div class="title">可提现余额</div>
        <div class="money">{{money}}<span>元</span></div>
        <div @click="goWalletDetail" class="btn-walletDetail">查看明细</div>
    </div>
</template>

<script>
    import { isForbidden } from "utils/stopRequest";

    export default {
        data() {
            return {
                money: '0.00'
            }
        },
        methods: {
            goWalletDetail() {
                this.$router.push({
                    path: '/pages/walletDetail/main'
                })
            }
        }
    };
</script>

<style scoped lang="less">
    @import "../../styles/mixin";

    .page-wallet {
        display: flex;
        flex-direction: column;
        align-items: center;
        padding-top: 90px;
        .icon {
            width: 90px;
            height: 90px;
            border-radius: 50%;
            background: @mainColor;
        }
        .title {
            font-size: @fs16;
            margin: 20px 0 4px;
        }
        .money {
            font-size: 40px;
            color: #000;
            font-weight: bold;
            span {
                font-size: @fs16;
                margin-left: 6px;
            }
        }
        .btn-walletDetail {
            width: 120px;
            height: 36px;
            border: 1rpx solid @mainColor;
            border-radius: 20px;
            text-align: center;
            line-height: 36px;
            color: @mainColor;
            margin-top: 30px;
            font-size: @fs16;
            overflow: hidden;
            &:active {
                background: @mainColor;
                color: #fff;
            }
        }
    }
</style>
