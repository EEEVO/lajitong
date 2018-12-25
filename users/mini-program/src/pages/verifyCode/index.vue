<template>
    <div class="tk-verifyCode">
        <h2>请输入验证码</h2>
        <p>验证码已通过短信发送到：</p>
        <p>{{mobileDesensitization}}</p>
        <tk-password
            class="code"
            :isFocus="autoFocus"
            @submit="submit"
        ></tk-password>

        <div class="getCode-wrapper">
            <i-button
                v-if="!isLoading"
                class="getCode-btn"
                type="getCode"
                @click="handleGetCode"
            >{{getCodeText}}
            </i-button>
            <div class="countdownText" v-else="isLoading">{{time}}s后可重新获取</div>
        </div>
    </div>
</template>

<script>
    import API from "api/apiList";

    const COUNTDOWN_TIME = 60;
    export default {
        data() {
            return {
                mobile: "",
                getCodeText: "重新获取",
                isLoading: true,
                time: 0,
                timer: null,
                currentInput: 0,
                autoFocus: true
            };
        },
        mounted() {
            // 获取手机号
            this.mobile = this.$root.$mp.query.mobile
            this.setCountDown();
        },
        methods: {
            // 倒计时
            setCountDown() {
                this.time = COUNTDOWN_TIME;
                this.isLoading = true;
                this.timer = setInterval(() => {
                    this.time--;
                    if (this.time === 0) {
                        clearInterval(this.timer);
                        this.isLoading = false;
                    }
                }, 1000);
            },
            // 发送获取验证码
            async handleGetCode() {
                this.autoFocus = true;
                let res = await this.$get(API.getCode, {
                    accountId: this.mobile
                });
                if (res.status === 200) {
                    this.setCountDown();
                }
            },
            inputCode(e) {
                if(e.target.detail.value) {
                    this.currentInput++
                }
            },
            async submit(e) {
                console.log(e.mp.detail);
                if(e.mp.detail.length === 6) {
                    let res = await this.$post(API.login, {
                        accountId: this.mobile,
                        smscode: e.mp.detail
                    })
                    if(res.status === 200) {
                        console.log(123);
                        wx.setStorageSync('isLogin', true);
                        wx.setStorageSync('userId', res.result.id);
                        clearInterval(this.timer)
                        this.$router.go(2)
                    }
                }
            }
        },
        computed: {
            mobileDesensitization() {
                return `${this.mobile.slice(0,3)}****${this.mobile.slice(7)}`
            }
        }
    };
</script>

<style scoped lang="less">
    .tk-verifyCode {
        border-top: 1rpx solid #d7d7d7;
        padding: 40px 15px;
        h2 {
            font-size: 18px;
            color: #333;
            margin-bottom: 20px;
        }
        p {
            font-size: 14px;
            color: #999;
        }
        .pwd {
            position: relative;
        }
        .pwd-wrapper {
            display: flex;
            justify-content: space-between;
            margin-top: 40px;
            li {
                height: 44px;
                &:last-child {
                    margin-right: 0;
                }
            }
        }
        .getCode-wrapper {
            position: relative;
            top: -10px;
            .getCode-btn,
            .countdownText {
                position: absolute;
                right: 0;
                top: 12px;
            }
            .countdownText {
                font-size: 14px;
                color: #999;
            }
        }


    }
</style>
<style lang="less">
    .tk-verifyCode {
        .tk-pwd-input {
            width: 44px;
            text-align: center !important;
            margin: 0 !important;
            height: 44px;
            input {
                height: 44px;
            }
            .getCode-btn {
                color: red !important;
            }
        }
        .hidden-input {
            height: 44px;
            position: absolute!important;
            left: 0;
            top: 0;
            right: 0;
            background: red!important;
            opacity: 0;
            &::after {
                border-bottom-width: 0!important;
            }
        }

    }
    code {
        position: relative;
        top: 40px
    }
</style>
