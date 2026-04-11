import { api } from "../api/api";


export async function login(username: string, password: string){
    try{    
        const response = await api.post("/auth/login", {
            username,
            password
        });

        const token = response.data.token;
        localStorage.setItem("token", token);

        return response.data;
    }
    catch(error:any){
        throw error.response?.data || {
            message: "Login Failed"
        }
    }
}

export async function getMe() {
    const response = await api.get("/auth/me");
    return response.data;
}