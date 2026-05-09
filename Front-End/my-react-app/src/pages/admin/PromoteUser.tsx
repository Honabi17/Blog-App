import { useState } from "react";
import { promoteUser } from "../../api/api";
import "../../styles/SettingPage.css";



export default function PromoteUser(){

    const [username, setUsername] = useState("");
    const [role, setRole] = useState("MODERATOR");
    const [message, setMessage] = useState("");

    const handleSubmit = async (e: React.FormEvent) =>{
        e.preventDefault();

        const response = await promoteUser(username, role);
        setMessage(`User promoted to ${response.data.role}`);
    }

    return(
        <div className="admin-card">
            <h2>Promote User</h2>

            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    placeholder="Insert a username..."
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                />

                <select
                    value={role}
                    onChange={(e) => setRole(e.target.value)}
                >
                    <option 
                        value="MODERATOR"
                    >
                        MODERATOR
                    </option>
                    <option
                        value="USER"
                    >
                        USER
                    </option>
                </select>

                <button type="submit">Save</button>
            </form>

            {message && <p>{message}</p>}
        </div>
    );
}