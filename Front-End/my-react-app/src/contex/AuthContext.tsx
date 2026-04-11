import { createContext, useEffect, useState } from "react";
import {getMe} from "../services/authService";
import {AuthContextType} from '../types/AuthTypes';

export const AuthContext = createContext< AuthContextType | null>(null);

export function AuthProvider({children}: {children: React.ReactNode}){

    const [token, setToken] = useState<string | null>(null);
    const [user, setUser] = useState<any>(null);
    const [loading, setLoading] = useState(true);

    useEffect(() =>{
        const storedToken = localStorage.getItem("token");

        if(!storedToken){
            setLoading(false);
            return
        }

        setToken(storedToken);

        getMe()
        .then((user) => setUser(user))
        .catch(() => logout())
        .finally(() => setLoading(false));
    }, []);

    function login(data: any) {
        setToken(data.token);
        localStorage.setItem("token", data.token);

        getMe().then((user) => setUser(user));
    }

    function logout() {
        setToken(null);
        setUser(null);
        localStorage.removeItem("token");
    }

    return(
        <AuthContext.Provider value={{user, token, login, logout, loading}}>
            {children}
        </AuthContext.Provider>
    );
}