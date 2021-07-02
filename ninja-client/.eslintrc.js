module.exports = {
  extends: [
    "plugin:vue/essential",
    "plugin:vue/strongly-recommended",
    "plugin:vue/recommended",
    "eslint:recommended"
  ],
  rules: {
    "no-unused-vars": ["error", {"vars": "all", "args": "after-used", "ignoreRestSiblings": false}]
  },
  parser: 'vue-eslint-parser',
  parserOptions: {
    parser: "babel-eslint",
    sourceType: 'module',
  },
  env: {
    "node": true,
  },
}