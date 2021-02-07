import logo from './logo.svg';
import React, { Component} from 'react';
import './App.css';
import paypal from './assets/paypal.png';
import bitcoin from './assets/bitcoin.png';
import bank from './assets/bank.png';
const axios = require('axios');

class PaymentsMethods extends Component {
  constructor(props) {
    super (props)
    this.state={
      paymentsMethods: [], 
      error: '',
      merchantId: props.match.params.id,
      amount: props.match.params.amount,
      paymentsMethodsList: [],
      currency: 'USD',
      recieveCurrency: 'USD'
    }
  } 

  //get all method for merchant
  componentDidMount = async () => {
    try {
      let response = await (await fetch(`http://localhost:8084/getTypes/${this.state.merchantId}`, {
        method: 'get',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
          'Access-Control-Allow-Methods': 'GET, POST, OPTIONS, PUT, DELETE',
          'Access-Control-Allow-Origin': '*'
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
      try {
        let response = await (await fetch('http://localhost:8084/CriptoPayment', {
            method: 'post',
            headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({
              amount: this.state.amount,
              currency: this.state.currency,
              recieveCurrency: this.state.recieveCurrency
            })
          })).json();
          const headers = {
            "x-requested-with": '*/*' ,
            'Content-Type': 'application/json',
            "Access-Control-Allow-Methods": `GET, POST, PATCH, PUT, DELETE, OPTIONS`,
            "Access-Control-Expose-Headers": 'Authorization',
            "Authorization": 'Bearer v3GexiQbkLfgWcdiB11b4t1s3FjRUFKbPcGDXhYi'
          }
         // console.log(response);
          if (response.status === "success"){
              if(response.redirect_url){
                let url = response.redirect_url;
                axios.post(
                  'https://cors-anywhere.herokuapp.com/' + url ,{},
                  {   headers: headers
                    
                  }
                ).then((response) => {
                    var response = response.data;
                    console.log(response)
                   // console.log(response.orders[0].payment_url)
                    window.open(response.payment_url,'_blank')
                  },
                  (error) => {
                  //  var status = error.response.status
                
                  }
                );
                //window.open(response.redirect_url,"_blank")
                // let response = await fetch(`${response.redirect_url}`, {
                //   method: 'post',
                //   headers: {
                //     'Accept': '*',
                //     'Content-Type': 'application/json',
                //     "Authorization": `Bearer MejhzV6eTUgWEcP5ms3iFNjBeM4Dqu5w4pdBfkVD`,
                //     "Access-Control-Allow-Origin": `*`,
                //     "Access-Control-Allow-Methods": `GET, POST, PATCH, PUT, DELETE, OPTIONS`,
                //     "Access-Control-Allow-Headers": `Origin, Content-Type, X-Auth-Token, Authorization`
                //   }
                // })

              }
              
          }else{
            alert("something went wrong ");
          }
        } catch (err) {
          this.setState({
            errors: err.toString()
          });
        }

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
            if (item === 'CRYPTO' ) {
              return(
                <div  className="item">
                  <div onClick={e => this.getMethod(e, item)}>
                    <div className="inline">
                      <img width="25px" height="25px" className="image" src={src} />
                    </div>
                    <div className="inline padding-bottom">
                      {item}
                    </div>
                  </div>
                  <div className="inline border" style={{width: '100%'}}>
                    <p>Price currency</p>
                    <select value={this.state.currency} onChange={e => this.setState({currency: e.target.value})}>
                      <option value="USD" selected={this.state.currency === "USD"}>USD</option>
                      <option value="EUR" selected={this.state.currency === "EUR"}>EUR</option>
                    </select>
                  </div>
                  <div className="inline border" style={{width: '100%'}}>
                    <p>Recieve currency</p>
                    <select value={this.state.recieveCurrency} onChange={e => this.setState({recieveCurrency: e.target.value})}>
                      <option value="USD" selected={this.state.recieveCurrency === "USD"}>USD</option>
                      <option value="EUR" selected={this.state.recieveCurrency === "EUR"}>EUR</option>
                    </select>
                  </div>
                </div>
              )
            } else {
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
             }

          })}
          </div>
        </header>
      </div>
    );

  }
  
}

export default PaymentsMethods;
