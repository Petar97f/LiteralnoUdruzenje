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
      seller: {},
      bank: "1"
    }
  } 

  componentDidMount = async () => {

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

  submit = async (e) => {
    console.log("submit")
    e.preventDefault();
    try {
      let response = await (await fetch(`http://localhost:8084/addMerchant`, {
        method: 'post',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
          //'Authorization': 'Bearer ' + localStorage.getItem("token"),
          'X-Auth-Token': localStorage.getItem("token")
        }, 
        body: JSON.stringify({
          name: this.state.seller.name,
          address: this.state.seller.address,
          password: this.state.seller.password,
          phoneNumber: this.state.seller.phoneNumber,
          paymentTypes: this.state.paymentsMethods.join(','),
          bankId: this.state.bank
        })
      })).json();
      if (response.status == 'fail') {
        alert(response.message);
      } else {
        alert(response.message);
      }
    } catch (err) {
      this.setState({
        errors: err.toString()
      });
    }
  }
  onChangeField = (e, field) => {
    let seller = {...this.state.seller};
    seller[field]= e.target.value;
    this.setState({
      seller: seller
    })
  }
  render () {
    return (
      <div>
        <div className="App">
        <header className="App-header">
        <div className="main-container">
          <div><h4>Register as Seller</h4></div>
        </div>
          <form className="mt-3 needs-validation" role="form"  onSubmit={this.submit}>
            <p className="font-12 m-0 p-0 align-left">Name:</p>
            <input className="font-12" type="text" name="name" required onChange={e => this.onChangeField(e, 'name')}/>
            <p className="font-12 m-0 p-0 align-left">Password:</p>
            <input className="font-12" type="password" name="password" required  onChange={e => this.onChangeField(e, 'password')}/>
            <p className="font-12 m-0 p-0 align-left">Address:</p>
            <input className="font-12" type="text" name="address" required  onChange={e => this.onChangeField(e, 'address')}/>
            <p className="font-12 m-0 p-0 align-left">Phone number:</p>
            <input className="font-12" type="text" name="phone" required  onChange={e => this.onChangeField(e, 'phone')}/>
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
              <div>
                <p className="font-12 m-0 p-0 align-left">Select Bank</p>
                <select className="font-12 m-0 p-0 align-left w-100" value={this.state.bank} onChange={e => this.setState({ bank: e.target.value})} >
                  <option value="1">Bank 1</option>
                  <option value="2">Bank 2</option>
                </select>
              </div>
            </div>
            <button className="btn btn-primary mt-3" type="submit">Register</button>
          </form>
      
        </header>
        </div>
      </div>
    );
  }

}

export default RegisterSeller;