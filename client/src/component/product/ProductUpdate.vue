<template>
    <div v-if="!isFetching">
        <div>
            <label>Название</label>
            <input type="text" v-model="product.title">
        </div>

        <div>
            <label>Описание</label>
            <input type="text" v-model="product.description">
        </div>

        <div>
            <label>Цена</label>
            <input type="number" v-model="product.cost">
        </div>

        <articles v-if="productId" :product-id="productId"></articles>

        <button @click="save">Сохранить</button>
    </div>
</template>

<script lang="ts">
    import {Component, Inject, Prop, Vue} from "vue-property-decorator";
    import {IProduct} from "@/model/Product";
    import ProductService from "@/service/ProductService";
    import Articles from "@/component/article/Articles.vue";

    @Component({
        components: {
            'articles': Articles
        }
    })
    export default class ProductUpdate extends Vue {

        @Inject('productService')
        private productService!: () => ProductService;

        @Prop()
        private productId?: number;

        public product: IProduct = {};
        public isFetching: boolean = false;
        public isSaving: boolean = false;

        public created(): void {
            this.isFetching = true;
            if (this.productId) {
                this.productService()
                    .find(this.productId, false)
                    .then(
                        res => {
                            this.product = res;
                        }
                    );
            }
            this.isFetching = false;
        }

        public save(): void {
            this.isSaving = true;
            if (this.product.id) {
                this.productService()
                    .update(this.product)
                    .then(
                        res => {
                            this.isSaving = false;
                            this.$router.go(-1);
                        }
                    )
            } else {
                this.productService()
                    .create(this.product)
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