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
      paymentsMethods: [], 
      error: '',
      merchantId: props.match.params.id,
      amount: props.match.params.amount,
      paymentsMethodsList: []
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
        paymentsMethods: response.paymentTypes,
      })
    } catch (err) {
      this.setState({
        errors: err.toString()
      });
    }
  }

  //on pay click
  getMethod = async (e, selectedPayment)  => {
    console.log(selectedPayment);
    if (selectedPayment === "BANK") {
      try {
      let response = await (await fetch('http://localhost:8084/PaymentBank', {
          method: 'get',
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            requestDTO: { id: this.state.id, amount: this.state.amount}
          })
        })).text();
        console.log(response);
      } catch (err) {
        this.setState({
          errors: err.toString()
        });
      }
    } else if (selectedPayment === "CRYPTO") {

    } else if (selectedPayment === "PAYPAL") {

    }
    
  }

  render () {
    return (
      <div className="App">
        <header className="App-header">
        <div className="main-container">
          <div><h4>Choose payment method</h4></div>
          {this.state.paymentsMethods && this.state.paymentsMethods.map(item => {
            let src = '';
            if (item === 'CRYPTO') {
              src = bitcoin;
            } else if (item === 'BANK') {
              src = bank;
            } else if (item === 'PAYPAL') {
              src = paypal;
            }
            return(
              <div className="item" onClick={e => this.getMethod(e, item)}>
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