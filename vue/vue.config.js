const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  pluginOptions: {
    html: {
      inject: {
        // 设置网页标题
        title: '您想要的网页标题',
      },
    },
  },
})
