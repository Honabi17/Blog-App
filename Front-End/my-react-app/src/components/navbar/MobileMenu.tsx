import { useNavigate } from "react-router-dom";

type Props = {
    open: boolean,
    menuRef: React.RefObject<HTMLDivElement>
}

export default function MobileMenu({open, menuRef}: Props){

    const navigate = useNavigate();
    
    return(
        <div 
            ref={menuRef} 
            className={`mobile-menu ${open ? "open" : ""}`}
        >
            

            <div className="mobile-actions">
                <button 
                    className="btn-outline"
                    onClick={() => navigate ("/login")}
                    >
                        Login
                    </button>

                <button 
                    className="btn-primary"
                    onClick={() => navigate ("/register")}
                >
                    Create Account
                </button>
            </div>
        </div>
    );
}