<template>
    <div class="page-demo">
        <i-button
            class="btn-get-code"
            @click="logout"
            type="tika"
        >
            退出登录
        </i-button>
    </div>
</template>

<script>
    import { isForbidden } from "utils/stopRequest";

    export default {
        methods: {
            logout() {
                wx.clearStorageSync();
                this.$router.push({
                    path: '/pages/index/main',
                    reLaunch: true
                })
            }
        }
    };
</script>

<style scoped lang="less">

</style>
