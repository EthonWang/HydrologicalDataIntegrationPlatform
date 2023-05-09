/**
 * main.js
 *
 * Bootstraps Vuetify and other plugins then mounts the App`
 */
import '@/@iconify/icons-bundle'
// Components
import App from './App.vue'

// Composables
import { createApp } from 'vue'

// Plugins
import { registerPlugins } from '@/plugins'

import '@/styles/styles.scss'
import '@core/scss/index.scss'

const app = createApp(App)

registerPlugins(app)

app.mount('#app')
