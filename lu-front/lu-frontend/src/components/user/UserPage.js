import React, { Component, Fragment } from 'react';
import User from '.././user/User';

class UserPage extends Component {
  constructor(props) {
    super(props);
    this.state = {
      merchatId: '1234'
    }
  }

  componentDidMount () {
    console.log(User)
  }
  onBuy = async () => {
    //now we need to redirect on new app
    try {
      let response = await (await fetch(`http://localhost:8081/buy/${this.state.merchantId}`, {
        method: 'post',
        headers: {
          'Accept': 'application/json',
					'Content-Type': 'application/json',
					'Access-Control-Allow-Origin': '*',
        },
        body: JSON.stringify({
       
        })
      })).text();
      if (response != 'fail') {
        alert('Registration successful, we will send you email to confirm registration');
        this.props.onClose();
      } else {
        alert('Something went wrong.');
        this.props.onClose();
      }
    } catch (err) {
      this.setState({
        errors: err.toString()
      });
    }
  }

  render () {
    return (
      <div className="margin-top-page">
        <div className="d-flex flex-row mt-2 ml-3">
        Books
        <button className="btn btn-primary" onClick={this.onBuy}>Buy</button>
        </div>
      </div>
    );
  }
  
}

export default UserPage;
