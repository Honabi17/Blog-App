import { useForm } from "react-hook-form";
import { checkUsername } from "../../services/userService";
import { useState } from "react";
import { registerUser } from "../../services/registerUser";

export function RegisterForm() {
  const [loading, setLoading] = useState(false);
  const [success, setSuccess] = useState("");
  const {
    register,
    handleSubmit,
    setError,
    formState: { errors },
  } = useForm();

  const registerValidation = {
    username: {
      required: "Username is mandatory",
    },

    email: {
      required: "Email is mandatory",
      pattern: {
        value: /^[^\s@]+@[^\s@]+\.[^\s@]+$/,
        message: "Invalid email format",
      },
    },

    password: {
      required: "Password is mandatory",
      minLength: {
        value: 4,
        message: "Password must have at least 4 characters",
      },
    },
  };

  const onSubmit = async (data: any) => {
    setLoading(true);
    setSuccess("");

    const exists = await checkUsername(data.username);

    if (exists) {
      setError("username", {
        type: "manual",
        message: "This username already exists.",
      });
      setLoading(false);
      return;
    }

    try {
      await registerUser(data);
      setSuccess("Account created successfully!");
    } catch (error : any) {
        const message = error?.message || "Someting went wrong. Try again.";
        setError("email", {type: "manual", message});
      }
      finally {
      setLoading(false);
    }
  };

  return (
    <>
      <form onSubmit={handleSubmit(onSubmit)}>
        <div className="input-group">
          <input
            className="input"
            placeholder="Username..."
            {...register("username", registerValidation.username)}
          />
          {errors.username && (
            <p className="error">{errors.username.message as string}</p>
          )}
        </div>

        <div className="input-group">
          <input
            className="input"
            type="email"
            placeholder="Email..."
            {...register("email", registerValidation.email)}
          />
          {errors.email && (
            <p className="error">{errors.email.message as string}</p>
          )}
        </div>

        <div className="input-group">
          <input
            className="input"
            type="password"
            placeholder="Password..."
            {...register("password", registerValidation.password)}
          />
          {errors.password && (
            <p className="error">{errors.password.message as string}</p>
          )}
        </div>

        <button 
          className="btn-create" 
          type="submit"
          disabled={loading}
        >
          {loading ? "Creating..." : "Create Account"}
        </button>

        {success && <p className="success">{success}</p>}
      </form>
    </>
  );
}
