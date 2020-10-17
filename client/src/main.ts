import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ArticleService from "@/service/ArticleService";

Vue.config.productionTip = false;

new Vue({
  router,
  render: h => h(App),
  provide: {
    articleService: () => new ArticleService()
  }
}).$mount('#app');
