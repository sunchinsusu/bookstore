import React, { useState } from 'react';
import { useParams } from 'react-router-dom';
import { useSelector } from 'react-redux';


const BookDetail = (props) => {
    const id = useParams().id;
    var thisBook = {};
    const books = useSelector(state => state.bookReducer);
    books.map((book) =>{
        if(book.id == id) thisBook = book;
    })
    const {book, setBook} = useState(thisBook);

    return (
        <div>
            {/* Id: {props.match.params.id} */}
            {
                console.log(book)
            }
        </div>
    );
}

export default BookDetail;