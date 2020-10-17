<template>
    <div>
        <label>Название</label>
        <input type="text" v-model="article.title">

        <label>Содержимое</label>
        <textarea v-model="article.title"></textarea>

        <label>Продукт</label>
        <select v-model="article.product">
            <option v-if="!articleId" :value="null" selected></option>
            <option :value="article.product.title" v-for="product in products">{{product.title}}</option>
        </select>
    </div>
</template>

<script lang="ts">
    import {Component, Inject, Prop, Vue} from "vue-property-decorator";
    import {IArticle} from "@/model/Article";
    import ArticleService from "@/service/ArticleService";
    import ProductService from "@/service/ProductService";
    import {IProduct} from "@/model/Product";

    @Component
    export default class ArticleDetails extends Vue {

        @Inject('articleService')
        private articleService!: () => ArticleService;

        @Inject('productService')
        private productService!: () => ProductService;

        @Prop()
        private articleId?: number;

        public article: IArticle = {};
        public products: IProduct[] = [];
        private isFetching: boolean = false;

        public mounted(): void {
            this.isFetching = true;
            if (this.articleId) {
                this.articleService()
                    .find(this.articleId, true)
                    .then(
                        res => {
                            this.article = res;
                            this.isFetching = false;
                        }
                    )
            }
            this.productService()
                .retrieve(false)
            .then(
                res => {
                    this.products = res.data;
                }
            );
            this.isFetching = false
        }
    }
</script>