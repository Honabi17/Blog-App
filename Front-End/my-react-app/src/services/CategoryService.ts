import {api} from "../api/api";
import { Category } from "../types/Category";

export const CategoryService = {
    getAll: async () => {
    const response = await api.get("/category/all");

    return response.data.map((c: any) => ({
        id: c.id,
        name: c.name,
        description: c.description,
        createdBy: c.createdBy,
        createdAt: c.createdAt,
        updatedAt: c.updatedAt
    }));
        
    },

    create: async (data) => {
        const response = await api.post<Category>("/category", data);
        return response.data;
    },

    update : async (id, data) => {
        const response = await api.patch<Category>(`/category/${id}`, data);
        return response.data;
    },

    delete : async (id) => {
        await api.delete(`/category/${id}`)
    }
    
}

