import React from 'react';
import { Link } from 'react-router-dom';
const Menu = (props) => {
    return (
        <div>
            Menu
            |<Link to="/">Home</Link>
            |<Link to="/book">Book</Link>
            |<Link to="/signin">Login</Link>
            |<Link to="/signup">Sign up</Link>
        </div>
    );
}

export default Menu;