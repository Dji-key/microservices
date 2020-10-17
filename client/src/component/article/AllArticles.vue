<template>
    <div>
        <h1>ARTICLES</h1>
        <div v-if="!isFetching && articles">
            <table>
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

        @Inject('articleService') private articleService!: () => ArticleService;

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