import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ArticleService from "@/service/ArticleService";
import ProductService from "@/service/ProductService";

Vue.config.productionTip = false;

new Vue({
  router,
  render: h => h(App),
  provide: {
    articleService: () => new ArticleService(),
    productService: () => new ProductService()
  }
}).$mount('#app');
