<template>
    <div class="page-qrcode">
        <div @click="this.closeQcode" class="q-code">
            <div class="canvas-wrapper">
                <canvas :style="canvasStyle" class='canvas' canvas-id='canvas' bindlongtap='save'></canvas>
            </div>

        </div>
    </div>
</template>

<script>
    import { isForbidden } from "utils/stopRequest";
    import QRCode from "utils/weapp-qrcode.js";

    export default {
        data() {
            return {
                qcodeShowFlag: false,
                qrcode: null,
                qrcode_w: 0
            }
        },
        onLoad() {
            this.createCode()
        },
        methods: {
            createCode() {
                let userId = wx.getStorageSync('userId');
                const W = wx.getSystemInfoSync().windowWidth;
                const rate = 750.0 / W;

                const qrcode_w = 480 / rate;
                this.qrcode_w = qrcode_w;
                if (this.qrcode) {
                    console.log(this.qrcode);
                    this.qrcode.makeCode(userId)
                    return
                }
                this.qrcode = new QRCode("canvas", {
                    // usingIn: this,
                    text: userId,
                    width: qrcode_w,
                    height: qrcode_w,
                    colorDark: "#000000",
                    colorLight: "#ffffff",
                    correctLevel: QRCode.CorrectLevel.H
                });
            }
        },
        computed: {
            canvasStyle() {
                const W = wx.getSystemInfoSync().windowWidth;
                const rate = 750.0 / W;
                const qrcode_w = 480 / rate;
                return `height: ${qrcode_w}px;width: ${qrcode_w}px`
            }
        }
    };
</script>

<style scoped lang="less">
    .page-qrcode {
        height: 100vh;
        .q-code {
            position: fixed;
            top: 0;
            bottom: 0;
            left: 0;
            right: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            background: rgba(0, 0, 0, .7);
            z-index: 100;
            .canvas-wrapper {
                background: #fff;
                border-radius: 4px;
                overflow: hidden;
                height: 300px;
                width: 300px;
                display: flex;
                justify-content: center;
                align-items: center;
            }
            canvas {
                /*height: 300px!important;*/
                /*width: 300px!important;*/
                z-index: 101;
            }
        }
    }
</style>
