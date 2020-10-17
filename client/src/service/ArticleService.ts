import axios from 'axios';
import {IArticle} from "@/model/Article";

const articleUrl = 'http://localhost:8081/article';

export default class ArticleService {

    public retrieve(paginationQuery?: any): Promise<any> {
        return new Promise<any>(resolve => {
            axios.get(articleUrl).then(function(res) {
                resolve(res);
            });
        });
    }

    public find(id: number): Promise<IArticle> {
        return new Promise<IArticle>(resolve => {
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

    public create(entity: IArticle): Promise<IArticle> {
        return new Promise<IArticle>(resolve => {
            axios.post(`${articleUrl}`, entity).then(function(res) {
                resolve(res.data);
            });
        });
    }

    public update(entity: IArticle): Promise<IArticle> {
        return new Promise<IArticle>(resolve => {
            axios.put(`${articleUrl}`, entity).then(function(res) {
                resolve(res.data);
            });
        });
    }
}