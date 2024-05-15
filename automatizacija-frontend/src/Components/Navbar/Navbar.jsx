import React, { useState, useEffect } from 'react';
import '../Navbar/Navbar.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faLemon } from '@fortawesome/free-solid-svg-icons';
import { Link, useNavigate} from 'react-router-dom';

function Navbar() {
    const token = sessionStorage.getItem("jwtToken");
    const navigate = useNavigate();
    const [isActive, setIsActive] = useState(false);
    const [showBurgerMenu, setShowBurgerMenu] = useState(window.innerWidth < 768);

    useEffect(() => {
        const handleResize = () => {
            if (window.innerWidth < 768) {
                setShowBurgerMenu(true);
            } else {
                setShowBurgerMenu(false);
            }
            if (window.innerWidth >= 768) {
                setIsActive(false);
            }
        };

        window.addEventListener('resize', handleResize);

        return () => {
            window.removeEventListener('resize', handleResize);
        };
    }, []);

    function handleClick() {
        setIsActive(prevState => !prevState);
    }

    function handleLogOut() {
        sessionStorage.clear();
        navigate("/");
    }

    return (
        <nav className='navbar-container'>
            <div className='logo'>
                Proizvodnja Sokova
                <FontAwesomeIcon icon={faLemon} className='icon'/>
            </div>

            {((isActive && showBurgerMenu) || !showBurgerMenu)  && !token && (
                <div className='navbar-links'>
                    <Link to='/'>
                        Home
                    </Link>
                    <Link to='/logIn' className= 'link' style={{border:'2px solid #FDDA0D', padding:'5px', color:'#FDDA0D', borderRadius: '10px',}}>
                        Log In
                    </Link>
                </div>
            )}

            {((isActive && showBurgerMenu) || !showBurgerMenu)  && token && (
                <div className='navbar-links'>
                    <Link to='/'>
                        Home
                    </Link>
                    { sessionStorage.getItem("korisnik") === "Admin" && (
                        <Link to='/procesProizvodnjeAdmin'>
                        Proizvodnje
                        </Link>
                    )}
                    { sessionStorage.getItem("korisnik") === "Kupac" && (
                        <Link to='/procesProizvodnjeKorisnik'>
                        Proizvodnje
                    </Link>
                    )}
                    <button className='btn1' onClick={handleLogOut}>
                        Log Out
                    </button>
                </div>
            )}

            {showBurgerMenu && (
                <div className='menu-icon' onClick={handleClick}>
                    <div>☰</div>
                </div>
            )}

        </nav>
    );
}

export default Navbar;