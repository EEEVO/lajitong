Component({
    properties: {
        Length: {
            type: Number,
            value: 6
        },
        isFocus: {
            type: Boolean,
            value: true
        },
        Value: {
            type: String,
            value: ""
        },
        ispassword: {
            type: Boolean,
            value: false
        }
    },
    methods: {
        Focus(e) {
            let inputValue = e.detail.value;
            this.setData({
                Value: inputValue
            });
            if (inputValue.length < 7) {
                this.triggerEvent("submit", `${inputValue}`);
            }
        },
        Tap() {
            this.setData({
                isFocus: true
            });
        }
    }
});
