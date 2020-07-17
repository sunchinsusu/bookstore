import {createStore, combineReducers} from 'redux';
import bookReducer from './bookReducer';

const rootReducer = combineReducers({
    bookReducer
})

export const store = createStore(rootReducer);