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
      console.log('tu sam');
      console.log(this.state.merchantId);
      console.log(this.state.amount);
      let requestDTO = {};
      requestDTO.id = this.state.merchantId;
      requestDTO.amount = this.state.amount;
      try {
      let response = await (await fetch('http://localhost:8084/PaymentBank', {
          method: 'post',
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            id: this.state.merchantId, amount: this.state.amount
          })
        })).json();
        console.log(response)
        if (response.status === 'fail') {
          alert('Something went wrong');
        } else if (response.status === "success") {
          if (response.data) {
            window.close();
            window.open(
                response.data,
                '_blank'
            );
          }
        }
      } catch (err) {
        this.setState({
          errors: err.toString()
        });
      }
    } else if (selectedPayment === "CRYPTO") {

    } else if (selectedPayment === "PAYPAL") {
      try {
        let response = await (await fetch('http://localhost:8084/PaymentPaypal', {
            method: 'post',
            headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                amount: this.state.amount
            })
          })).json();
          console.log(response);
          if (response.status === "success"){
              if(response.redirect_url){
                window.open(response.redirect_url,"_blank")
              }
          }else{
            alert("something went wrong ");
          }
        } catch (err) {
          this.setState({
            errors: err.toString()
          });
        }
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
