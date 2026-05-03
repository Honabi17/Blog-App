import { api } from "../api/api"
import { Comment } from "../types/comment/Comment";


export const CommentService ={

    async getByPost(postId:number): Promise <Comment[]>{
        const response = await api.get("/comments",{
            params:{
                postId,
                page:0,
                size:50,
                sortBy:"createdAt",
                direction:"desc"
            }
        })

        return response.data.content;
    },

    async create(comment:{content:string, postId:number}){
        const response = await api.post("/comments", comment);
        return response.data;
    },

    async update(id:number, content:string){
        const response = await api.put(`/comments/${id}`, {content});
        return response.data;
    },

    async delete(id:number){
        await api.delete(`/comments/${id}`);
    }
}