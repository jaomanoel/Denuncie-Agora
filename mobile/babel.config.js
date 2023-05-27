module.exports = function (api) {
  api.cache(true);
  return {
    presets: ["babel-preset-expo"],
    plugins: [
      require.resolve("expo-router/babel"),
      "module-resolver",
      {
        root: ["./src"],
        extensions: [".js", ".jsx", ".ts", ".tsx"],
        alias: {
          "@types": "./src/@types",
          "@assets": "./src/assets",
          "@components": "./src/components",
          "@constants": "./src/constants",
        },
      },
    ],
  };
};
