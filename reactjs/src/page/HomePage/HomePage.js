import React from 'react';
import Menu from '../../component/Menu'
import Navigation from '../../component/Navigation';

const HomePage = (props) => {

    const btnRemoveClick = () => {
        localStorage.removeItem('token');
        alert("Remove token success");
        window.location = window.location.href
    }

    return (
        <div>
            <Navigation/>
            HomePage
            <hr/>
            <br/>
            <button onClick={btnRemoveClick}>Remove token</button>
        </div>
    );
}

export default HomePage;