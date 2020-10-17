<template>
    <div>
        <h1>Все статьи</h1>
        <div v-if="!isFetching && articles">
            <table>
                <thead>
                <tr>
                    <th>Название</th>
                    <th>Содержимое</th>
                    <th>Дата создания</th>
                    <th>Продукт</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="article in articles">
                    <td>
                        <router-link :to="{name: 'ArticleDetails', params: {articleId: article.id}}">{{article.title}}</router-link>
                    </td>
                    <td>{{article.content}}</td>
                    <td>{{article.creationDate}}</td>
                    <td>
                        <router-link :to="{name: 'ProductDetails', params: {productId: article.product.id}}">{{article.product.title}}</router-link>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script lang="ts">
    import {Component, Inject, Vue} from "vue-property-decorator";
    import {IArticle} from "@/model/Article";
    import ArticleService from "@/service/ArticleService";

    @Component
    export default class AllArticles extends Vue {

        @Inject('articleService')
        private articleService!: () => ArticleService;

        public articles: IArticle[] = [];
        private isFetching: boolean = false;

        public mounted(): void {
            this.isFetching = true;
            this.articleService()
                .retrieve()
                .then(
                    res => {
                        this.articles = res.data;
                        this.isFetching = false;
                    },
                    err => {
                        this.isFetching = false;
                    }
                )
        }
    }
</script>