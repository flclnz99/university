import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vitejs.dev/config/
export default defineConfig({
  base: "/",
  plugins: [react()],
  preview: {
    port: 5173,
    strictPort: true,
  },
  server: {
    watch: {
      usePolling: true,
    },
    port: 5173,
    strictPort: true,
    host: true,
    origin: "http://0.0.0.0:5173",

    proxy: {
      "/api":"http://webui.mymusicspace.it"
    },
    cors:true
  }
})
