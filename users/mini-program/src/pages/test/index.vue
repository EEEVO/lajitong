<template>
    <div class="page-index">
        <i-button
            size="small"
            type="primary"
            @click="http">http</i-button>
        <i-button
            size="small"
            type="primary"
            @click="jump">jump</i-button>
        <i-button
            size="small"
            type="primary"
            @click="toast">toast</i-button>
        <i-button
            size="small"
            type="primary"
            @click="showloading">showloading</i-button>
        <i-button
            size="small"
            type="primary"
            @click="hideloading">hideloading</i-button>
    </div>
</template>

<script>
    import { isForbidden } from "utils/stopRequest";
    import API from 'api/apiList'

    export default {
        async created() {

        },
        methods: {
            async http() {
                if (isForbidden()) return;
                const data = this.$get(API.baseInfo);
                console.log(data);
            },
            jump() {
                this.$router.push({
                    path: "/pages/demo/main"
                });
            },
            toast() {
                this.$toast('嗨~')
            },
            showloading() {
                this.$showloading()
                setTimeout(() => {
                    this.$hideloading()
                }, 2000)
            },
            hideloading() {
                this.$hideloading()
            }
        }
    };
</script>

<style scoped>

</style>
