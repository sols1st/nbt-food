import { ProxyOptions, defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";
import path from "path";
import { VitePWA } from "vite-plugin-pwa";

export default defineConfig({
  plugins: [
    vue(),
    VitePWA({
      registerType: "autoUpdate",
      includeAssets: ["favicon.svg"],
      manifest: {
        name: "NBT FOOD",
        short_name: "NBT FOOD",
        icons: [
          {
            src: "maskable_icon_x512.png",
            sizes: "512x512",
            type: "image/png",
          },
          {
            src: "maskable_icon_x384.png",
            sizes: "384x384",
            type: "image/png",
          },
        ],
      },
      devOptions: {
        enabled: true,
      },
    }),
  ],
  resolve: {
    alias: {
      "@": path.resolve(__dirname, "src"),
    },
  },
  server: {
    proxy: {
      "/api": {
        target: "http://localhost:8080", // 后端服务器的URL
        // target: "http://10.89.193.12:8080",

        changeOrigin: true,
        pathRewrite: {
          "^/api": "/api", // 将/api前缀重写为/api
        },
        autoRewrite: true,
      } as ProxyOptions,
    },
    port: 8081,
    host: "0.0.0.0", // 开放本机地址访问
  },
});
