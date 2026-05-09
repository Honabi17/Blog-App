import { useState } from "react";
import { useSearchParams } from "react-router-dom";
import { confirmPasswordReset } from "../../services/authService";



export default function ConfirmReset(){

    const [params] = useSearchParams();
    const token = params.get("token") || "";

    const [password, setPassword] = useState("");
    const [message, setMessage] = useState("");

    const handleSubmit = async (e:React.FormEvent) => {
        e.preventDefault();
        const response = await confirmPasswordReset(token, password);
        setMessage(response.data);
    }

    return(
        <div className="auth-card">
            <h2>Choose your password</h2>

            <form onSubmit={handleSubmit}>
                <input 
                    type="password"
                    placeholder="Insert the new password..."
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />

                <button type="submit">Save</button>
            </form>

            {message && <p>{message}</p>}

        </div>
    );
}