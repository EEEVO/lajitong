// ele时间选择器格式化
const pickerOptions2 = {
  shortcuts: [
    {
      text: '最近一周',
      onClick(picker) {
        const end = new Date();
        const start = new Date();
        start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
        picker.$emit('pick', [start, end]);
      },
    },
    {
      text: '最近一个月',
      onClick(picker) {
        const end = new Date();
        const start = new Date();
        start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
        picker.$emit('pick', [start, end]);
      },
    },
    {
      text: '最近三个月',
      onClick(picker) {
        const end = new Date();
        const start = new Date();
        start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
        picker.$emit('pick', [start, end]);
      },
    },
  ],
};

// 设备类型枚举
const orderTypes = [
  {
    value: '401',
    label: '纸张',
  },
  {
    value: '402',
    label: '金属',
  },
  {
    value: '403',
    label: '塑料',
  },
  {
    value: '404',
    label: '纺织物',
  },
];

export { pickerOptions2, orderTypes };
