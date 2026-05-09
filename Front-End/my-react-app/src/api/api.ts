import axios from 'axios';

export const api = axios.create({
    baseURL: "http://localhost:8081/api",
});

api.interceptors.request.use((config) => {
    const token = localStorage.getItem("token");

    if(token){
        config.headers.Authorization = `Bearer ${token}`;
    }

    return config;
})

export const promoteUser = (username:string, newRole:string) =>
    api.patch("/admin/promote", {username, newRole});
