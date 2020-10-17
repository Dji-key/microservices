import Vue from 'vue'
import VueRouter, {RouteConfig} from 'vue-router'
import AllProducts from "@/component/product/AllProducts.vue";
import AllArticles from "@/component/article/AllArticles.vue";

Vue.use(VueRouter)

const routes: Array<RouteConfig> = [
  {
    path: '/products',
    name: 'Products',
    component: AllProducts
  },
  {
    path: '/articles',
    name: 'Articles',
    component: AllArticles
  }
]

const router = new VueRouter({
  routes
})

export default router
