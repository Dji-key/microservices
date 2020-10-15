interface IProduct {
    id?: number;
    title?: string;
    description?: string;
    cost?: number;
    articles?: IArticle[];
}

class Product implements IProduct {}