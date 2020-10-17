<template>
    <div class="table">
        <h1 v-if="!productId">Все статьи</h1>
        <h1 v-else>Статьи продукта</h1>
        <div v-if="!isFetching && articles">
            <table>
                <thead>
                <tr>
                    <th>Название</th>
                    <th>Содержимое</th>
                    <th>Дата создания</th>
                    <th v-if="!productId">Продукт</th>
                    <th/>
                    <th/>
                </tr>
                </thead>
                <tbody>
                <tr v-for="article in articles">
                    <td>
                        <router-link :to="{name: 'ArticleDetails', params: {articleId: article.id}}">{{article.title}}</router-link>
                    </td>
                    <td>{{article.content}}</td>
                    <td>{{article.creationDate}}</td>
                    <td v-if="!productId">
                        <router-link :to="{name: 'ProductDetails', params: {productId: article.product.id}}">{{article.product.title}}</router-link>
                    </td>
                    <td>
                        <router-link :to="{name: 'ArticleUpdate', params: {articleId: article.id}}">Редактировать</router-link>
                    </td>
                    <td>
                        Удалить
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script lang="ts">
    import {Component, Inject, Prop, Vue} from "vue-property-decorator";
    import {IArticle} from "@/model/Article";
    import ArticleService from "@/service/ArticleService";

    @Component
    export default class Articles extends Vue {

        @Inject('articleService')
        private articleService!: () => ArticleService;

        @Prop()
        private productId?: number;

        public articles: IArticle[] = [];
        private isFetching: boolean = false;

        public mounted(): void {
            this.isFetching = true;
            if (this.productId) {
                this.articleService()
                .findByProductId(this.productId, false)
                .then(
                    res => {
                        this.articles = res.data;
                    }
                )
            } else {
                this.articleService()
                    .retrieve(true)
                    .then(
                        res => {
                            this.articles = res.data;
                            this.isFetching = false;
                        }
                    )
            }
            this.isFetching = false;
        }
    }
</script>

<style>
    #table {
        font-family: Avenir, Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
    }
</style>