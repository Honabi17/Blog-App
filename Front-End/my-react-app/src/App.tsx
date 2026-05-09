import { BrowserRouter, Routes, Route } from "react-router-dom";
import "./App.css";
import Auth from "./pages/auth/Auth";
import Landing from "./pages/Landing";
import Register from "./pages/Register";
import Login from "./pages/Login";
import ProtectedRoute from "./components/ProtectedRoute";
import Dashboard from "./pages/Dashboard";
import CategoryPage from "./pages/CategoryPage";
import DashboardLayout from "./layouts/DashboardLayout";
import PostPage from "./pages/PostPage";
import PostDetailsPage from "./pages/PostDetailsPage";
import CommentPage from "./pages/CommentPage";
import SettingPage from "./pages/SettingPage";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Landing />} />
        <Route path="/auth" element={<Auth />} />
        <Route path="/Login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route
          path="/dashboard"
          element={
            <ProtectedRoute>
              <DashboardLayout>
                <Dashboard />
              </DashboardLayout>
            </ProtectedRoute>
          }
        />
        <Route
          path="/categories"
          element={
            <ProtectedRoute>
              <DashboardLayout>
                <CategoryPage />
              </DashboardLayout>
            </ProtectedRoute>
          }
        />
        <Route
          path="/posts/:id"
          element={
            <ProtectedRoute>
              <DashboardLayout>
                <PostDetailsPage />
              </DashboardLayout>
            </ProtectedRoute>
          }
        />
        <Route
          path="/posts"
          element={
            <ProtectedRoute>
              <DashboardLayout>
                <PostPage />
              </DashboardLayout>
            </ProtectedRoute>
          }
        />
        <Route
          path="/comments"
          element={
            <ProtectedRoute>
              <DashboardLayout>
                <CommentPage />
              </DashboardLayout>
            </ProtectedRoute>
          }
        />
        <Route
          path="/settings"
          element={
            <ProtectedRoute>
              <DashboardLayout>
                <SettingPage />
              </DashboardLayout>
            </ProtectedRoute>
          }
        />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
