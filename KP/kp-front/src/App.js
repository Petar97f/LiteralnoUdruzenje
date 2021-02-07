import logo from './logo.svg';
import React, { Component} from 'react';
import './App.css';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";
import PaymentsMethods from './PaymentMethods';
import ConfirmationPaypal from './ConfirmationPaypal';
import RegisterSeller from './RegisterSeller';
import StatusSuccess from './StatusSuccess';
import StatusFailed from './StatusFailed';
import StatusError from './StatusError';

class App extends Component {
  constructor(props) {
    super (props)
    
  } 


  render () {
    return (
      <Router>
        <div className="App">
        <Switch>
          <Route exact path="/" /> 
          <Route exact path="/:id/:amount" component={PaymentsMethods} />
          <Route path="/confirmationPaypal" component={ConfirmationPaypal} />
          <Route path="/registration" component={RegisterSeller} />
          <Route path="/success" component={StatusSuccess} />
          <Route path="/failed" component={StatusFailed} />
          <Route path="/error" component={StatusError} />
        </Switch>
        </div>
      </Router>
    );

  }
  
}

export default App;
