import { NavLink } from "react-router-dom";
import "../styles/Sidebar.css";
import { useState } from "react";

export default function Sidebar() {
  const [open, setOpen] = useState(window.innerWidth >= 1024);
  const isMobile = window.innerWidth < 1024;

  return (
    <>
      {isMobile && open && (
        <div className="sidebar-overlay" onClick={() => setOpen(false)} />
      )}
      <button className="hamburger" onClick={() => setOpen(!open)}>
        <span></span>
        <span></span>
        <span></span>
        <span></span>
      </button>
      <aside className={`sidebar ${open ? "open" : ""}`}>
        {open && (
          <div className="sidebar-header">
            <h2>MyBlog</h2>
          </div>
        )}

        <nav className="sidebar-menu">
          <NavLink to="/dashboard" onClick={() => isMobile && setOpen(false)}>
            {" "}
            📊 {open && "Dashboard"}
          </NavLink>
          <NavLink to="/categories" onClick={() => isMobile && setOpen(false)}>
            📂 {open && "Categories"}
          </NavLink>
          <NavLink to="/posts" onClick={() => isMobile && setOpen(false)}>
            📝 {open && "Posts"}
          </NavLink>
          <NavLink to="/comments" onClick={() => isMobile && setOpen(false)}>
            💬 {open && "Comments"}
          </NavLink>
           <NavLink to="/settings" onClick={() => isMobile && setOpen(false)}>
            ⚙️ {open && "Settings"}
          </NavLink>
        </nav>
        <button
          className="btn-logout"
          onClick={() => {
            localStorage.removeItem("token");
            window.location.href = "/";
          }}
        >
          ⇦⎗ {open && "Logout"}
        </button>
      </aside>
    </>
  );
}
