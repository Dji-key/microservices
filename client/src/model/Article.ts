interface IArticle {
    id?: number;
    title?: string;
    content?: string;
    creationDate?: string;
    product?: IProduct;
}

class Article implements IArticle {}