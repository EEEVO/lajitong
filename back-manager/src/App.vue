<template>
  <div id="app">
    <el-container>
      <!-- 左侧菜单栏 -->
      <el-aside width="auto" id="aside">
        <Menu/>
      </el-aside>
      <!--右侧菜单栏 -->
      <el-container>
        <el-header class="top-header">
          <Header/>
        </el-header>
        <el-main>
          <transition name="fade-transform" mode="out-in">
            <router-view class="app-router-view" :key="key"/>
          </transition>
        </el-main>
        <el-footer>Admin @2018 by XIXIXI</el-footer>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import Menu from './components/common/Menu.vue';
import Header from './components/common/Header.vue';

export default {
  name: 'app',
  components: {
    Menu,
    Header,
  },
  computed: {
    ...mapGetters(['sideBar']),
    key() {
      return this.$route.fullPath;
    },
  },
};
</script>

<style lang="less">
@import './assets/less/common.less';
.top-header {
  background-color: teal;
  color: #fff;
  text-align: center;
  line-height: 60px;
}
.el-footer {
  background-color: #f0f2f5;
  color: #827d7d;
  text-align: center;
  line-height: 60px;
  font-size: 14px;
}
.el-main {
  .fade-transform-leave-active,
  .fade-transform-enter-active {
    transition: all 0.5s;
  }
  .fade-transform-enter {
    opacity: 0;
    transform: translateX(-30px);
  }
  .fade-transform-leave-to {
    opacity: 0;
    transform: translateX(30px);
  }
  .el-table .disabled-row {
    opacity: 0.5;
  }
  .el-table .highlight-row {
    background: #00828117;
  }
  .el-pagination {
    display: flex;
    justify-content: flex-end;
    margin-top: 20px;
  }
}
</style>
