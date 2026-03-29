import { RegisterForm } from "../components/RegisterForm";
import Login from "./Login";
import "./Auth.css";

export default function Auth() {
  return (
    <div className="auth-page">
      <h1 className="auth-brand">MyBlog</h1>
      <div className="auth-container">
        

        <div className="auth-card">
          <h1 className="auth-title">Create Account</h1>
          <p className="auth-subtitle">Join our community and start writing.</p>

          <RegisterForm />

          <button className="btn-google">
            <svg width="18" height="18" viewBox="0 0 48 48">
              <path fill="#EA4335" d="M24 9.5c3.54 0 6.71 1.22 9.21 3.6l6.85-6.85C35.9 2.38 30.47 0 24 0 14.62 0 6.51 5.38 2.56 13.22l7.98 6.19C12.43 13.02 17.74 9.5 24 9.5z"/>
              <path fill="#4285F4" d="M46.5 24.5c0-1.57-.14-3.08-.41-4.5H24v9h12.85c-.56 2.85-2.23 5.26-4.74 6.88l7.27 5.64C43.79 37.52 46.5 31.41 46.5 24.5z"/>
              <path fill="#FBBC05" d="M10.54 28.41c-.48-1.43-.75-2.96-.75-4.41s.27-2.98.75-4.41l-7.98-6.19C.92 16.33 0 20.06 0 24s.92 7.67 2.56 10.59l7.98-6.18z"/>
              <path fill="#34A853" d="M24 48c6.47 0 11.9-2.38 15.93-6.47l-7.27-5.64c-2.02 1.36-4.63 2.11-8.66 2.11-6.26 0-11.57-3.52-13.46-8.91l-7.98 6.18C6.51 42.62 14.62 48 24 48z"/>
            </svg>
            Continue with Google
          </button>
        </div>

        <div className="auth-card">
          <Login />
        </div>
      </div>
    </div>
  );
}
