<template>
    <div v-if="!isFetching">
        <div>
            <label>Название</label>
            <input type="text" v-model="article.title">
        </div>

        <div>
            <label>Содержимое</label>
            <textarea v-model="article.content"></textarea>
        </div>

        <div v-if="!productId">
            <label>Продукт</label>
            <select v-model="article.product">
                <option v-if="article.product" :value="null" selected></option>
                <option v-for="product in products" :value="article.product && product.id === article.product.id ? article.product : product">{{product.title}}</option>
            </select>
        </div>
        <button @click="save">Сохранить</button>
    </div>
</template>

<script lang="ts">
    import {Component, Inject, Prop, Vue} from "vue-property-decorator";
    import {IArticle} from "@/model/Article";
    import ArticleService from "@/service/ArticleService";
    import ProductService from "@/service/ProductService";
    import {IProduct} from "@/model/Product";

    @Component
    export default class ArticleUpdate extends Vue {

        @Inject('articleService')
        private articleService!: () => ArticleService;

        @Inject('productService')
        private productService!: () => ProductService;

        @Prop()
        private articleId?: number;

        @Prop()
        private productId?: number;

        public article: IArticle = {};
        public products: IProduct[] = [];
        public isFetching: boolean = false;
        public isSaving: boolean = false;

        public created(): void {
            this.isFetching = true;
            if (this.articleId) {
                this.articleService()
                    .find(this.articleId, true)
                    .then(
                        res => {
                            this.article = res;
                        }
                    )
            }
            if (this.productId) {
                this.productService()
                    .find(this.productId, false)
                    .then(
                        res => {
                            this.article.product = res;
                        }
                    )
            } else {
                this.productService()
                    .retrieve(false)
                    .then(
                        res => {
                            this.products = res.data;
                        }
                    );
            }
            this.isFetching = false
        }

        public save() : void {
            this.isSaving = true;
            if (this.article.id) {
                this.articleService()
                    .update(this.article)
                    .then(
                        res => {
                            this.isSaving = false;
                            this.$router.go(-1);
                        }
                    )
            } else {
                this.articleService()
                    .create(this.article)
                    .then(
                        res => {
                            this.isSaving = false;
                            this.$router.go(-1);
                        }
                    )
            }
            this.isSaving = false;
        }
    }
</script>