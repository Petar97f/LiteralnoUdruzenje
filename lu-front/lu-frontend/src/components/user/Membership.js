import React, { Component} from 'react';
import { Form } from 'react-bootstrap';
import User from '.././user/User';

class Membership extends Component {
	constructor(props) {
		super(props);
		this.state = {
      error: ''
    }
  }

  componentDidMount () { 
  
  }

  payMembership = async (e) => {
    e.preventDefault();
    console.log("user email", User.email);
    try {
      let response = await (await fetch(`http://localhost:8081/payBill/${User.username}`, {
        method: 'post',
        headers: {
          'Accept': 'application/json',
					'Content-Type': 'application/json',
					'Access-Control-Allow-Origin': '*',
        }
      })).json();
      console.log(response)
      if (response.status == 'fail') {
        alert('Something went wrong');
      } else {
        alert('ok');
      }
    } catch (err) {
      this.setState({
        errors: err.toString()
      });
    }
  }

  render () {
    return (
      <div>
        <div className="margin-top-page">
          <div className="d-flex flex-row mt-2 ml-3">
            <div className="p-3" >
              <p><label>Membership price: </label></p>
              <br/>
              <button className="btn btn-primary" onClick={this.payMembership}>Pay Membership</button>
            </div>
          </div>
        </div>
      </div>
    )
  }
}


export default Membership;