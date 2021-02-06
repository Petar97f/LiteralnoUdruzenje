import React, { Component, Fragment } from 'react';
import User from '.././user/User';
import Publish from '../app/Publish';
import ReadManuscript from "../app/ReadManuscript";

class PaymentStatus extends Component {
  constructor(props) {
    super(props);
    this.state = {
      path: props.history.location.pathname
    }
  }

  render () {
    return (
      <div className="margin-top-page">
        <div className="d-flex flex-row mt-2 ml-3 pt-4">
        <div className="mt-2">
          <p>Status</p>
          {this.state.path.includes('error') && <p>Error</p>}
          {this.state.path.includes('success') && <p>Success</p>}
          {this.state.path.includes('failed') && <p>Failed</p>}
          </div>
        </div>
      </div>
    ) 
  }
}
export default PaymentStatus;