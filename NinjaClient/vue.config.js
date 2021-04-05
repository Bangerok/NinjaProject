module.exports = {
  "devServer": {
    "port": 3000,
    "proxy": {
      "/api": {
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