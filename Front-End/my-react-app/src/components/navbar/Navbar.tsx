import { useEffect, useRef, useState } from "react";
import MobileMenu from "./MobileMenu";
import { useNavigate } from "react-router-dom";


export default function Navbar(){

    const navigate = useNavigate();
    const [open, setOpen] = useState(false);

    const menuRef = useRef<HTMLDivElement>(null);
    const burgerRef = useRef<HTMLDivElement>(null);

    const toggleMenu = () => {
        setOpen(prev => !prev);
    }

    useEffect (() => {
        function handleClickOutside(event: MouseEvent){
            const target = event.target as Node;
            
            if(burgerRef.current?.contains(target)){
                return;
            }

            if(
                open &&
                menuRef.current &&
                !menuRef.current.contains(target)
            ){
                setOpen(false);
            }

        }

        document.addEventListener("mousedown", handleClickOutside);

        return () =>{
            document.removeEventListener("mousedown", handleClickOutside)
        }
    }, [open]);

    return(
        <nav className="navbar">
            <div className="logo">MyBlog</div>
           
            <div 
                className={`hamburger ${open ? "open" : ""}`} 
                ref={burgerRef}
                onClick={toggleMenu}
            >
                <span></span>
                <span></span>
                <span></span>
            </div>
           
            <MobileMenu open={open} menuRef={menuRef} />
            
            <div className="nav-actions">
                <button 
                    className="btn-outline"
                    onClick={() => navigate("/login")}
                >
                    Login
                </button>
    
                <button 
                    className="btn-primary"
                    onClick={()=> navigate("/register")}
                >
                    Create Account
                </button>
            </div>
        </nav>
    );
}