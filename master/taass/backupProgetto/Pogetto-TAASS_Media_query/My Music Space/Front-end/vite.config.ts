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
      "/app":"http://localhost:8091",
      "/artist":"http://localhost:8092",
      "/api":"http://localhost:8080"
    },
    cors:false
  }
})
