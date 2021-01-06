import logo from './logo.svg';
import React, { Component} from 'react';
import './App.css';
import paypal from './assets/paypal.png';
import bitcoin from './assets/bitcoin.png';
import bank from './assets/bank.png';

class PaymentsMethods extends Component {
  constructor(props) {
    super (props)
    this.state={
      paymentsMethods: ['paypal', 'bitcoin', 'bank'], 
      error: '',
      merchantId: props.match.params.merchantId,
    }
  } 

  //get all method for merchant
  componentDidMount = async () => {
    try {
      let response = await (await fetch(`http://localhost:8084/getTypes/${this.state.merchantId}`, {
        method: 'get',
        headers: {
          'Accept': 'application/json',
					'Content-Type': 'application/json'
        },
      })).json();
      this.setState({
        paymentsMethods: response,
      })
    } catch (err) {
      this.setState({
        errors: err.toString()
      });
    }
  }

  //on pay click
  getMethod = async (e)  => {
    console.log(e.target.value);
    try {
      let response = await (await fetch('http://localhost:8081/', {
        method: 'get',
        headers: {
          'Accept': 'application/json',
					'Content-Type': 'application/json'
        },
        body: JSON.stringify({
      
        })
      })).json();
  
    } catch (err) {
      this.setState({
        errors: err.toString()
      });
    }
  }

  render () {
    return (
      <div className="App">
        <header className="App-header">
        <div className="main-container">
          <div><h4>Choose payment method</h4></div>
          {this.state.paymentsMethods.map(item => {
            let src = '';
            if (item === 'bitcoin') {
              src = bitcoin;
            } else if (item === 'bank') {
              src = bank;
            } else if (item === 'paypal') {
              src = paypal;
            }
            return(
              <div className="item" onClick={e => this.getMethod(e)}>
                <div className="inline">
                  <img width="25px" height="25px" className="image" src={src} />
                </div>
                <div className="inline padding-bottom">
                  {item}
                </div>
              </div>
            )
          })}
          </div>
        </header>
      </div>
    );

  }
  
}

export default PaymentsMethods;