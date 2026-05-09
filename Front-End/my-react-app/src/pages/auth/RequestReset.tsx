import { useState } from "react";
import { requestPasswordReset } from "../../services/authService";



export default function RequestReset(){

    const [email, setEmail] = useState("");
    const [message, setMessage] = useState("");

    const handleSubmit = async(e:React.FormEvent) => {
        e.preventDefault();
        
        const response = await requestPasswordReset(email);
        setMessage(response.data);
    }

    return(
        <div className="auth-card">
            <h2>Reset Password</h2>
            <form onSubmit={handleSubmit}>
                <input 
                    type="email"
                    placeholder="Insert your email..."
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                />

                <button type="submit">Send reset email</button>
            </form>

            {message && <p>{message}</p>}
        </div>
    );
}