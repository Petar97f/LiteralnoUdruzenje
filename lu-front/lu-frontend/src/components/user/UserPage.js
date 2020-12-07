import React, { Component, Fragment } from 'react';
import User from '.././user/User';

class UserPage extends Component {
  constructor(props) {
    super(props);
    this.state = {

    }
  }

  componentDidMount () {
    console.log(User)
  }
  onBuy = () => {
    //now we need to redirect on new app
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
