<template>
    <div class="table">
        <h3 v-if="!productId">Все статьи</h3>
        <h3 v-else>Статьи продукта</h3>
        <router-link v-if="!productId" :to="{name: 'ArticleCreate'}">Создать статью</router-link>
        <router-link v-else :to="{name: 'ArticleCreate', params: {productId}}">Добавить статью</router-link>
        <div v-if="!isFetching && articles.length !== 0">
            <table>
                <thead>
                <tr>
                    <th>Название</th>
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
                    <td>{{article.creationDate}}</td>
                    <td v-if="!productId">
                        <router-link :to="{name: 'ProductDetails', params: {productId: article.product.id}}">{{article.product.title}}</router-link>
                    </td>
                    <td>
                        <router-link tag="button" :to="{name: 'ArticleUpdate', params: {articleId: article.id}}">Редактировать</router-link>
                    </td>
                    <td>
                        <button @click="remove(article.id)">Удалить</button>
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
            this.retrieveAll();
        }

        public retrieveAll(): void {
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

        public remove(id: number): void {
            this.articleService()
                .delete(id)
                .then(
                    res => {
                        this.retrieveAll();
                    }
                )
        }
    }
</script>