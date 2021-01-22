import logo from './logo.svg';
import React, { Component} from 'react';
import './App.css';
import paypal from './assets/paypal.png';
import bitcoin from './assets/bitcoin.png';
import bank from './assets/bank.png';


class RegisterSeller extends Component {
  constructor(props) {
    super (props)
    this.state={
      paymentsMethods: ['paypal'], 
      error: '',
    }
  } 

  onChange = (value) => {
    if (!this.state.paymentsMethods.includes(value)) {
      this.setState({
        paymentsMethods: this.state.paymentsMethods.concat([value])
      })
    } else {
      this.setState({
        paymentsMethods: this.state.paymentsMethods.filter(item => item !== value)
      })
    }
  }

  render () {
    return (
      <div>
        <div className="App">
        <header className="App-header">
        <div className="main-container">
          <div><h4>Register as Seller</h4></div>
        </div>
          <form className="mt-3">
            <p className="font-12 m-0 p-0 align-left">Name:</p>
            <input className="font-12" type="text" name="name" />
            <p className="font-12 m-0 p-0 align-left">Lastname:</p>
            <input className="font-12" type="text" name="lastname" />
            <div className="mt-2">
              <div>
                <input type="checkbox" value="paypal" checked={this.state.paymentsMethods.includes("paypal") ? true : false} name="paypal" onChange={e => this.onChange(e.target.value)} />
                <img className="ml-2" src={paypal} width="32px" height="32px"/>
              </div>
              <div>
                <input type="checkbox" value="bitcoin" checked={this.state.paymentsMethods.includes("bitcoin") ? true : false} onChange={e => this.onChange(e.target.value)}  />
                <img className="ml-2" src={bitcoin} width="32px" height="32px" />
              </div>
              <div>
                <input type="checkbox"  value="bank" checked={this.state.paymentsMethods.includes("bank") ? true: false} onChange={e => this.onChange(e.target.value)}  />
                <img className="ml-2" src={bank} width="32px" height="32px"/>
              </div>
            </div>
          </form>
          <button className="btn btn-primary mt-3" type="submit">Register</button>
        </header>
        </div>
      
      </div>
    );
  }

}

export default RegisterSeller;