<template>
  <div>
    <el-container>
      <el-header class="header-no-bck" height="“82”">
        <el-card shadow="hover">
          <el-row :gutter="20">
            <el-col :span="6">
              <el-select v-model="orderType" placeholder="请选择设备类型">
                <el-option
                  v-for="item in orderTypes"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-col>
            <el-col :span="6">
              <el-date-picker
                v-model="date"
                type="daterange"
                align="right"
                unlink-panels
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                :picker-options="pickerOptions2"
                value-format="yyyy-MM-dd"
                format="yyyy-MM-dd"
              ></el-date-picker>
            </el-col>
            <el-col :span="12">
              <div class="fr">
                <el-button
                  type="info"
                  icon="el-icon-search"
                  size="medium"
                  @click="()=> this.queryOrderList(this.OrderListParams)"
                >查询</el-button>
              </div>
            </el-col>
          </el-row>
        </el-card>
      </el-header>
      <el-main>
        <el-card shadow="hover">
          <el-table :data="tableList" stripe style="width: 100%">
            <el-table-column prop="id" label="订单ID"></el-table-column>
            <el-table-column prop="orderId" label="设备ID" width="180"></el-table-column>
            <el-table-column prop="orderType" label="订单类型"></el-table-column>
            <el-table-column prop="weight" label="重量"></el-table-column>
            <el-table-column prop="price" label="价格"></el-table-column>
            <el-table-column prop="amount" label="金额"></el-table-column>
            <el-table-column prop="deliveryTime" label="投递日期" width="180"></el-table-column>
          </el-table>
          <el-pagination
            layout="prev, pager, next"
            :total="total"
            @current-change="handleCurrentChange"
          ></el-pagination>
        </el-card>
      </el-main>
    </el-container>
  </div>
</template>

<script type="es6">
import { orderTypes, pickerOptions2 } from '@/lib/obj.js';
import { queryOrderList } from '@/api';

export default {
  data() {
    return {
      // 设备类型枚举
      pickerOptions2,
      orderTypes,
      orderType: '',
      date: '',
      tableList: [],
      pageNumber: '1',
      pageSize: '10',
      total: 0,
    };
  },
  created() {
    this.queryOrderList(this.OrderListParams);
  },
  computed: {
    OrderListParams() {
      return {
        orderType: this.orderType,
        startDate: this.date && this.date[0],
        endDate: this.date && this.date[1],
        pageNumber: this.pageNumber,
        pageSize: this.pageSize,
      };
    },
  },
  methods: {
    handleCurrentChange(val) {
      this.pageNumber = val;
      this.queryOrderList(this.OrderListParams);
    },
    /**
     * 获取订单列表
     * @param pageNumber
     * @param pageSize
     * @param startDate
     * @param endDate
     * @param orderType
     * @returns {Promise<void>}
     */
    async queryOrderList(queryParams) {
      let res = await queryOrderList(queryParams);
      this.tableList = res.result.dataList;
      this.pageNumber = res.result.pageNumber;
      this.pageSize = res.result.pageSize;
      this.total = res.result.total;
    },
  },
};
</script>

<style lang="less">
</style>