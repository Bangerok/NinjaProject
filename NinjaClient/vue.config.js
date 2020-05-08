module.exports = {
  "outputDir": "target/dist",
  "assetsDir": "static",
  "publicPath": "./",
  "devServer": {
    "contentBase": "./dist",
    "compress": true,
    "port": 8000,
    "allowedHosts": [
      "localhost:9000"
    ],
    "proxy": "http://localhost:9000",
    "stats": "errors-only",
    "clientLogLevel": "error"
  },
  "transpileDependencies": [
    "vuetify"
  ]
}