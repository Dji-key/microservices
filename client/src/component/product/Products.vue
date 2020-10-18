<template>
    <div>
        <h1>Все продукты</h1>
        <div v-if="!isFetching && products">
            <table>
                <thead>
                <tr>
                    <th>Название</th>
                    <th>Описание</th>
                    <th>Цена</th>
                    <th>Статьи</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="product in products">
                    <td>
                        <router-link :to="{name: 'ProductDetails', params: {productId: product.id}}">{{product.title}}</router-link>
                    </td>
                    <td>{{product.description}}</td>
                    <td>{{product.cost}}</td>
                    <td>
                        <div v-if="product.articles.length !== 0">
                            <p v-for="article in product.articles">
                                <router-link :to="{name: 'ArticleDetails', params: {articleId: article.id}}">{{article.title}}</router-link>
                            </p>
                        </div>
                        <div v-else>
                            Отсутствуют
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script lang="ts">
    import {Component, Inject, Prop, Vue} from "vue-property-decorator";
    import {IProduct} from "@/model/Product";
    import ProductService from "@/service/ProductService";

    @Component
    export default class Products extends Vue {

        @Inject('productService')
        private productService!: () => ProductService;

        public products: IProduct[] = [];
        private isFetching: boolean = false;

        public mounted(): void {
            this.isFetching = true;
            this.productService()
                .retrieve(true)
                .then(
                    res => {
                        this.products = res.data;
                        this.isFetching = false;
                    },
                    err => {
                        this.isFetching = false;
                    }
                )
        }
    }
</script>