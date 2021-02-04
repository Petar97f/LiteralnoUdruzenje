import React, { Component, Fragment } from 'react';
import User from '.././user/User';
import Forms from '.././forms/Forms.js';

class EditorPage extends Component {
  constructor(props) {
    super(props);
    this.state = {
      formFields: [],
      form: {},
      data: []
    }
  }
  async componentDidMount () {

  }

  render () {
    return (
      <div className="margin-top-page">
        <div className="d-flex flex-row ml-3 pt-4">
          <div className="mt-2">
            Editor Page:
            /** import component here */
          </div>
        </div>
      </div>
    );
  }
}

export default EditorPage;