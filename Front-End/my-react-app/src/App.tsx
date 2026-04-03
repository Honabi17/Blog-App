import {BrowserRouter, Routes, Route} from 'react-router-dom';
import "./App.css";
import Auth from "./pages/Auth";
import Landing from "./pages/Landing";
import Register from './pages/Register';
import Login from './pages/Login';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Landing />} />
        <Route path="/auth" element={<Auth />} />
        <Route path="/Login" element={<Login />}/>
        <Route path="/register" element={<Register />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
