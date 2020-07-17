import Axios from 'axios';


const init = Axios.create({
    headers: { 'Authorization': localStorage.token }
});

export default init;