<template>
    <div class="table">
        <h3>Все продукты</h3>
        <router-link :to="{name: 'ProductCreate'}">Создать продукт</router-link>
        <div v-if="!isFetching && products.length !== 0">
            <table>
                <thead>
                <tr>
                    <th>Название</th>
                    <th>Описание</th>
                    <th>Цена</th>
                    <th>Статьи</th>
                    <th/>
                    <th/>
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
                    <td>
                        <router-link tag="button" :to="{name: 'ProductUpdate', params: {productId: product.id}}">Редактировать</router-link>
                    </td>
                    <td>
                        <button class="button" @click="remove(product.id)">Удалить</button>
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

        public created(): void {
            this.retrieveAll();
        }

        private retrieveAll(): void {
            this.isFetching = true;
            this.productService()
                .retrieve(true)
                .then(
                    res => {
                        this.products = res.data;
                    }
                );
            this.isFetching = false;
        }

        public remove(id: number): void {
            this.productService()
                .delete(id)
                .then(
                    res => {
                        this.retrieveAll()
                    }
                )
        }
    }
</script>