import React from 'react';
import GlobalStyle from './globalStyles';
import { BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import TickerHomePage from './pages/TickerHomePage';
import Navbar from './components/navbar/Navbar';

function App() {
  return (
      <Router>
        <GlobalStyle />
        <Navbar />
        <Routes>
          <Route exact path='/' element={<TickerHomePage />}/>
        </Routes>
      </Router>
  );
}

export default App;
