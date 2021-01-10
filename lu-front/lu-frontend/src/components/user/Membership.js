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
    //get membership by id if activated = true
    //ako je null isto ostaviti pay Membership
    // skoloniti i napisati placeno ako je membership payed
  }

  payMembership = async (e) => {
    e.preventDefault();
    console.log("user email", User.email);
    try {
      let response = await (await fetch(`http://localhost:8081/membership/payBill/${User.username}`, {
        method: 'post',
        headers: {
          'Accept': 'application/json',
					'Content-Type': 'application/json'
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
              <p><label>Membership price: 1200</label></p>
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