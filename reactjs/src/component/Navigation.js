import React from 'react';

const Navigation = () => {

    const btnRemoveClick = () => {
        localStorage.removeItem('token');
        alert("Remove token success");
        window.location = window.location.href
    }

    return (
        <nav className="navbar navbar-expand-lg navbar" style={{ backgroundColor: '#0C2659' }}>
            <div className="container">
                <a className="navbar-brand dropdown" style={{ fontFamily: 'cursive', color: 'white' }} href="/home">
                    <i className="fa fa-home" aria-hidden="true" />
                    Admin
                </a>
                <div className="collapse navbar-collapse" id="navbarResponsive">
                    <ul className="navbar-nav ml-auto">
                        <li className="nav-item">
                            <a className="nav-link" href="/account" style={{ fontFamily: 'cursive', color: 'white' }}>
                                <i className="fa fa-user" aria-hidden="true" />
                                Account
                            </a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="/book" style={{ fontFamily: 'cursive', color: 'white' }}>
                                <i class="fa fa-book" aria-hidden="true"></i>
                                Book
                            </a>
                        </li>
                        <li className="nav-item" onClick={btnRemoveClick}>
                            <a className="nav-link" href="#" style={{ fontFamily: 'cursive', color: 'white' }}>
                                <i class="fa fa-sign-in" aria-hidden="true"></i>
                                Logout
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

    );
}

export default Navigation;