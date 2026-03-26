import React, { useState } from "react";
import { login } from "../services/authService.ts";

export default function Login() {
  const [form, setForm] = useState({
    username: "",
    password: "",
  });
  const [loading, setLoading] = useState(false);
  const [errors, setErrors] = useState("");

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    setLoading(true);
    setErrors("");

    if (!form.username || !form.password) {
      setErrors("Fill in the username and password fields.");
      setLoading(false);
      return;
    }

    try {
      const data = await login(form.username, form.password);
      localStorage.setItem("token", data.token);
    } catch (error) {
      setErrors("Invalid credentials!");
    } finally {
      setLoading(false);
    }
  };

  return (
    <>
      <h1 className="auth-title">Login</h1>
      <p className="auth-subtitle">Welcome back!</p>

      <form onSubmit={handleSubmit}>
        <div className="input-group">
          <p>Username</p>
          <input
            className="input"
            name="username"
            placeholder="Username..."
            value={form.username}
            onChange={handleChange}
          />
        </div>

        <div className="input-group">
          <p>Password</p>
          <input
            className="input"
            type="password"
            name="password"
            placeholder="Password..."
            value={form.password}
            onChange={handleChange}
          />
        </div>

        <button className="btn-create" type="submit" disabled={loading}>
          {loading ? "Loading..." : "Login"}
        </button>

        {errors && <p className="error">{errors}</p>}
      </form>
    </>
  );
}
