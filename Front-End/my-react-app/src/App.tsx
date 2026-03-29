import {BrowserRouter, Routes, Route} from 'react-router-dom';
import "./App.css";
import Auth from "./pages/Auth";
import Landing from "./pages/Landing";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Landing />} />
        <Route path="/auth" element={<Auth />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
