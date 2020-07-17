import * as Types from '../constant/ActionType';

var initState = [];

const bookReducer = (state = initState, action) => {
    switch (action.type) {
        case Types.GET_BOOK:
            return [...action.payload.books];
        case Types.ADD_BOOK:
            state.push(action.payload.book);
            return [...state];
        case Types.UPDATE_BOOK:
            var index = findIndex(state, action.payload.book.id);
            state[index] = action.payload.book;
            return [...state];
        case Types.DELETE_BOOK:
            var index = findIndex(state, action.payload.book.id);
            state.splice(index, 1);
            return [...state];
        default: return [...state];
    }
};

var findIndex = (books, id) => {
    var result = -1;
    books.forEach((book, index) => {
        if (book.id === id) {
            result = index;
        }
    });
    return result;
}

export default bookReducer;