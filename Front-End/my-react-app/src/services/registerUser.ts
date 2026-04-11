import { api } from "../api/api";


export async function registerUser(data:{
    username: string;
    email: string;
    password: string;
}) {
    try{
        const response = await api.post("/auth/register", data);
        return response.data;
    }
    catch(error:any){
        throw error.response?.data || {message:"Registration fail!"}
    }
}