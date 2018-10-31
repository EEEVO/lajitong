Component({
    properties: {
        tipText: {
            type: String,
            value: '',
        },
        btnText: {
            type: String,
            value: ''
        }
    },

    methods: {
        handleTap () {
            this.triggerEvent('click');
        },
    }
});
