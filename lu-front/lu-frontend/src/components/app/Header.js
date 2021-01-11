import React, { Component} from 'react'
import Login from './Login';
import Register from './Register';
import RegisterReaderModal from './RegisterReaderModal';
import { Link } from 'react-router-dom';
import User from '.././user/User';

class Header extends Component {
  constructor (props) {
    super(props);
    this.state = {
      showLogin: false,
      showRegister: false,
      isLoggedIn: false,
      showRegisterReader: false
    }
  }

  onLogin = (e) => {
    e.preventDefault();
    this.setState({
      showLogin: true
    })
  } 

  onRegister = (e) => {
    e.preventDefault();
    this.setState({
      showRegister: true
    })
  } 

  onRegisterWritter = (e) => {
    e.preventDefault();
    this.setState({
      showRegisterWritter: true
    })
  }

  onLogout = (e) => {
    e.preventDefault();
    this.setState({
      isLoggedIn: false
    })
    sessionStorage.removeItem("jwt");
  }

  onRegisterReader = (e) => {
    e.preventDefault();
    this.setState({
      showRegisterReader: true
    })
  }

  render () {
    if (!User.isLoggedIn) {
      return (
        <div className="header fixed-top w-100">
          <div className="d-flex flex-row justify-content-between h-100 ">
            <div className="d-flex flex-row justify-content-start align-self-center">
              <Link to={`/`}  className="ml-3" >Literalno Udruzenje</Link>
            </div>
            <div className="d-flex flex-row justify-content-end align-self-center">
              <Link to={`!#`} className="text-decoration-none mr-5" onClick={this.onLogin}>Login</Link>
              <Link to={`!#`} className="text-decoration-none mr-5" onClick={this.onRegister}>Register as Writter</Link>
              <Link to={`!#`} className="text-decoration-none mr-5" onClick={this.onRegisterReader}>Register as Reader</Link>
            </div>
          </div>
          {this.state.showLogin && <Login show={this.state.showLogin} onClose={e => this.setState({showLogin: false})} onRegister={e => this.setState({showRegister: true, showLogin: false})}/>}
          {this.state.showRegister && <Register show={this.state.showRegister} onClose={e => this.setState({showRegister: false})} onLogin={e => this.setState({showRegister: false, showLogin: true})}/>}
          {this.state.showRegisterReader && <RegisterReaderModal show={this.state.showRegisterReader} onClose={e => this.setState({showRegisterReader: false})} onLogin={e => this.setState({showRegisterReader: false, showLogin: true})}/>}
        </div>
      );
    } else {
      return (
        <div className="header fixed-top w-100">
          <div className="d-flex flex-row justify-content-between h-100 ">
            <div className="d-flex flex-row justify-content-start align-self-center">
              <h4 href="#" className="ml-3" >User: {User.name}</h4>
            </div>
            <div className="d-flex flex-row justify-content-end align-self-center">
              {User.role === "ROLE_WRITER" && <Link to={`/membership`} className="text-decoration-none mr-5" >Membership</Link>}
              <Link to={`/user`} className="text-decoration-none mr-5" >Home</Link>
              <Link to={`/about`} className="text-decoration-none mr-5" >About</Link>
              <Link to={`!#`} className="text-decoration-none mr-5" onClick={this.onLogout}>Logout</Link>
            </div>
          </div>
        </div>
      );
    }
  }
  
}

export default Header;