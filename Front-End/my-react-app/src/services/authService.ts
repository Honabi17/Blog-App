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

export const requestPasswordReset = (email:string) => 
    api.post("/auth/reset-password/request", {email});


export const confirmPasswordReset = (token:string, newPassword:string) =>
    api.post("/auth/reset-password/confirm", {token, newPassword});


export const updateNewEmail = (newEmail:string) =>
    api.put("/user/email", {newEmail});