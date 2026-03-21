import {useState} from 'react';
import { login } from '../services/authService';

export default function Login(){

    const[form, setForm] = useState({
        username:"",
        password:""
    });
    const[loading, setLoading] = useState(false);
    const[errors, setErrors] = useState("");


    const handleChange = (e) => {
        setForm({
            ...form,
            [e.target.name]: e.target.value
        });
    }

    const handleSubmit = async (e) =>{
        e.preventDefault();
        setLoading(true);
        setErrors("");


        if(!form.username || !form.password){
            setErrors("Fill in the username and password fields.");
            setLoading(false);
            return;
        }

        try{

            const data = await login(form.username, form.password);
            localStorage.setItem("token", data.token);
        }
        catch(error){
            setErrors("Unvalid credentials!");
        }
        finally{
            setLoading(false);
        }
    }

    return(
        <form onSubmit={handleSubmit}>
            <input 
                name="username"
                value={form.username}
                onChange={handleChange}
            />

            <input 
                name="password"
                value={form.password}
                onChange={handleChange}
            />
            
            <button 
                type="submit"
                disabled={loading}
            > 
                {loading ? "Loading..." : "Login" 
            </button>

            {errors && <p style={{color: "red"}}>{errors}</p>}
        </form>

    );
}