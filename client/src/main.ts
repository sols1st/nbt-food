import { createApp } from "vue";
import "./style.css";
import App from "./App.vue";
import router from "./router";
import { IonicVue } from "@ionic/vue";
import { createPinia } from "pinia"
import piniaPlugin from 'pinia-plugin-persistedstate'

import "@ionic/vue/css/core.css";

import "@ionic/vue/css/normalize.css";
import "@ionic/vue/css/structure.css";
import "@ionic/vue/css/typography.css";

import "@ionic/vue/css/padding.css";
import "@ionic/vue/css/float-elements.css";
import "@ionic/vue/css/text-alignment.css";
import "@ionic/vue/css/text-transformation.css";
import "@ionic/vue/css/flex-utils.css";
import "@ionic/vue/css/display.css";
import { createLogto, LogtoConfig } from '@logto/vue';

const config: LogtoConfig = {
  endpoint: 'https://auth.app.nbtca.space/',
  appId: 'ntltcfajj6a8vciiha3zt',
  resources: ['https://food.app.nbtca.space'],
  scopes:['admin','role']
};

const pinia = createPinia()
pinia.use(piniaPlugin)

const app = createApp(App);
app.use(router);
app.use(IonicVue);
app.use(pinia)
app.use(createLogto, config);

router.isReady().then(() => {
  app.mount("#app");
});
