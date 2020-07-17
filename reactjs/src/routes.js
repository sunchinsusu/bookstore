import React from 'react';
import HomePage from './page/HomePage/HomePage';
import BookPage from './page/BookPage/BookPage';
import SignIn from './page/SignPage/SignIn';
import SignUp from './page/SignPage/SignUp'
import BookDetail from './page/BookPage/BookDetail';

const requireAuthen = (component)=> {
    const token = localStorage.token;
    if(token != null ) return component
    return <SignIn/>
}

const routes = [
    {
        path: '/',
        main: () => requireAuthen(<HomePage/>)
    },
    {
        path: '/book',
        main: () => requireAuthen(<BookPage/>)
    },
    {
        path: '/book/:id',
        main: () => requireAuthen(<BookDetail/>)
    },
    {
        path: '/signin',
        main: () => <SignIn/>
    },
    {
        path: '/signup',
        main: () => <SignUp/>
    }
]

export default routes