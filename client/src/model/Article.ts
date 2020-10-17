import {IProduct} from "@/model/Product";

export interface IArticle {
    id?: number;
    title?: string;
    content?: string;
    creationDate?: string;
    product?: IProduct;
}

export class Article implements IArticle {
    constructor(
        public id?: number,
        public title?: string,
        public content?: string,
        public creationDate?: string,
        public product?: IProduct
    ) {}
}