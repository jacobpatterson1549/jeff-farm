import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import axios from "axios";

Vue.config.productionTip = false;

axios.defaults.headers.common["Access-Control-Allow-Origin"] =
  "http://localhost:8081";
axios.defaults.baseURL = "http://localhost:8080/jeff-farm-ws/";

new Vue({
  router,
  render: h => h(App)
}).$mount("#app");
