module.exports = {
  configureWebpack: {
    devtool: 'source-map'
  },
  outputDir: 'target/dist',
  assetsDir: 'static',
  publicPath: process.env.NODE_ENV === 'production' ?
      '/ninja-server/' :
      './',
  devServer: {
    contentBase: './dist',
    compress: true,
    port: 8000,
    allowedHosts: [
      'localhost:9000'
    ],
    proxy: 'http://localhost:9000',
    stats: 'errors-only',
    clientLogLevel: 'error',
  },
};