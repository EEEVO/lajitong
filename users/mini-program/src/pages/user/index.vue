<template>
    <div class="page-user">
        <div class="avatar-wrapper">
            <div class="bg"></div>
            <div class="avatar">
                <div class="img"></div>
                <div class="name">{{name}}</div>
            </div>
        </div>
        <div class="user-list">
            <i-cell-group>
                <i-cell @click="showEditName" title="用户昵称" :value="name" is-link></i-cell>
                <i-cell title="登录手机号" :value="mobile"></i-cell>
                <i-cell title="关于我们" is-link url="/pages/aboutUs/main"></i-cell>
                <i-cell title="设置" is-link url="/pages/setting/main"></i-cell>
            </i-cell-group>
        </div>
        <i-modal  title="修改昵称" :visible="isShowEditName" @ok="confirmEdit" @cancel="cancelEdit">
            <i-input
                i-class="name-line"
                placeholder="请输入昵称"
                autofocus
                @change="change"
                :value="editName"></i-input>
        </i-modal>
    </div>
</template>

<script>
    import { isForbidden } from "utils/stopRequest";

    export default {
        data() {
            return {
                name: '环保达人110',
                isShowEditName: false,
                editName: ''
            }
        },
        onLoad() {
            wx.setNavigationBarColor({
                frontColor: "#000000",
                backgroundColor: "#f5d739"
            });
        },
        methods: {
            showEditName() {
                this.editName = this.name;
                this.isShowEditName = true;
            },
            confirmEdit() {
                this.name = this.editName;
                this.cancelEdit()
            },
            cancelEdit() {
                this.isShowEditName = false;
            },
            change(e) {
                this.editName = e.target.detail.value;
            }
        },
        computed: {
            mobile() {
                return '158****8888'
            }
        }
    };
</script>

<style scoped lang="less">
    @import "../../styles/mixin";

    .page-user {
        .avatar-wrapper {
            position: relative;
            height: 200px;
            background: #fff;
            width: 100vw;
            overflow: hidden;
            .bg {
                width: 120vw;
                height: 300px;
                border-radius: 50%;
                background: @mainColor;
                position: absolute;
                top: -220px;
                left: -10vw;
            }
            .avatar {
                position: absolute;
                z-index: 10;
                top: 40px;
                left: 50%;
                transform: translate(-50%, 0);
                .img {
                    width: 90px;
                    height: 90px;
                    background: #fff;
                    border-radius: 50%;
                    box-shadow: 0 0 5px 2px rgba(0, 0, 0, .1);
                }
                .name {
                    margin-top: 10px;
                    color: @fontColor3;
                }
            }
        }
        .name-line {
            border-bottom: 1rpx solid @mainColor;
        }
    }
</style>
