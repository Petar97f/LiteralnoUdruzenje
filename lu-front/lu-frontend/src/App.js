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
		let user = jwt(response);
		console.log(user)
		if (user) {
			User.isLoggedIn = true;
			User.name = user.sub.name;
			User.username = user.sub.username;
			User.surname = user.sub.surname;
			User.email = user.sub.email;
			User.city= user.sub.city;
			User.country = user.sub.country;
			User.role = user.roles[0].authority;
      this.props.history.push('/user');
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
