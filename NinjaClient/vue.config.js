module.exports = {
  "outputDir": "target/dist",
  "assetsDir": "static",
  "publicPath": "./",
  "devServer": {
    "contentBase": "./dist",
    "compress": true,
    "port": 8000,
    "proxy": {
      "/server" : {
        target: 'http://localhost:9000',
        changeOrigin: true,
      }
    },
    "stats": "errors-only",
    "clientLogLevel": "error"
  },
  "transpileDependencies": [
    "vuetify"
  ],
}