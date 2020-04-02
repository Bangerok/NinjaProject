module.exports = {
    outputDir: 'target/dist',
    assetsDir: 'static',
    publicPath: process.env.NODE_ENV === 'production' ?
        '/ninja-server/' :
        './',
};