import { api } from "../api/api";


export async function getTrafficStats(){
    const response = await api.get("/dashboard/traffic");
    return response.data;
}

export async function getEarningStats(){
    const response = await api.get("/dashboard/earnings");
    return response.data;
}

export async function getRecentPost() {
    const response = await api.get("/dashboard/recent-posts");
    return response.data;
    
}

export async function getDashboardStats() {
    const response = await api.get("/dashboard/stats");
    return response.data;
}