import React, { useState } from "react";
import { login } from "../services/authService.ts";
import LoginForm from "../components/auth/LoginForm.tsx";

export default function Login() {
  return (
    <>
      <h1 className="auth-title">Login</h1>
      <p className="auth-subtitle">Welcome back!</p>

      <LoginForm />

      <p className="auth-link">
        Don't have an account?
        <a href="/create-account">Create one</a>
      </p>
    </>
  );
}
