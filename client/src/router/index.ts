import Vue from 'vue'
import VueRouter, {RouteConfig} from 'vue-router'
import Products from "@/component/product/Products.vue";
import ProductDetails from "@/component/product/ProductDetails.vue";
import Articles from "@/component/article/Articles.vue";
import ArticleDetails from "@/component/article/ArticleDetails.vue";
import ArticleUpdate from "@/component/article/ArticleUpdate.vue";

Vue.use(VueRouter);

const routes: Array<RouteConfig> = [
  {
    path: '/products',
    name: 'Products',
    component: Products
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
    component: Articles
  },
  {
    path: '/article/:articleId',
    name: 'ArticleDetails',
    component: ArticleDetails,
    props: true
  },
  {
    path: '/article/create',
    name: 'ArticleCreate',
    component: ArticleUpdate,
  },
  {
    path: '/article/:articleId/edit',
    name: 'ArticleUpdate',
    component: ArticleUpdate,
    props: true
  }
];

const router = new VueRouter({
  routes
});

export default router
