module.exports = {
  "outputDir": "target/dist",
  "assetsDir": "static",
  "publicPath": "./",
  "devServer": {
    "contentBase": "./dist",
    "compress": true,
    "port": 8000,
    "proxy": {
      "/api" : {
        target: 'http://localhost:9000',
        ws: true,
        changeOrigin: true,
        pathRewrite: {
          '^/api': '',
        },
      }
    },
    "stats": "errors-only",
    "clientLogLevel": "error"
  },
  "transpileDependencies": [
    "vuetify"
  ],
}