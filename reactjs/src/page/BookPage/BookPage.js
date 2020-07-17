import React, { useState, useEffect } from 'react';
import Menu from '../../component/Menu';
import { useSelector, useDispatch } from 'react-redux';
import callApi from '../../util/apiCaller';
import * as Type from '../../constant/ActionType';
import Navigation from '../../component/Navigation';
import * as Path from '../../constant/path';
import { Link } from 'react-router-dom';

const BookPage = (props) => {
    const index = 1;
    const dispatch = useDispatch();
    const books = useSelector(state => state.bookReducer);

    useEffect(() => {
        callApi('/book', 'GET', null)
            .then(res => {
                dispatch({
                    type: Type.GET_BOOK,
                    payload: {
                        books: res.data
                    }
                })
            });
    }, books)


    return (
        <div>
            <Navigation />
            <br/>
            <div className="container">
                <table className="table table-bordered table-striped">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Image</th>
                            <th>Name</th>
                            <th>Author</th>
                            <th>Description</th>
                            <th>Sale Price</th>
                            <th>Purchase Price</th>
                            <th>Sale Off</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            books.map((book) => {
                                return (
                                    <tr>
                                        <td>{book.id}</td>
                                        <td><img src={Path.SERVER_BASE_URL + book.url} style={{width:'40px', height:'40px'}}/></td>
                                        <td><Link to={"/book/"+book.id}>{book.name}</Link></td>
                                        <td>{book.author}</td>
                                        <td>{book.des}</td>
                                        <td>{book.salePrice} $</td>
                                        <td>{book.purchasePrice} $</td>
                                        <td>{book.saleOff} $</td>
                                        <td><i class="fa fa-times-circle" aria-hidden="true"></i></td>
                                    </tr>
                                )
                            })
                        }
                    </tbody>
                </table>
            </div>

        </div>
    );
}

export default BookPage;