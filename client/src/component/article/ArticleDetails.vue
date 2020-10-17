<template>
    <div>
    </div>
</template>

<script lang="ts">
    import {Component, Inject, Prop, Vue} from "vue-property-decorator";
    import {IArticle} from "@/model/Article";
    import ArticleService from "@/service/ArticleService";
    import {Route} from "vue-router";

    @Component
    export default class ArticleDetails extends Vue {

        @Inject('articleService') private articleService!: () => ArticleService;

        @Prop()
        private articleId!: number;

        public article: IArticle = {};
        private isFetching: boolean = false;

        public mounted(): void {
            this.isFetching = true;
            this.articleService()
                .find(this.articleId)
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