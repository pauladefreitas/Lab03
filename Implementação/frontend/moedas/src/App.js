import DoarMoedas from './pages/doarMoedas/DoarMoedas';
import VizualizarAluno from './pages/crudAluno/vizualizarAluno';
import CadastrarAluno from './pages/crudAluno/cadastrarAluno';
import VizualizarEmpresa from './pages/crudEmpresa/vizualizarEmpresa';
import CadastrarEmpresa from './pages/crudEmpresa/cadastrarEmpresa'

import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';

function App() {
  return (
    <div className="App">
        <Router>
          <Routes>
            <Route path="/" element={<DoarMoedas />} />
            <Route path="/vizualizarAluno" element={<VizualizarAluno />} />
            <Route path="/cadastrarAluno" element={<CadastrarAluno />} />
            <Route path="/vizualizarEmpresa" element={<VizualizarEmpresa />} />
            <Route path="/cadastrarEmpresa" element={<CadastrarEmpresa />} />

          </Routes>
        </Router>
    </div>
  );
}

export default App;