module.exports = {
  "devServer": {
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
}