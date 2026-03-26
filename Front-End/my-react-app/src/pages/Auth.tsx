import { RegisterForm } from "../components/RegisterForm";
import Login from "./Login";
import "./Auth.css";

export default function Auth() {
  return (
    <div className="auth-page">
      <div className="auth-container">
        <div className="auth-card">
          <h1 className="auth-title">Create Account</h1>
          <p className="auth-subtitle">Join our community and start writing.</p>

          <RegisterForm />

          <button className="btn-google">
            <img src="/google-icon.svg" alt="Google" />
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
