<template>
  <div class="user">
    <div class="nav">
      <el-button type="primary" icon="el-icon-menu" circle @click="toggleSiderBar"></el-button>
      <Breadcrumb />
    </div>
    <el-dropdown trigger="click">
      <span class="el-dropdown-link">Welcome！{{ realName }}<i class="el-icon-arrow-down el-icon--right"></i></span>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item>Logout</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
import Cookies from 'js-cookie';
import Breadcrumb from './Breadcrumb.vue';
import { mapActions, mapGetters } from 'vuex';

export default {
  name: 'Header',
  components: {
    Breadcrumb,
  },
  data() {
    return {
      realName: ''
    }
  },
  beforeMount() {
    this.realName = decodeURI(Cookies.get('realName'))
  },
  computed: {
    ...mapGetters(['sideBar'])
  },
  methods: {
    ...mapActions(['toggleSiderBar']),
  }
};
</script>

<style lang="less">
  .user{
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  .nav {
    display: flex;
  }
  .el-dropdown-link{
    color: #fff;
    cursor: pointer;
  }
</style>

