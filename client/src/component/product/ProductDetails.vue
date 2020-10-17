<template>
    <div>
        <dl>
            <dt><span>Название</span></dt>
            <dd>{{product.title}}</dd>

            <dt><span>Описание</span></dt>
            <dd>{{product.description}}</dd>

            <dt><span>Цена</span></dt>
            <dd>{{product.cost}}</dd>
        </dl>
    </div>
</template>

<script lang="ts">
    import {Component, Inject, Prop, Vue} from "vue-property-decorator";
    import {IProduct} from "@/model/Product";
    import ProductService from "@/service/ProductService";
    import {Route} from "vue-router";

    @Component
    export default class ProductDetails extends Vue {

        @Inject('productService') private productService!: () => ProductService;

        @Prop()
        private productId!: number;

        public product: IProduct = {};
        private isFetching: boolean = false;

        public mounted(): void {
            this.isFetching = true;
            this.productService()
                .find(this.productId)
            .then(
                res => {
                    this.product = res;
                    this.isFetching = false;
                },
                err => {
                    this.isFetching = false;
                }
            )
        }
    }
</script>