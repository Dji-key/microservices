import Vue from 'vue'
import VueRouter, {RouteConfig} from 'vue-router'
import AllProducts from "@/component/product/AllProducts.vue";
import ProductDetails from "@/component/product/ProductDetails.vue";
import AllArticles from "@/component/article/AllArticles.vue";
import ArticleDetails from "@/component/article/ArticleDetails.vue";

Vue.use(VueRouter);

const routes: Array<RouteConfig> = [
  {
    path: '/products',
    name: 'Products',
    component: AllProducts
  },
  {
    path: '/product/:productId',
    name: 'ProductDetails',
    component: ProductDetails,
    props: true
  },
  {
    path: '/articles',
    name: 'Articles',
    component: AllArticles
  },
  {
    path: '/article/:articleId',
    name: 'ArticleDetails',
    component: ArticleDetails,
    props: true
  }
];

const router = new VueRouter({
  routes
});

export default router
