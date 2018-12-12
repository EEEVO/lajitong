<script>
import { mapGetters } from 'vuex';
import logo_big from '@/assets/images/logo_big.png';
import logo_small from '@/assets/images/logo_small.png';

export default {
  name: 'Menu',
  render() {
    return (
      <el-menu
        class="el-menu"
        router
        style={{ height: '100vh' }}
        default-active={this.$route.path}
        collapse={this.isCollapse}
      >
        <div class="logo">
          <transition name="fade">
            <img
              src={this.isCollapse ? logo_small : logo_big}
              width={this.isCollapse ? '40' : '120'}
            />
          </transition>
        </div>
        {this.renderMenu(this.$router.options.routes, 0)}
      </el-menu>
    );
  },
  data() {
    return {
      // isCollapse: false
    };
  },
  created() {},
  computed: {
    ...mapGetters(['sideBar']),
    isCollapse() {
      return !this.sideBar;
    },
  },
  methods: {
    renderMenu(optionsRoutes, index) {
      return optionsRoutes.map((item, _index) => {
        if (item.meta.hide) {
          return null;
        }
        if (!item.children) {
          return (
            <el-menu-item index={item.path}>
              {item.meta.icon && <i class={item.meta.icon} />}
              <span slot="title">{item.meta.title}</span>
            </el-menu-item>
          );
        }
        return (
          <el-submenu index={`${index}-${_index}`}>
            <template slot="title">
              {item.meta.icon && <i class={item.meta.icon} />}
              <span slot="title">{item.meta.title}</span>
            </template>
            {this.renderMenu(item.children, _index)}
          </el-submenu>
        );
      });
    },
  },
};
</script>

<style lang="less" scoped>
.logo {
  height: 60px;
  background-color: teal;
  display: flex;
  align-items: center;
  justify-content: center;
}
.el-menu:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.28s;
}
.fade-enter,
.fade-leave-active {
  opacity: 0;
}
</style>
