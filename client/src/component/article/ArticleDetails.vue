<template>
    <div>
        <div v-if="!isFetching && article">
            <dl>
                <dt><span>Название</span></dt>
                <dd>{{article.title}}</dd>

                <dt><span>Дата создания</span></dt>
                <dd>{{article.creationDate}}</dd>

                <dt><span>Статья продукта</span></dt>
                <dd><router-link :to="{name: 'ProductDetails', params: {productId: article.product.id}}">{{article.product.title}}</router-link></dd>

                <dt><span>Содержимое</span></dt>
                <dd>{{article.content}}</dd>
            </dl>
        </div>
    </div>
</template>

<script lang="ts">
    import {Component, Inject, Prop, Vue} from "vue-property-decorator";
    import {IArticle} from "@/model/Article";
    import ArticleService from "@/service/ArticleService";

    @Component
    export default class ArticleDetails extends Vue {

        @Inject('articleService')
        private articleService!: () => ArticleService;

        @Prop()
        private articleId!: number;

        public article: IArticle = {};
        private isFetching: boolean = false;

        public mounted(): void {
            this.isFetching = true;
            this.articleService()
                .find(this.articleId, true)
                .then(
                    res => {
                        this.article = res;
                        this.isFetching = false;
                    },
                    err => {
                        this.isFetching = false;
                    }
                )
        }
    }
</script>