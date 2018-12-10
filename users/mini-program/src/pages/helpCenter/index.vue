<template>
    <div class="page-helpCenter">
        <div class="helpCenter-list"  v-if="helpCenterList.length">
            <div class="helpCenter-item"
                 :key="index"
                 v-for="(item, index) in helpCenterList"
            >
                <div class="help-title" @click="showCurrentQuestion(index)">
                    {{index+1}}. {{item.title}}
                    <image class="icon-arow" :src="arrow" :class="showIndex === index ? 'down': ''"></image>
                </div>
                <div class="help-content" v-show="showIndex === index">
                    {{item.content}}
                </div>
            </div>
        </div>
        <div v-else class="empty">
            暂无问题
        </div>
    </div>
</template>

<script>
    import { isForbidden } from "utils/stopRequest";
    import API from "api/apiList";

    export default {
        data() {
            return {
                helpCenterList: [],
                showIndex: -1,
                arrow: require('../../assets/imgs/icon-arrow.png')

            };
        },
        onLoad() {
            this.getHelpCenterList();
        },
        methods: {
            async getHelpCenterList() {
                console.log(123123);
                const res = await this.$get(API.helpCenterList);
                console.log(res);
                if (res.status !== 200) return;
                this.helpCenterList = res.result;
            },
            showCurrentQuestion(i) {
                this.showIndex = i
            }
        }
    };
</script>

<style scoped lang="less">
    .page-helpCenter {
        width: 100vw;
        min-height: 100vh;
        background: #f2f2f2;
        .helpCenter-list {
            padding: 15px;
            background: #fff;
            .helpCenter-item {
                border-bottom: 1rpx solid #d7d7d7;
                font-size: 14px;
                padding: 10px 0;
                &:first-child {
                    padding-top: 0;
                }
                &:last-child {
                    border: none;
                    padding-bottom: 0;
                }
                .help-title {
                    line-height: 30px;
                    position: relative;
                    .icon-arow {
                        position: absolute;
                        right: 0;
                        top: 50%;
                        margin-top: -8px;
                        width: 16px;
                        height: 16px;
                        display: block;
                        z-index: 10;
                        transition: all .2s;
                        &.down {
                            transform: rotate(90deg);
                        }
                    }
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
