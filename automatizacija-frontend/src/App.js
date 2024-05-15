import './App.css';
import { BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import Navbar from './Components/Navbar/Navbar';
import HomePage from './Pages/HomePage/HomePage';
import LoginPage from './Pages/LoginPage/LoginPage';
import RegisterPage from './Pages/RegisterPage/RegisterPage';
import { Footer } from './Components/Footer/Footer';
import SokoviPage from './Pages/SokoviPage/SokoviPage';
import ProizvodnjaPage from './Pages/ProizvodnjaPage/ProizvodnjaPage';
import ProcesProizvodnjeKorisnikPage from './Pages/ProcesProizvodnjeKorisnikPage/ProcesProizvodnjeKorisnikPage';
import ProcesProizvodnjeAdminPage from './Pages/ProcesProizvodnjeAdimPage/ProcesProizvodnjeAdminPage';

function App() {
  return (
    <div className="App">
      <Router>
        <div>
          <Routes>
            <Route path='/' element={<><Navbar/><HomePage/><Footer/></>}></Route>
            <Route path='/logIn' element={<><Navbar/><LoginPage/><Footer/></>}></Route>
            <Route path='/register' element={<><Navbar/><RegisterPage/><Footer/></>}></Route>
            <Route path='/sokovi' element={<><Navbar/><SokoviPage/><Footer/></>}></Route>
            <Route path='/proizvodnja/:id/:vrsta' element={<><Navbar/><ProizvodnjaPage/><Footer/></>}></Route>
            <Route path='/procesProizvodnjeKorisnik' element={<><Navbar/><ProcesProizvodnjeKorisnikPage/><Footer/></>}></Route>
            <Route path='/procesProizvodnjeAdmin' element={<><Navbar/><ProcesProizvodnjeAdminPage/><Footer/></>}></Route>
          </Routes>
        </div>
      </Router>
    </div>
  );
}

export default App;
