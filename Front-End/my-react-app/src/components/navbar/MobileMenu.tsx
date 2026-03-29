type Props = {
    open: boolean,
    menuRef: React.RefObject<HTMLDivElement>
}

export default function MobileMenu({open, menuRef}: Props){
    return(
        <div 
            ref={menuRef} 
            className={`mobile-menu ${open ? "open" : ""}`}
        >
            

            <div className="mobile-actions">
                <button className="btn-outline">Login</button>
                <button className="btn-primary">Create Account</button>
            </div>
        </div>
    );
}