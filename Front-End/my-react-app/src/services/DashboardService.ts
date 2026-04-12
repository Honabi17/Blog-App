import { api } from "../api/api";


export async function getTrafficStats(){
    const response = await api.get("/dashboard/traffic");
    return response.data;
}

export async function getEarningStats(){
    const response = await api.get("/dashboard/earning");
    return response.data;
}