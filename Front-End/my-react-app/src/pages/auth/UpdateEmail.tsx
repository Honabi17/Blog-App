import { useState } from "react";
import { updateNewEmail } from "../../services/authService";
import {useNavigate} from "react-router-dom";
import "../../styles/SettingPage.css";

export default function UpdateEmail(){

    const navigate = useNavigate();

    const [email, setEmail] = useState("");
    const [message, setMessage] = useState("");

    const handleSubmit = async (e:React.FormEvent) => {
        e.preventDefault();

        const response = await updateNewEmail(email);
        setMessage(`Email updated to ${response.data.email}`);
    }

    return(
        <div className="profile-card">
            <h2>Update Email</h2>

            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    placeholder="Insert the new email..."
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                />

                <button type="submit">Save</button>
                <button 
                    type="button"
                    onClick={() => navigate(-1)}>Cancel</button>
            </form>

            {message && <p>{message}</p>}
        </div>
    );
}