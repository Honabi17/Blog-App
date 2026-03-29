import { useEffect, useRef, useState } from "react";
import MobileMenu from "./MobileMenu";


export default function Navbar(){

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
                <button className="btn-outline">Login</button>
                <button className="btn-primary">Create Account</button>
            </div>
        </nav>
    );
}