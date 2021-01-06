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
          <Route exact path="/:merchantId" component={PaymentsMethods} />
        </Switch>
        </div>
      </Router>
    );

  }
  
}

export default App;
