import axios from 'axios';
import * as Path from '../constant/path'
import sunAxios from './sunAxios';

const callApi = (endpoint, method, body) => {
    return sunAxios({
        method,
        url: `${Path.SERVER_BASE_URL}${endpoint}`,
        data: body
    }).catch(err => {
        console.log(err);
    });
}

export default callApi;


