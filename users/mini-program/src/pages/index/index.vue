<template>
    <div class="page-index">
        <swiper class="swiper-content">
            <swiper-item class="swiper-slide">
                <div class="swiper-slide-img-wrapper">
                    <image mode="aspectFit" class="slide-image" src="https://dummyimage.com/700x300/f5d639/fff"></image>
                </div>
            </swiper-item>
            <swiper-item class="swiper-slide">
                <div class="swiper-slide-img-wrapper">
                    <image mode="aspectFit" class="slide-image" src="https://dummyimage.com/700x300/f5d639/fff"></image>
                </div>
            </swiper-item>
            <swiper-item class="swiper-slide">
                <div class="swiper-slide-img-wrapper">
                    <image mode="aspectFit" class="slide-image" src="https://dummyimage.com/700x300/f5d639/fff"></image>
                </div>
            </swiper-item>
        </swiper>
        <div class="nav-list">
            <div @click="navItem(index)" class="nav-item" v-for="(item, index) in navList" :key="index">
                <img :src="item.icon" alt="">
                <h4>{{item.title}}</h4>
            </div>
        </div>
        <div class="card-list">
            <div class="card-item card-green-gold">
                <div class="title">
                    <h5>我的环保金</h5>
                    <p>{{greenGold}} <span>元</span></p>
                </div>
                <div @click="goWallet" class="link">我的钱包<div class="icon-wrapper"><i-icon color="#666" type="enter" /></div></div>
            </div>
            <div class="card-item card-count">
                <div class="title">
                    <h5>累计投递</h5>
                    <p>{{count}} <span>次</span></p>
                </div>
                <div @click="goDelivery" class="link">查看记录<div class="icon-wrapper"><i-icon color="#666" type="enter" /></div></div>
            </div>
        </div>
        <div @click="code" class="code"><div class="icon-wrapper"><i-icon size="20" color="#000" type="scan" /></div><div class="txt">生成二维码投递</div></div>
    </div>
</template>

<script>
    import { isForbidden } from "utils/stopRequest";

    export default {
        data() {
            return {
                navList: [{
                    title: '附近回收机',
                    icon: require('../../assets/imgs/icon-local.png')
                },{
                    title: '帮助中心',
                    icon: require('../../assets/imgs/icon-help.png')
                },{
                    title: '个人中心',
                    icon: require('../../assets/imgs/icon-user.png')
                }],
                greenGold: '0.00',
                count: 0
            }
        },
        methods: {
            code() {
                if(this.checkLogin()) return
                this.$toast('投递')
            },
            checkLogin() {
                if(wx.getStorageSync('isLogin')) return false
                this.$router.push({
                    path: "/pages/login/main"
                });
                return true
            },
            navItem(index) {
                switch (index) {
                    case 0:
                        this.$toast('敬请期待');
                        break;
                    case 1:
                        this.$router.push({
                            path: "/pages/helpCenter/main"
                        });
                        break;
                    case 2:
                        this.$router.push({
                            path: "/pages/user/main"
                        });
                        break;
                }
            },
            goWallet() {
                if(this.checkLogin()) return
                this.$router.push({
                    path: "/pages/wallet/main"
                });
            },
            goDelivery() {
                if(this.checkLogin()) return
                this.$router.push({
                    path: "/pages/delivery/main"
                });
            }
        }
    };
</script>

<style scoped lang="less">
    @import "../../styles/mixin";

    .page-index {
        padding-top: 10px;
        .swiper-content {
            height: 150px;
            .swiper-slide {
                .swiper-slide-img-wrapper {
                    width: 93%;
                    height: 150px;
                    border-radius: 6px;
                    overflow: hidden;
                    margin: 0 auto;
                }
                .slide-image {
                    width: 100%;
                    height: 150px;
                }
            }

        }
        .nav-list {
            display: flex;
            padding: 20px 0;
            .nav-item {
                flex: 1;
                text-align: center;
                h4 {
                    font-size: @fs14;
                    text-align: center;
                    color: @fontColor3;
                }
                img {
                    width: 32px;
                    height: 32px;
                    display: block;
                    margin: 0 auto 10px;
                }
            }
        }
        .icon-wrapper {
            position: relative;
            top: -1px;
        }
        .card-list {
            .card-item {
                box-shadow: 0 0 4px rgba(0,0,0,.1);
                width: 93%;
                margin: 20px auto 0;
                border-radius: 6px;
                padding: 15px;
                display: flex;
                position: relative;
                .title {
                    h5 {
                        font-size: @fs14;
                        color: @fontColor6;
                    }
                    p {
                        font-size: @fs30;
                        color: #000;
                        margin-top: 10px;
                        font-weight: bold;
                        span {
                            font-size: @fs16;
                        }
                    }
                }
                .link {
                    position: absolute;
                    top: 50%;
                    right: 15px;
                    transform: translate(0, -50%);
                    font-size: @fs14;
                    color: @fontColor9;
                    border: 1rpx solid @fontColor9;
                    padding: 4px 10px;
                    border-radius: 20px;
                    display: flex;
                    align-items: center;
                    &:active {
                        opacity: .8;
                    }
                }
            }
        }
        .code {
            padding: 10px 20px;
            background: rgba(245, 215, 57, 0.8);
            background: linear-gradient(rgba(245, 215, 57, 0.8),rgba(245, 215, 57, 1));
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 30px auto 0;
            width: 220px;
            border-radius: 30px;
            box-shadow: 0 0 4px rgba(0,0,0,.1);
            &:active {
                opacity: .8;
            }
            .txt {
                font-size: @fs16;
                margin-left: 6px;
            }
        }
    }
</style>
