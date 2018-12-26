const path = require('path');
const objOS = require('os');

function resolve(dir) {
  return path.join(__dirname, dir);
}

module.exports = {
  /** 区分打包环境与开发环境
   * process.env.NODE_ENV==='production'  (生产)
   * process.env.NODE_ENV==='development' (开发环境)
   * baseUrl: process.env.NODE_ENV==='production'?"https://cdn.didabisai.com/front/":'front/',
   */
  baseUrl: '/',
  // process.env.NODE_ENV === 'production'
  //   ? 'https://cdn.didabisai.com/front/'
  //   : '/',

  // 当运行 vue-cli-service build 时生成的生产环境构建文件的目录。注意目标目录在构建之前会被清除 (构建时传入 --no-clean 可关闭该行为)。
  outputDir: 'dist',

  lintOnSave: true,

  runtimeCompiler: false, // babel-loader默认会跳过`node_modules`依赖. // 通过这个选项可以显示转译一个依赖

  transpileDependencies: [
    /* string or regex */
  ], // 是否为生产环境构建生成sourceMap?

  productionSourceMap: false, // 调整内部的webpack配置. // see https://github.com/vuejs/vue-cli/blob/dev/docs/webpack.md

  //添加别名配置
  chainWebpack: config => {
    config.resolve.alias
      .set('@', resolve('src'))
      .set('public', resolve('public'));
  },

  configureWebpack: () => {}, // CSS 相关选项

  css: {
    // 是否使用css分离插件 ExtractTextPlugin
    extract: true,

    // 允许生成 CSS source maps?
    sourceMap: false,

    // CSS预设器配置项
    loaderOptions: {},

    // 启用CSS modules
    modules: false,
  },

  parallel: objOS.cpus().length > 1, // PWA 插件相关配置 // see https://github.com/vuejs/vue-cli/tree/dev/packages/%40vue/cli-plugin-pwa

  pwa: {}, // configure webpack-dev-server behavior

  devServer: {
    open: process.platform === 'darwin',

    disableHostCheck: false,

    host: '0.0.0.0',

    port: 8088,

    https: false,

    hotOnly: false, // See https://github.com/vuejs/vue-cli/blob/dev/docs/cli-service.md#configuring-proxy

    proxy: {
      '/': {
        target: 'http://www.xiaoredcap.com/',
        changeOrigin: true,
        ws: false,
        pathRewrite: {
          '^/': '/',
        },
      },
    },
    // proxy: null,

    // before: app => {},
  }, // 第三方插件配置

  pluginOptions: {},
};
