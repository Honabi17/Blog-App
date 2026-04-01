import { RegisterForm } from "../components/auth/RegisterForm";
import { useNavigate } from "react-router-dom";

export default function Register() {
  const navigate = useNavigate();

  return (
    <>
      <h1 className="auth-title">Create Account</h1>
      <p className="auth-subtitle">Join our community!</p>

      <RegisterForm />

      <p className="auth-link">
        Already have an account?
        <span className="auth-link-click" onClick={() => navigate("/login")}>
          Login
        </span>
      </p>
    </>
  );
}
