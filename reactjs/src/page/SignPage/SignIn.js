import React, { useState } from 'react';
import Axios from 'axios';
import * as Path from '../../constant/path';
import './login.css';

const SignIn = () => {

    const [account, setAccount] = useState({
        username: '',
        password: ''
    });

    const btnSubmitClick = () => {
        const url = Path.SERVER_BASE_URL + '/signin';
        Axios.post(url, account)
            .then(res => {
                localStorage.setItem('token', 'Bearer ' + res.data.token);

                window.location = window.location.href;
                alert("Login as: " + res.data.username);


            })
            .catch(error => console.log(error));
    }

    const change = (event) => {
        setAccount({
            ...account,
            [event.target.name]: event.target.value
        })
    }

    return (
        <div className="container-fluid">
            <div className="row">
                <div className="col-md-4 offset-md-4">
                    <a href="/">
                        <legend>
                            Welcome to BookStore
        </legend>
                    </a>
                    <div className="card">
                        {/* <div class="card-header">
              <div class="title">
                  <i class="fa fa-sign-in" aria-hidden="true"></i>
                  Login
              </div>
          </div> */}
                        <div className="card-body" id="form">
                            <div className="mb-3">
                                <div className="input-group">
                                    <div className="input-group-prepend">
                                        <span className="input-group-text">
                                            <i className="fa fa-user" aria-hidden="true" />
                                        </span>
                                    </div>
                                    <input type="text" name="username" value={account.username} onChange={change} className="form-control" placeholder="Username/Email" required />
                                </div>
                            </div>
                            <div className="mb-3">
                                <div className="input-group">
                                    <div className="input-group-prepend">
                                        <span className="input-group-text">
                                            <i className="fa fa-lock" aria-hidden="true" />
                                        </span>
                                    </div>
                                    <input type="password" name="password" value={account.password} onChange={change} className="form-control" placeholder="Password" required />
                                </div>
                            </div>
                            <button name="submit" onClick={btnSubmitClick} className="btn btn-primary btn-block" >Sign in</button>

                            <br />
                            <div className="row" id="listlink">
                                <div className="col-md-6">
                                    <a href="#" data-toggle="modal" data-target="#forgotPassword">Forgot Password?</a>
                                    {/* Modal */}
                                    <div className="modal fade" id="forgotPassword" tabIndex={-1} role="dialog" aria-labelledby="forgotPasswordLabel" aria-hidden="true">
                                        <div className="modal-dialog">
                                            <div className="modal-content">
                                                <div className="modal-header">
                                                    <h5 className="modal-title">Forgot Password</h5>
                                                    <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">×</span>
                                                    </button>
                                                </div>
                                                <div className="modal-body">
                                                    <div className="mb-3">
                                                        <div className="input-group">
                                                            <div className="input-group-prepend">
                                                                <span className="input-group-text">
                                                                    <i className="fa fa-envelope" aria-hidden="true" />
                                                                </span>
                                                            </div>
                                                            <input type="email" className="form-control" placeholder="Email" required />
                                                        </div>
                                                    </div>
                                                </div>
                                                <div className="modal-footer">
                                                    <button type="button" className="btn btn-secondary" data-dismiss="modal">Close
                      </button>
                                                    <button type="button" className="btn btn-primary" data-dismiss="modal" id="btn">Send
                      </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div className="col-md-6">
                                    <a href="#" data-toggle="modal" data-target="#registry">Create an account.</a>
                                    <form method="post">
                                        {/* Modal */}
                                        <div className="modal fade" id="registry" tabIndex={-1} role="dialog" aria-labelledby="registryLabel" aria-hidden="true">
                                            <div className="modal-dialog">
                                                <div className="modal-content">
                                                    <div className="modal-header">
                                                        <h5 className="modal-title" id="registryLabel">Your information</h5>
                                                        <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true">×</span>
                                                        </button>
                                                    </div>
                                                    <div className="modal-body">
                                                        <div className="form-group row" style={{ paddingTop: '5%' }}>
                                                            <label className="col-sm-3 col-form-label" style={{ textAlign: 'left', fontWeight: 'bold' }}>
                                                                <i className="fa fa-user-o" aria-hidden="true" />
                            Name:
                          </label>
                                                            <div className="col-sm-9">
                                                                <input type="text" className="form-control" name="registry_name" required />
                                                            </div>
                                                        </div>
                                                        <div className="form-group row" style={{ paddingTop: '5%' }}>
                                                            <label className="col-sm-3 col-form-label" style={{ textAlign: 'left', fontWeight: 'bold' }}>
                                                                <i className="fa fa-address-book-o" aria-hidden="true" />
                            Address:
                          </label>
                                                            <div className="col-sm-9">
                                                                <input type="text" className="form-control" name="registry_address" required />
                                                            </div>
                                                        </div>
                                                        <div className="form-group row" style={{ paddingTop: '5%' }}>
                                                            <label className="col-sm-3 col-form-label" style={{ textAlign: 'left', fontWeight: 'bold' }}>
                                                                <i className="fa fa-phone" aria-hidden="true" />
                            Tel:
                          </label>
                                                            <div className="col-sm-9">
                                                                <input type="text" className="form-control" name="registry_tel" required />
                                                            </div>
                                                        </div>
                                                        <div className="form-group row" style={{ paddingTop: '5%' }}>
                                                            <label className="col-sm-3 col-form-label" style={{ textAlign: 'left', fontWeight: 'bold' }}>
                                                                <i className="fa fa-envelope-o" aria-hidden="true" />
                            Email:
                          </label>
                                                            <div className="col-sm-9">
                                                                <input type="email" className="form-control" name="registry_email" required />
                                                            </div>
                                                        </div>
                                                        <div className="form-group row" style={{ paddingTop: '5%' }}>
                                                            <label className="col-sm-3 col-form-label" style={{ textAlign: 'left', fontWeight: 'bold' }}>
                                                                <i className="fa fa-lock" aria-hidden="true" />
                            Password:
                          </label>
                                                            <div className="col-sm-9">
                                                                <input id="registry_password" type="password" className="form-control" name="registry_password" required />
                                                            </div>
                                                        </div>
                                                        <div className="form-group row" style={{ paddingTop: '5%' }}>
                                                            <label className="col-sm-3 col-form-label" style={{ textAlign: 'left', fontWeight: 'bold' }}>
                                                                Confirm password:
                          </label>
                                                            <div className="col-sm-9">
                                                                <input onchange="checkConfirmPassword()" id="registry_confirmPassword" type="password" className="form-control" name="registry_confirmPassword" required />
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div className="modal-footer" id="registry_modal_footer">
                                                        <button type="button" className="btn btn-secondary" data-dismiss="modal">Close
                        </button>
                                                        <button type="submit" className="btn btn-primary" id="btnSubmitRegistry" formAction="/registry" disabled>Submit
                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div >
        </div >
    );
}

export default SignIn;
