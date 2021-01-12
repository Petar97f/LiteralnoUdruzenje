import logo from './logo.svg';
import React, { Component} from 'react';
import './App.css';


class ConfirmationPaypal extends Component {
  constructor(props) {
    super (props)
    let params = new window.URLSearchParams(window.location.search)
    let paymentId = params.get('paymentId')
    let token = params.get('token')
    let PayerID = params.get('PayerID')
    this.state={
      paymentId: paymentId,
      token: token,
      PayerID: PayerID
  }} 
    componentDidMount(){
      console.log(this.state.paymentId);
      console.log(this.state.token);
      console.log(this.state.PayerID);
    }
      confirm = async ()=>{
        try {
         
          let response = await (await fetch(`http://localhost:8085/complete/payment?paymentId=${this.state.paymentId}&token=${this.state.token}&PayerID=${this.state.PayerID}`, {
              method: 'get',
              headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
              },
              
            })).json();
            alert('Uspesna preplata');
            console.log(response);
          } catch (err) {
            this.setState({
              errors: err.toString()
            });
          }
      }
      render (){
          return ( 
            <div className="App">
            <header className="App-header">
            <div className="main-container">
              <div><h4>Confirm paypal payment</h4></div>
                <button className = "btn btn-primary" onClick = {this.confirm } >
                  Confirm
                </button>
              </div>
            </header>
          </div>
      )
      }


    }

    export default ConfirmationPaypal;