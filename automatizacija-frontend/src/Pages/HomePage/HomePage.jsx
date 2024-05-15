import React from 'react';
import BannerImage from '../../Components/Assets/background4.jpg';
import '../HomePage/HomePage.css';
import { Link, useNavigate } from "react-router-dom";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faAppleWhole } from '@fortawesome/free-solid-svg-icons';
import { faLeaf } from '@fortawesome/free-solid-svg-icons';
import { faHandHoldingHeart } from '@fortawesome/free-solid-svg-icons';

function HomePage() {

  const navigate = useNavigate();

  return (
    <div>
        <div className="home" style={{ backgroundImage: `linear-gradient( to bottom, rgba(85,85,85, 0.4) 0%, rgba(0, 0, 0, 0) 100% ), url(${BannerImage})` }}>
            <div className="headerContainer">
                <h1> PROIZVODNJA SOKOVA </h1>
                <p className='opis1'> Brz, lak i efikasan način poručivanja potpuno prirodnih sokova!</p>
                <Link to="/sokovi">
                    <button className='animated-button' onClick={(e) => {
                      e.preventDefault();
                      sessionStorage.getItem("jwtToken") ?
                      navigate('/sokovi') :
                      navigate('/logIn')
                    }}> ODABERI KOMBINACIJU </button>
                </Link>
            </div>
        </div>

        <div className="achievements">

        <div className="work">
          <i><FontAwesomeIcon icon={faAppleWhole} /></i>
          <p className="work-heading">ODABERI UKUS</p>
          <p className="work-text">Širok izbor različitih ukusa sokova, ceđenih od 100% organskog voća i povrća i kombinacije istih.</p>
        </div>

        <div className="work">
          <i ><FontAwesomeIcon icon={faLeaf} /></i>
          <p className="work-heading">Prirodno</p>
          <p className="work-text">Potpuno prirodno, bez dodatih šećera, boja, konzervanasa i emulgatora.</p>
        </div>
        
        <div className="work">
          <i><FontAwesomeIcon icon={faHandHoldingHeart} /></i>
          <p className="work-heading">Zdravo</p>
          <p className="work-text">Zdrav izvor vitamina i minerala, pogodan za svakog člana porodice bez obzira na uzrast.</p>
        </div>
      </div>
    </div>
  )
}

export default HomePage