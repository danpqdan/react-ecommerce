import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';
import Navbar from "./components/NavBar";
import Carrinho from './pages/Carrinho';
import Home from './pages/Home';
import Produtos from './pages/Produtos';

function App() {
  return (
    <Router>
      <div className="App">
        <header className="App-header">
          <div>
            {/* Navbar sempre visível */}
            <Navbar />

          </div>

        </header>
        {/* Definindo as rotas */}
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/about" element={<Produtos />} />
          <Route path="/products" element={<Carrinho />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
