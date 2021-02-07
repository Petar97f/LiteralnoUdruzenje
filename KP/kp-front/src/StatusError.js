import logo from './logo.svg';
import React, { Component} from 'react';
import './App.css';


class StatusError extends Component {
  constructor(props) {
    super (props)
    this.state={
    }
  } 


  render () {
    return (
      <div>
        <div className="App">
        <header className="App-header">
        <div className="main-container">
          <div><h4>Payment Error</h4></div>
        </div>
      
        </header>
        </div>
      </div>
    );
  }
}


export default StatusError;