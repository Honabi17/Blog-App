import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { login } from "../../services/authService";

export default function LoginForm() {
  const [form, setForm] = useState({ username: "", password: "" });
  const [loading, setLoading] = useState(false);
  const [errors, setErrors] = useState("");
  const navigate = useNavigate();

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setErrors("");
    setLoading(true);

    if (!form.username.trim() || !form.password.trim()) {
      setErrors("Fill in the username and password fields.");
      setLoading(false);
      return;
    }

    try {
      const data = await login(form.username, form.password);
      localStorage.setItem("token", data.token);
      navigate("/dashboard");
    } catch (err) {
      setErrors("Invalid credentials!");
    } finally {
      setLoading(false);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div className="input-group">
        <p>Username</p>
        <input
          className="input"
          type="text"
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
  );
}
