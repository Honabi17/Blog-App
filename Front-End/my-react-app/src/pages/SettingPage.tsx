import { useState } from "react";
import useAuth from "../context/useAuth";
import UpdateEmail from "./auth/UpdateEmail";
import PromoteUser from "./admin/PromoteUser";
import "../styles/SettingPage.css";



export default function SettingPage(){

    const [tab, setTab] = useState("email");
    const {user, loading} = useAuth();

    if(loading){
        return <p>Loading...</p>
    }

    return(
        <div className="settings-container">
            <h1 className="settings-title">Settings</h1>

            <div className="settings-tab">
                <button
                    className= {tab === "email" ? "active" : ""}
                    onClick={() => setTab("email")}
                >
                    Update email
                </button>

                {user?.role === "ADMIN" && (
                    <button
                        className={tab === "promote" ? "active" : ""}
                        onClick={() => setTab("promote")}
                    >
                        Promote User
                    </button>
                )}
            </div>

            <div className="settings-content">
                {tab === "email" && <UpdateEmail />}
                {tab === "promote" && <PromoteUser/>}
            </div>
        </div>
    );
}