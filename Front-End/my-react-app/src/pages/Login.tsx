import React, { useState } from "react";
import { login } from "../services/authService.ts";
import LoginForm from "../components/auth/LoginForm.tsx";
import { useNavigate } from "react-router-dom";
import '../styles/Login.css';

export default function Login() {

  const navigate = useNavigate();

  return (
    <div className="auth-page">
      <div className="auth-container">
        <h1 className="auth-title">Login</h1>
        <p className="auth-subtitle">Welcome back!</p>

        <LoginForm />

        <div className="auth-link">
          <span>Don't have an account?</span>
          <span
            className="auth-link-click"
            onClick={() => navigate ("/register")}
          >
            Create one
          </span>
        </div>
      </div>
    </div>
  );
}
