<template>
    <div class="tk-login">
        <i-input
            class="tk-input"
            i-class="tk-input-mobile"
            type="number"
            :value="mobile"
            maxlength="11"
            placeholder="请输入手机号"
            @change="handleChangeValue"
            autofocus="true"
        ></i-input>
        <i-button
            class="btn-get-code"
            @click="handleGetCode"
            type="tika"
            :disabled="btnIsDisabled"
            :loading="loading"
        >
            获取验证码
        </i-button>
    </div>
</template>

<script>
    import regList from "utils/regList";
    import API from "api/apiList";
    import { isForbidden, allow } from "utils/stopRequest";

    export default {
        data() {
            return {
                mobile: "",
                btnIsDisabled: true,
                loading: false
            };
        },

        onShow() {
            this.check()
        },

        methods: {
            // 获取验证码
            async handleGetCode() {
                if (this.btnIsDisabled) return;
                if (isForbidden()) return;
                this.loading = true;
                let res = await this.$get(API.getCode, {
                    accountId: this.mobile
                });
                this.loading = false;
                if(res.status !== 200) return
                this.$router.push({
                    path: "/pages/verifyCode/main",
                    query: {
                        mobile: this.mobile
                    }
                });
                Object.assign(this.$data, this.$options.data())

            },
            // 输入手机号码
            handleChangeValue(e) {
                this.mobile = e.target.detail.value;
                this.btnIsDisabled = !regList.tel.test(this.mobile);
            },
            // 失去焦点判断
            check() {
                console.log(this.mobile);
                this.btnIsDisabled = !regList.tel.test(this.mobile);
            }
        }
    };
</script>

<style scoped lang="less">
    @import "../../styles/mixin";

    .tk-login {
        height: 100vh;
        position: relative;
        border-top: 1rpx solid #d7d7d7;
        .tk-input {
            position: absolute;
            top: 80px;
            left: 3%;
            width: 94%;
            padding: 0 14px;
        }
        .btn-get-code {
            position: absolute;
            top: 140px;
            left: 3%;
            width: 94%;
        }
    }
</style>
