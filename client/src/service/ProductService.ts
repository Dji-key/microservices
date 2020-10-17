import axios from 'axios';
import {IProduct} from "@/model/Product";

const articleUrl = 'http://localhost:8081/product';

export default class ProductService {

    public retrieve(fetchArticles?: boolean): Promise<any> {
        return new Promise<any>(resolve => {
            axios.get(articleUrl).then(function(res) {
                resolve(res);
            });
        });
    }

    public find(id: number, fetchArticles?: boolean): Promise<IProduct> {
        return new Promise<IProduct>(resolve => {
            axios.get(`${articleUrl}/${id}`).then(function(res) {
                resolve(res.data);
            });
        });
    }

    public delete(id: number): Promise<any> {
        return new Promise<any>(resolve => {
            axios.delete(`${articleUrl}/${id}`).then(function(res) {
                resolve(res);
            });
        });
    }

    public create(entity: IProduct): Promise<IProduct> {
        return new Promise<IProduct>(resolve => {
            axios.post(`${articleUrl}`, entity).then(function(res) {
                resolve(res.data);
            });
        });
    }

    public update(entity: IProduct): Promise<IProduct> {
        return new Promise<IProduct>(resolve => {
            axios.put(`${articleUrl}`, entity).then(function(res) {
                resolve(res.data);
            });
        });
    }
}