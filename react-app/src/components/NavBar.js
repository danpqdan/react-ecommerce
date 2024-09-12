import React from 'react';
import { Link } from 'react-router-dom';
import logo from '../logo.svg';


import "./CSS/NavBarCss.css"

function Navbar() {
    return (
        <>
            <nav>
                <img src={logo} className="App-logo" alt="logo" />

                <ul>
                    <li>
                        <Link to="/">Home</Link>
                    </li>
                    <li>
                        <Link to="/about">Sobre</Link>
                    </li>
                    <li>
                        <Link to="/products">Produtos</Link>
                    </li>
                </ul>
            </nav>
        </>

    );
}

export default Navbar;
