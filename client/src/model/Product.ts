import {IArticle} from "@/model/Article";

export interface IProduct {
    id?: number;
    title?: string;
    description?: string;
    cost?: number;
    articles?: IArticle[];
}

export class Product implements IProduct {
    constructor(
        public id?: number,
        public title?: string,
        public description?: string,
        public cost?: number,
        public articles?: IArticle[]
    ) {}
}