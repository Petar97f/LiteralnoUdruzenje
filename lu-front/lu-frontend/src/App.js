import React, { Component} from 'react';
import { withRouter } from 'react-router-dom';
import { observer } from 'mobx-react';
import AppRoutes from './AppRoutes';
import Header from './components/app/Header';
import Footer from './components/app/Footer';
import './App.scss';
import User from './components/user/User';
import jwt from 'jwt-decode' 

class App extends Component {
  constructor (props) {
    super(props);
    this.state = {
      ready: false
    };
  }

  componentDidMount () {
    let response = localStorage.getItem("token");
    if (response && response !== "fail"){
      let token = jwt(response);
      if (token && token.user && token !== "fail") {
        User.isLoggedIn = true;
        User.name = token.user.name;
        User.username = token.user.username;
        User.surname = token.user.surname;
        User.email = token.user.email;
        User.city= token.user.city;
        User.country = token.user.country;
        User.role = token.roles[0].authority;
        this.props.history.push('/user');
      } else {
        this.props.history.push('/');
      }
    } else {
      this.props.history.push('/');
    }
	
  }
  
  render () {
    return (
      <div className="App">
        <Header />
        <AppRoutes />
        <Footer />
      </div>
    );
  }
  
}

export default observer(withRouter(App));
